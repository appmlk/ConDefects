from collections import defaultdict
import sys


class segtree():
    n=1
    size=1
    log=2
    d=[0]
    op=None
    e=10**15
    def __init__(self,V,OP,E):
        self.n=len(V)
        self.op=OP
        self.e=E
        self.log=(self.n-1).bit_length()
        self.size=1<<self.log
        self.d=[E for i in range(2*self.size)]
        for i in range(self.n):
            self.d[self.size+i]=V[i]
        for i in range(self.size-1,0,-1):
            self.update(i)
    def set(self,p,x):
        assert 0<=p and p<self.n
        p+=self.size
        self.d[p]=x
        for i in range(1,self.log+1):
            self.update(p>>i)
    def get(self,p):
        assert 0<=p and p<self.n
        return self.d[p+self.size]
    def prod(self,l,r):
        assert 0<=l and l<=r and r<=self.n
        sml=self.e
        smr=self.e
        l+=self.size
        r+=self.size
        while(l<r):
            if (l&1):
                sml=self.op(sml,self.d[l])
                l+=1
            if (r&1):
                smr=self.op(self.d[r-1],smr)
                r-=1
            l>>=1
            r>>=1
        return self.op(sml,smr)
    def all_prod(self):
        return self.d[1]
    def max_right(self,l,f):
        assert 0<=l and l<=self.n
        assert f(self.e)
        if l==self.n:
            return self.n
        l+=self.size
        sm=self.e
        while(1):
            while(l%2==0):
                l>>=1
            if not(f(self.op(sm,self.d[l]))):
                while(l<self.size):
                    l=2*l
                    if f(self.op(sm,self.d[l])):
                        sm=self.op(sm,self.d[l])
                        l+=1
                return l-self.size
            sm=self.op(sm,self.d[l])
            l+=1
            if (l&-l)==l:
                break
        return self.n
    def min_left(self,r,f):
        assert 0<=r and r<self.n
        assert f(self.e)
        if r==0:
            return 0
        r+=self.size
        sm=self.e
        while(1):
            r-=1
            while(r>1 & (r%2)):
                r>>=1
            if not(f(self.op(self.d[r],sm))):
                while(r<self.size):
                    r=(2*r+1)
                    if f(self.op(self.d[r],sm)):
                        sm=self.op(self.d[r],sm)
                        r-=1
                return r+1-self.size
            sm=self.op(self.d[r],sm)
            if (r& -r)==r:
                break
        return 0
    def update(self,k):
        self.d[k]=self.op(self.d[2*k],self.d[2*k+1])
    def __str__(self):
        return str([self.get(i) for i in range(self.n)])

MOD = 998244353
sys.setrecursionlimit(10**9)

N,H = map(int,input().split())
X = list(map(int,input().split()))

position_index = list(zip(X,range(N)))
position_index.sort()

V = []
for _,i in position_index:
    V.append(i)

R = [None] * N
for i in range(N):
    R[V[i]] = i

components = []
positions = [None] * N

st = segtree(V, min, N)

l = -10**18
for p,i in position_index:
    if l+H < p:
        components.append([i])
    else:
        components[-1].append(i)
    l = p
    
for i in range(len(components)):
    comp = components[i]
    for j in range(len(comp)):
        positions[comp[j]] = (i,j)

def calc(turn, depth, l, r):
    if l >= r:
        dc = defaultdict(int)
        dc[turn] = pow(2,-depth,MOD)
        return dc
    
    t = 10**6

    t = st.prod(l,r)
    j = R[t]
    
    dc = defaultdict(int)
    left = calc(t, depth+1, l, j)
    right = calc(t, depth+1, j+1, r)
    
    if len(left) < len(right):
        for k in left:
            right[k] += left[k]
            right[k] %= MOD
        return right
    else:
        for k in right:
            left[k] += right[k]
            left[k] %= MOD
        return left

ans = []
temp = 0
for comp in components:
    x = len(comp)
    ans.append(calc(0,0, temp, temp+x))
    temp += x
    
components_prob = [0] * len(components)

ans_lst = [None] * N
zeros = len(components)
prob = 0
prod = 0
for i in range(N):
    j,_ = positions[i]
    if components_prob[j] == 0:
        if ans[j][i] > 0:
            components_prob[j] = ans[j][i]
            if zeros == 1:
                prod = 1
                for num in components_prob:
                    prod *= num
                    prod %= MOD
            zeros -= 1
    else:
        prod = prod * pow(components_prob[j], -1, MOD) * (components_prob[j] + ans[j][i])
        prod %= MOD
        components_prob[j] += ans[j][i]
        components_prob[j] %= MOD
        if components_prob[j] == 0:
            zeros += 1
    
    ans_lst[i] = prod - prob
    prob = prod

coef = pow(2,N,MOD)
ans_lst = list(map(lambda x: (x*coef)%MOD,ans_lst))
print(*ans_lst)
