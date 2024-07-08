
inf = 10 ** 16
#頂点は1-index,下段は0-index
class LazySegTree:

    #単位元と結合と作用をここで定義
    Xunit = -inf
    Aunit = 0

    
    def Xf(self,x,y):
        return max(x,y)
    
    #Xf = max
    def Af(self,a,b):
        return a + b
    #AのXへの作用
    def operate(self,x,a):
        return x + a

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
        vL = self.Xunit
        vR = self.Xunit
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

N,B,Q = map(int,input().split())
a = list(map(int,input().split()))
l = [0] * N
l[0] = a[0] - B
for i in range(1,N):
    l[i] = l[i-1] + a[i] - B
seg = LazySegTree(N)
seg.build(l)
for _ in range(Q):
    c,x = map(int,input().split())
    delta = x - a[c-1]
    a[c-1] = x
    seg.operate_range(c-1,N,delta)
    tmp = seg.fold(0,N)
    if tmp < 0:
        print(seg.fold(N - 1,N) / N + B)
        continue
    end = N
    start = 0
    while end - start > 1:
        mid = end +start >> 1
        if seg.fold(0,mid) >= 0:
            end = mid
        else:
            start = mid
    
    print(seg.fold(0,end) / end + B)
