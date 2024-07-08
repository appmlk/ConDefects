class lazy_segtree():
    def update(self,k):
        self.d[k]=self.op(self.d[2*k],self.d[2*k+1])
    def all_apply(self,k,f):
        self.d[k]=self.mapping(f,self.d[k])
        if (k<self.size):
            self.lz[k]=self.composition(f,self.lz[k])
    def push(self,k):
        self.all_apply(2*k,self.lz[k])
        self.all_apply(2*k+1,self.lz[k])
        self.lz[k]=self.identity
    def __init__(self,V,OP,E,MAPPING,COMPOSITION,ID):
        self.n=len(V)
        self.log=(self.n-1).bit_length()
        self.size=1<<self.log
        self.d=[E for i in range(2*self.size)]
        self.lz=[ID for i in range(self.size)]
        self.e=E
        self.op=OP
        self.mapping=MAPPING
        self.composition=COMPOSITION
        self.identity=ID
        self.length=[1]*(2*self.size)
        for k in range(self.size-1,0,-1):
            self.length[k]=self.op(self.length[2*k],self.length[2*k+1])
        for i in range(self.n):
            self.d[self.size+i]=V[i]
        for i in range(self.size-1,0,-1):
            self.update(i)
    def set(self,p,x):
        assert 0<=p and p<self.n
        p+=self.size
        for i in range(self.log,0,-1):
            self.push(p>>i)
        self.d[p]=x
        for i in range(1,self.log+1):
            self.update(p>>i)
    def get(self,p):
        assert 0<=p and p<self.n
        p+=self.size
        for i in range(self.log,0,-1):
            self.push(p>>i)
        return self.d[p]
    def prod(self,l,r):
        assert 0<=l and l<=r and r<=self.n
        if l==r:
            return self.e
        l+=self.size
        r+=self.size
        for i in range(self.log,0,-1):
            if (((l>>i)<<i)!=l):
                self.push(l>>i)
            if (((r>>i)<<i)!=r):
                self.push(r>>i)
        sml,smr=self.e,self.e
        while(l<r):
            if l&1:
                sml=self.op(sml,self.d[l])
                l+=1
            if r&1:
                r-=1
                smr=self.op(self.d[r],smr)
            l>>=1
            r>>=1
        return self.op(sml,smr)
    def all_prod(self):
        return self.d[1]
    def apply_point(self,p,f):
        assert 0<=p and p<self.n
        p+=self.size
        for i in range(self.log,0,-1):
            self.push(p>>i)
        self.d[p]=self.mapping(f,self.d[p])
        for i in range(1,self.log+1):
            self.update(p>>i)
    def apply(self,l,r,f):
        assert 0<=l and l<=r and r<=self.n
        if l==r:
            return
        l+=self.size
        r+=self.size
        for i in range(self.log,0,-1):
            if (((l>>i)<<i)!=l):
                self.push(l>>i)
            if (((r>>i)<<i)!=r):
                self.push((r-1)>>i)
        l2,r2=l,r
        while(l<r):
            if (l&1):
                self.all_apply(l,f)
                l+=1
            if (r&1):
                r-=1
                self.all_apply(r,f)
            l>>=1
            r>>=1
        l,r=l2,r2
        for i in range(1,self.log+1):
            if (((l>>i)<<i)!=l):
                self.update(l>>i)
            if (((r>>i)<<i)!=r):
                self.update((r-1)>>i)
    
################################
#opの単位元 op(data1, e) = data1
e=0

#区間演算
def op(x,y):
    return (x+y)%mod
    
#上のlazy→下のdata
def mapping(lazy_upper,data_lower):
    a,b=lazy_upper
    return (a*data_lower+b)%mod

#上のlazy→下のlazy
def composition(lazy_upper,lazy_lower):
    a1,b1=lazy_upper
    a2,b2=lazy_lower
    return (a1*a2)%mod,(b2*a1+b1)%mod

#mapping(_id, data_lower) = data_lower
_id=(1,0)

#V,OP,E,MAPPING,COMPOSITION,ID

N,M=map(int,input().split())
A=list(map(int,input().split()))
mod=998244353

seg=lazy_segtree(A,op,e,mapping,composition,_id)

for i in range(M):
    l,r,x=map(int,input().split())
    l-=1
    v=r-l
    d=pow(v,-1,mod)
    seg.apply(l,r,((1-d)%mod,(d*x)%mod))
    
for i in range(N):
    print(seg.get(i)%mod)