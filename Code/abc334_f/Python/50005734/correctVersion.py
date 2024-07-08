N,K=list(map(int, input().split()))
Sx,Sy=list(map(int, input().split()))
def segfunc(x, y):
    return min(x,y)
ide_ele =10**17
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
P=[]
def calc(S,T):
    return ((S[0]-T[0])**2+(S[1]-T[1])**2)**0.5
for _ in range(N):
    P.append(list(map(int, input().split())))
P.append([Sx,Sy])
ans=calc((Sx,Sy),P[0])
dp=[0]*N
for i in range(N):
    a=calc(P[i],P[i+1])
    b=calc(P[i],P[-1])+calc(P[-1],P[i+1])
    ans+=a
    dp[i]=b-a

Seg=SegTree([10**17]*(N+10),segfunc,ide_ele)
Seg.update(0,0)
for i in range(1,N+1):
    Seg.update(i,Seg.query(max(0,i-K),i)+dp[i-1])
ans+=Seg.query(N,N+1)
print(ans)