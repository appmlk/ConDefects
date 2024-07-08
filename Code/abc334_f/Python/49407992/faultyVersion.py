
def segfunc(x, y):
    return min(x,y)

ide_ele = 100000000000

class segtree:
    def __init__(self, init_val, segfunc, ide_ele):

        n = len(init_val)
        self.segfunc = segfunc
        self.ide_ele = ide_ele
        self.num = 1 << (n - 1).bit_length()
        self.tree = [ide_ele] * 2 * self.num

        for i in range(n):
            self.tree[self.num + i] = init_val[i]

        for i in range(self.num - 1, 0, -1):
            self.tree[i] = self.segfunc(self.tree[2 * i], self.tree[2 * i + 1])

    def update(self, k, x):

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


n,k=map(int,input().split())
sx,sy=map(int,input().split())
d=[]
p=[]
dsum=0
for i in range(n):
    x,y=map(int,input().split())
    dist1=((sx-x)**2+(sy-y)**2)**0.5
    if i==0:
        d.append(dist1)
        dsum+=dist1
    else:
        dist2=((p[-1][0]-sx)**2+(p[-1][1]-sy)**2)**0.5
        dist3=((p[-1][0]-x)**2+(p[-1][1]-y)**2)**0.5
        d.append(dist1+dist2-dist3)
        dsum+=dist3
    p.append((x,y))
        
dp=[10**18]*n
dp[0]=0
seg=segtree(dp,segfunc,ide_ele)

for i in range(1,n):
    dp[i]=seg.query(max(0,i-k),i)+d[i]
    seg.update(i,dp[i])


dist=((p[-1][0]-sx)**2+(p[-1][1]-sy)**2)**0.5
ans=seg.query(n-k,n)+dist+dsum
print(ans)