        
#####segfunc#####
def Sum(x, y):
    return x+y
#################


class SegTree:
    
    #init(init_val, ide_ele): 配列init_valで初期化 O(N)
    #update(k, x): k番目の値をxに更新 O(logN)
    #query(l, r): 区間[l, r)をsegfuncしたものを返す O(logN)
    def __init__(self, init_val, segfunc, ide_ele):
        
        #init_val: 配列の初期値
        #segfunc: 区間にしたい操作
        #ide_ele: 単位元
        #n: 要素数
        #num: n以上の最小の2のべき乗
        #tree: セグメント木(1-index)
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
        #k番目の値をxに更新
        #k: index(0-index)
        #x: update value
        k += self.num
        self.tree[k] = x
        while k > 1:
            self.tree[k >> 1] = self.segfunc(self.tree[k], self.tree[k ^ 1])
            k >>= 1

    def query(self, l, r):
        #[l, r)のsegfuncしたものを得る
        #l: index(0-index)
        #r: index(0-index)
        res = self.ide_ele

        l += self.num
        r += self.num
        while l < r:
            if l & 1:
                res = self.segfunc(res,self.tree[l])
                l += 1
            if r & 1:
                res = self.segfunc(res,self.tree[r - 1])
            l >>= 1
            r >>= 1
        return res

def func(a,b):
    if a[-1]>b[-1]:
        a,b=b,a
    return (a[0]+b[0],max(a[1],a[0]+b[1]),max(a[-1],b[-1]))

'''
セグ木に(0からiまでの合計、0からj(j<=i)までの方形の最大値)
を乗せる。
'''
N,B,Q=map(int,input().split())
A=list(map(int,input().split()))
st=SegTree([(A[i]-B,A[i]-B,i) for i in range(N)],func,(0,-10**15,-1))
for _ in range(Q):
    C,X=map(int,input().split())
    st.update(C-1,(X-B,X-B))
    left,right=0,N
    while right-left>1:
        mid=(right+left)//2
        q=st.query(0,mid)
        if q[1]>=0:
            right=mid
        else:
            left=mid
    print(st.query(0,right)[0]/right+B)
