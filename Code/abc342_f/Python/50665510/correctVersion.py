

#頂点は1-index,下段は0-index
class LazySegTree:

    #単位元と結合と作用をここで定義
    Xunit = (0,1)
    Aunit = 0

    
    def Xf(self,x,y):
        return (x[0] + y[0],x[1] + y[1])
    
    #Xf = max
    def Af(self,a,b):
        return a + b
    #AのXへの作用
    def operate(self,x,a):
        return (x[0] + a * x[1],x[1])

    def __init__(self,N):
        self.N = N
        self.X = [self.Xunit] * (N + N)
        self.A = [self.Aunit] * (N + N)
    def build(self,seq):
        for i,x in enumerate(seq,self.N):
            self.X[i] = x
        for i in range(self.N-1,0,-1):
            self.X[i] = self.Xf(self.X[i<<1],self.X[i<<1 | 1])
    def eval_at(self,i):
        return self.operate(self.X[i],self.A[i])
    def propagate_at(self,i):
        self.X[i] = self.eval_at(i)
        self.A[i<<1] = self.Af(self.A[i<<1],self.A[i])
        self.A[i<<1 | 1] = self.Af(self.A[i<<1 | 1],self.A[i])
        self.A[i] = self.Aunit
    def propagate_above(self,i):
        H = i.bit_length() - 1
        for h in range(H,0,-1):
            self.propagate_at(i >> h)
    def recalc_above(self,i):
        while i > 1:
            i >>= 1
            self.X[i] = self.Xf(self.eval_at(i << 1),self.eval_at(i << 1 | 1))
    def update(self,i,x):
        i += self.N
        self.propagate_above(i)
        self.X[i] = x
        self.A[i] = self.Aunit
        self.recalc_above(i)
    def fold(self,L = 0,R = -1):
        if R == -1:R = self.N
        L += self.N
        R += self.N
        self.propagate_above(L // (L & -L))
        self.propagate_above(R // (R & -R) -1)
        vL = (0,0)
        vR = (0,0)
        while L < R:
            if L & 1:
                vL = self.Xf(vL,self.eval_at(L))
                L += 1
            if R & 1:
                R -= 1
                vR = self.Xf(self.eval_at(R),vR)
            L >>= 1
            R >>= 1
        return self.Xf(vL,vR)
    def operate_range(self,L,R,x):
        #区間全体に作用させる
        L += self.N
        R += self.N
        L0 = L // (L & -L)
        R0 = R // (R & -R) - 1
        self.propagate_above(L0)
        self.propagate_above(R0)
        while L < R:
            if L & 1:
                self.A[L] = self.Af(self.A[L],x)
                L += 1
            if R & 1:
                R -= 1
                self.A[R] = self.Af(self.A[R],x)
            L >>= 1
            R >>= 1
        self.recalc_above(L0)
        self.recalc_above(R0)
    def write(self):
        print(self.X)
    def change(self,Xf,Xunit,Af,Aunit,operate):
        self.Xf = Xf
        self.Xunit = Xunit
        self.Af = Af
        self.Aunit = Aunit
        self.operate = operate

N,L,D = map(int,input().split())
seg = LazySegTree(N + 2)
seg.update(0,(1,1))
r = 1 / D

for i in range(L):
    u,v = seg.fold(i,i + 1)

    if i + D <= N:
        seg.operate_range(i + 1,i + D + 1,u * r)
    else:
        seg.operate_range(i + 1,N + 1,u * r)
        k = i + D - N
        seg.operate_range(N + 1,N + 2,u * k * r)
        
dp = [0] * (N + 1)
dp[N] = 1 - seg.fold(N,N + 1)[0]

seg2 = LazySegTree(N + 1)
seg2.update(N,(dp[N],1))

e = seg.fold(N + 1,N + 2)[0]

"""
for i in range(N - 1,-1,-1):
    if i > L:
        u = seg.fold(L,i)[0] + e
        t = 0
        for k in range(L,i):
            t += seg.fold(k,k + 1)[0]
        print(i,L,u - e,t)
    else:
        u = e

    if i + D <= N:
        v = seg2.fold(i + 1,i + D + 1)[0] * r
    else:
        v = seg2.fold(i + 1,N + 1)[0] * r

    seg2.update(i,(max(u,v),1))
print(seg2.fold(0,1)[0])

for i in range(N + 1):
    print(seg.fold(i,i + 1)[0],seg2.fold(i,i + 1)[0])
seg.write()
"""

S = [0] * (N + 1)
for i in range(1,N + 1):
    S[i] = seg.fold(i,i + 1)[0]
    if i > 0:
        S[i] += S[i - 1]

dp = [0] * (N + 2)
dp[N] = 1 - seg.fold(N,N + 1)[0]
for i in range(N - 1,-1,-1):
    if i > L:
        u = S[i-1] - S[L - 1] + e
    else:
        u = e
    if i + D <= N:
        v = dp[i + 1] - dp[i + D + 1]
        v *= r
    else:
        v = dp[i + 1] * r
    dp[i] = max(u,v)
    dp[i] += dp[i + 1]
print(dp[0] - dp[1])
