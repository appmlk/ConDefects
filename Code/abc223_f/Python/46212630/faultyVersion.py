
import bisect, heapq, sys, math, copy, itertools, decimal
from collections import defaultdict, deque
sys.setrecursionlimit(10**7)
def INT(): return int(input())
def MI(): return map(int, input().split())
def MS(): return map(str, input().split())
def LI(): return list(map(int, input().split()))
def LS(): return list(map(str, input().split()))
def pr_line(itr): print(*itr, sep='\n')
def pr_mtx(matrix): [print(*row) for row in matrix] 
INF = 1<<62

'''
操作：(segfunc, 単位元) = {
        最小値：(min(x, y), INF),
        最大値：(max(x, y), -INF),
        区間和：(x + y, 0),
        区間積：(x * y, 1),
        最大公約数：(math.gcd(x, y), 0),
        論理和：(x | y, 0)
        論理積：(x & y, 0)
        排他的論理和：(x ^ y, 0)
        }
'''
#############################################################s

def segfunc(x, y):
    return [x[0] + y[0], min(x[1], x[0] + y[1])]

ide_ele = [0, INF]

class SegTree:
    def __init__(self, init_val, segfunc, ide_ele):
        n = len(init_val)  #要素数
        self.segfunc = segfunc  #区間にしたい操作
        self.ide_ele = ide_ele  #単位元
        self.num = 1 << (n-1).bit_length()  #n以上最小の2のべき乗
        self.tree = [ide_ele] * 2 * self.num  #セグメント木
        #配列の値を歯にセット
        for i in range(n):
            self.tree[self.num + i] = init_val[i]
        #構築していく
        for i in range(self.num - 1, 0, -1):
            self.tree[i] = self.segfunc(self.tree[2 * i], self.tree[2 * i + 1])
    
    def update(self, k, x): #k番目(0-index)の値をxに更新
        k += self.num #葉に移動
        self.tree[k] = x
        while k > 1:
            #隣同士の子ノードの処理結果を親ノードに更新
            self.tree[k >> 1] = self.segfunc(self.tree[k], self.tree[k | 1])            
            k >>= 1
    
    def query(self, l ,r): #[l, r)のsegfunc結果を得る（0-index）
        res_l, res_r = self.ide_ele, self.ide_ele
        l += self.num
        r += self.num
        while l < r:
            if l & 1:
                res_l = self.segfunc(res_l, self.tree[l])
                l += 1
            if r & 1:
                res_r = self.segfunc(self.tree[r - 1], res_r)
            l >>= 1
            r >>= 1
        res = self.segfunc(res_l, res_r)
        return res 
    
    def __getitem__(self, x):
        x += self.num #葉に移動
        return self.tree[x]

###############################################################

N, Q = MI()
S = list(input())
A = [[1, 1] if s=='(' else [-1, -1] for s in S] 

seg = SegTree(A, segfunc=segfunc, ide_ele=ide_ele)
ans = []
for _ in range(Q):
    q, l, r = MI()
    l -= 1
    r -= 1
    if q == 1:
        a, b = seg[l], seg[r]
        seg.update(l, b)
        seg.update(r, a)
    else:
        a, b = seg.query(l, r+1) #a:l~rの累積和, b:l~rの最小値
        if a == 0 and b == 0:
            ans.append('Yes')
        else:
            ans.append('No')

pr_line(ans)