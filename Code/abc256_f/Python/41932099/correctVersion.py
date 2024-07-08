def extgcd(a, b):
    if b:
        d, y, x = extgcd(b, a % b)
        y -= (a // b) * x
        return d, x, y
    return a, 1, 0

# V = [(X_i, Y_i), ...]: X_i (mod Y_i)
def remainder(V):
    x = 0; d = 1
    for X, Y in V:
        g, a, b = extgcd(d, Y)
        x, d = (Y*b*x + d*a*X) // g, d*(Y // g)
        x %= d
    return x, d

#以下modinv
def mod_inv(a, m):
    g, x, y = extgcd(a, m)

    if g != 1:
        raise Exception("a and m are not coprime.")

    if x < 0:
        x += m

    return x

        
#####segfunc#####
def Sum(x, y):
    return x+y
#################


class SegTree:
    """
    init(init_val, ide_ele): 配列init_valで初期化 O(N)
    update(k, x): k番目の値をxに更新 O(logN)
    query(l, r): 区間[l, r)をsegfuncしたものを返す O(logN)
    """
    def __init__(self, init_val, segfunc, ide_ele):
        """
        init_val: 配列の初期値
        segfunc: 区間にしたい操作
        ide_ele: 単位元
        n: 要素数
        num: n以上の最小の2のべき乗
        tree: セグメント木(1-index)
        """
        n = len(init_val)
        self.segfunc = segfunc
        self.ide_ele = ide_ele
        self.num = 1 << (n - 1).bit_length()
        self.tree = [ide_ele] * 2 * self.num
        # 配列の値を葉にセット
        for i in range(n):
            self.tree[self.num + i] = init_val[i]
        # 構築していく
        for i in range(self.num - 1, 0, -1):
            self.tree[i] = self.segfunc(self.tree[2 * i], self.tree[2 * i + 1])

    def update(self, k, x):
        """
        k番目の値をxに更新
        k: index(0-index)
        x: update value
        """
        k += self.num
        self.tree[k] = x
        while k > 1:
            self.tree[k >> 1] = self.segfunc(self.tree[k], self.tree[k ^ 1])
            k >>= 1

    def query(self, l, r):
        """
        [l, r)のsegfuncしたものを得る
        l: index(0-index)
        r: index(0-index)
        """
        res = self.ide_ele

        l += self.num
        r += self.num
        while l < r:
            if l & 1:
                res = self.segfunc(res, self.tree[l])
                l += 1
            if r & 1:
                res = self.segfunc(res, self.tree[r - 1])
            l >>= 1
            r >>= 1
        return res
        
    def setValue(self,x,i):
        self.update(x-1,i)
        
    def getMax(self,i,j):
        return self.query(i-1,j)

import sys
input=sys.stdin.readline
N,Q=map(int,input().split())
A=list(map(int,input().split()))
Ai=[]
Aii=[]
for i in range(N):
    Ai.append(A[i]*(i+1))
    Aii.append(A[i]*(i+1)*(i+1))
sta=SegTree(A,Sum,0)
stai=SegTree(Ai,Sum,0)
staii=SegTree(Aii,Sum,0)
p=998244353
for _ in range(Q):
    q=list(map(int,input().split()))
    if q[0]==1:
        q,x,v=q
        sta.update(x-1,v)
        stai.update(x-1,v*x)
        staii.update(x-1,v*x*x)
    else:
        q,x=q
        print((sta.getMax(1,x)*(x+1)*(x+2)-stai.getMax(1,x)*(2*x+3)+staii.getMax(1,x))*mod_inv(2,p)%p)