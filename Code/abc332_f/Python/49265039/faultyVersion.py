mod=998244353
class SegTreeLazy():
    def __init__(self, a):
        # 初期化
        n=len(a)
        self.n = n
        self.a = a
        self.ans=[0]*n
        self.logN=(n-1).bit_length()
        self.n0 = 1<<self.logN
        self.ktree=[1]*2*self.n0
        self.dtree=[0]*2*self.n0
        for i in range(n):
            self.dtree[self.n0-1+i]=a[i]
            self.ktree[self.n0-1+i]=0
        self.lazy=[None]*2*self.n0 
    # [l,r)の上にある親ノード全てを返す
    def gindex(self,l, r):
        L = (l + self.n0) >> 1; R = (r + self.n0) >> 1
        lc = 0 if l & 1 else (L & -L).bit_length()
        rc = 0 if r & 1 else (R & -R).bit_length()
        for i in range(self.logN):
            if rc <= i:
                yield R
            if L < R and lc <= i:
                yield L
            L >>= 1; R >>= 1     
    # 遅延伝搬処理
    def propagates(self,*ids):
        for i in reversed(ids):
            v = self.lazy[i-1]
            if v is None:
                continue
            self.lazy[2*i-1] = self.lazy[2*i]=1
            self.ktree[2*i-1] = self.ktree[i-1]*self.ktree[2*i-1]%mod
            self.ktree[2*i] =  self.ktree[i-1]*self.ktree[2*i]%mod
            self.dtree[2*i-1] =  (self.ktree[i-1]*self.dtree[2*i-1]%mod+self.dtree[i-1])%mod
            self.dtree[2*i] = (self.ktree[i-1]*self.dtree[2*i]%mod+self.dtree[i-1])%mod
            self.lazy[i-1] = None
            self.ktree[i-1] = 1 
            self.dtree[i-1] = 0 
     
    # 区間[l, r)をk,dで更新
    def update(self,l, r, k,d):
        *ids, = self.gindex(l, r)
        self.propagates(*ids)
        L = self.n0 + l; R = self.n0 + r
        while L < R:
            if R & 1:
                R -= 1
                self.ktree[R-1] =self.ktree[R-1]*k%mod 
                self.dtree[R-1] = (self.dtree[R-1]*k%mod+d)%mod
                self.lazy[R-1]=1
            if L & 1:
                self.ktree[L-1] = self.ktree[L-1]*k%mod
                self.dtree[L-1] = (self.dtree[L-1]*k%mod+d)%mod
                self.lazy[L-1]=1
                L += 1
            L >>= 1; R >>= 1
            
        
    def query(self):
        ans=[0]*n
        for i in range(1,self.n0):
            v = self.lazy[i-1]
            if v is None:
                continue
            self.lazy[2*i-1] = self.lazy[2*i]=1
            self.ktree[2*i-1] = self.ktree[i-1]*self.ktree[2*i-1]%mod
            self.ktree[2*i] =  self.ktree[i-1]*self.ktree[2*i]%mod
            self.dtree[2*i-1] =  (self.ktree[i-1]*self.dtree[2*i-1]%mod+self.dtree[i-1])%mod
            self.dtree[2*i] = (self.ktree[i-1]*self.dtree[2*i]%mod+self.dtree[i-1])%mod
            self.lazy[i-1] = None
            self.ktree[i-1] = 1 
            self.dtree[i-1] = 0 
            
        return self.dtree[self.n0-1:self.n0-1+self.n]
def modinv(a):
    return pow(a,mod-2,mod)
    
n,m=map(int,input().split())
A=list(map(int,input().split()))
st=SegTreeLazy(A)
p=[0]*n
q=[0]*n
for i in range(n):
    p[i]=modinv(i+1)
    q[i]=(1-p[i])%mod
for i in range(m):
    l,r,x=map(int,input().split())
    st.update(l-1,r,q[r-l],p[r-l]*x%mod)
ans=st.query()
print(*ans)    
