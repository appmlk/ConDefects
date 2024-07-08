from collections import defaultdict

class Segment_Tree:
    def __init__(self,N,f,e,lst=None,dynamic=False):
        self.f=f
        self.e=e
        self.N=N
        if dynamic:
            self.segment_tree=defaultdict(lambda:self.e)
        else:
            if lst==None:
                self.segment_tree=[self.e]*2*self.N
            else:
                assert len(lst)<=self.N
                self.segment_tree=[self.e]*self.N+[x for x in lst]+[self.e]*(N-len(lst))
                for i in range(self.N-1,0,-1):
                    self.segment_tree[i]=self.f(self.segment_tree[i<<1],self.segment_tree[i<<1|1])

    def __getitem__(self,i):
        if type(i)==int:
            if -self.N<=i<0:
                return self.segment_tree[i+self.N*2]
            elif 0<=i<self.N:
                return self.segment_tree[i+self.N]
            else:
                raise IndexError("list index out of range")
        else:
            a,b,c=i.start,i.stop,i.step
            if a==None:
                a=self.N
            else:
                a+=self.N
            if b==None:
                b=self.N*2
            else:
                b+=self.N
            return self.segment_tree[slice(a,b,c)]

    def __setitem__(self,i,x):
        if -self.N<=i<0:
            i+=self.N*2
        elif 0<=i<self.N:
            i+=self.N
        else:
            raise IndexError("list index out of range")
        self.segment_tree[i]=x
        while i>1:
            i>>= 1
            self.segment_tree[i]=self.f(self.segment_tree[i<<1],self.segment_tree[i<<1|1])

    def Build(self,lst):
        for i,x in enumerate(lst,self.N):
            self.segment_tree[i]=x
        for i in range(self.N-1,0,-1):
            self.segment_tree[i]=self.f(self.segment_tree[i<<1],self.segment_tree[i<<1|1])

    def Fold(self,L=None,R=None):
        if L==None:
            L=self.N
        else:
            L+=self.N
        if R==None:
            R=self.N*2
        else:
            R+=self.N
        vL=self.e
        vR=self.e
        while L<R:
            if L&1:
                vL=self.f(vL,self.segment_tree[L])
                L+=1
            if R&1:
                R-=1
                vR=self.f(self.segment_tree[R],vR)
            L>>=1
            R>>=1
        return self.f(vL,vR)

    def Fold_Index(self,L=None,R=None):
        if L==None:
            L=self.N
        else:
            L+=self.N
        if R==None:
            R=self.N*2
        else:
            R+=self.N
        if L==R:
            return None
        x=self.Fold(L-self.N,R-self.N)
        while L<R:
            if L&1:
                if self.segment_tree[L]==x:
                    i=L
                    break
                L+=1
            if R&1:
                R-=1
                if self.segment_tree[R]==x:
                    i=R
                    break
            L>>=1
            R>>=1
        while i<self.N:
            if self.segment_tree[i]==self.segment_tree[i<<1]:
                i<<=1
            else:
                i<<=1
                i|=1
        i-=self.N
        return i

    def Bisect_Right(self,L=None,f=None):
        if L==self.N:
            return self.N
        if L==None:
            L=0
        L+=self.N
        vl=self.e
        vr=self.e
        l,r=L,self.N*2
        while l<r:
            if l&1:
                vl=self.f(vl,self.segment_tree[l])
                l+=1
            if r&1:
                r-=1
                vr=self.f(self.segment_tree[r],vr)
            l>>=1
            r>>=1
        if f(self.f(vl,vr)):
            return self.N
        v=self.e
        while True:
            while L%2==0:
                L>>=1
            vv=self.f(v,self.segment_tree[L])
            if f(vv):
                v=vv
                L+=1
            else:
                while L<self.N:
                    L<<=1
                    vv=self.f(v,self.segment_tree[L])
                    if f(vv):
                        v=vv
                        L+=1
                return L-self.N

    def Bisect_Left(self,R=None,f=None):
        if R==0:
            return 0
        if R==None:
            R=self.N
        R+=self.N
        vl=self.e
        vr=self.e
        l,r=self.N,R
        while l<r:
            if l&1:
                vl=self.f(vl,self.segment_tree[l])
                l+=1
            if r&1:
                r-=1
                vr=self.f(self.segment_tree[r],vr)
            l>>=1
            r>>=1
        if f(self.f(vl,vr)):
            return 0
        v=self.e
        while True:
            R-=1
            while R>1 and R%2:
                R>>=1
            vv=self.f(self.segment_tree[R],v)
            if f(vv):
                v=vv
            else:
                while R<self.N:
                    R=2*R+1
                    vv=self.f(self.segment_tree[R],v)
                    if f(vv):
                        v=vv
                        R-=1
                return R+1-self.N

    def __str__(self):
        return "["+", ".join(map(str,self.segment_tree[self.N:]))+"]"

def Extended_Euclid(n,m):
    stack=[]
    while m:
        stack.append((n,m))
        n,m=m,n%m
    if n>=0:
        x,y=1,0
    else:
        x,y=-1,0
    for i in range(len(stack)-1,-1,-1):
        n,m=stack[i]
        x,y=y,x-(n//m)*y
    return x,y

class MOD:
    def __init__(self,p,e=None):
        self.p=p
        self.e=e
        if self.e==None:
            self.mod=self.p
        else:
            self.mod=self.p**self.e

    def Pow(self,a,n):
        a%=self.mod
        if n>=0:
            return pow(a,n,self.mod)
        else:
            #assert math.gcd(a,self.mod)==1
            x=Extended_Euclid(a,self.mod)[0]
            return pow(x,-n,self.mod)

    def Build_Fact(self,N):
        assert N>=0
        self.factorial=[1]
        if self.e==None:
            for i in range(1,N+1):
                self.factorial.append(self.factorial[-1]*i%self.mod)
        else:
            self.cnt=[0]*(N+1)
            for i in range(1,N+1):
                self.cnt[i]=self.cnt[i-1]
                ii=i
                while ii%self.p==0:
                    ii//=self.p
                    self.cnt[i]+=1
                self.factorial.append(self.factorial[-1]*ii%self.mod)
        self.factorial_inve=[None]*(N+1)
        self.factorial_inve[-1]=self.Pow(self.factorial[-1],-1)
        for i in range(N-1,-1,-1):
            ii=i+1
            while ii%self.p==0:
                ii//=self.p
            self.factorial_inve[i]=(self.factorial_inve[i+1]*ii)%self.mod

    def Build_Inverse(self,N):
        self.inverse=[None]*(N+1)
        assert self.p>N
        self.inverse[1]=1
        for n in range(2,N+1):
            if n%self.p==0:
                continue
            a,b=divmod(self.mod,n)
            self.inverse[n]=(-a*self.inverse[b])%self.mod

    def Inverse(self,n):
        return self.inverse[n]

    def Fact(self,N):
        if N<0:
            return 0
        retu=self.factorial[N]
        if self.e!=None and self.cnt[N]:
            retu*=pow(self.p,self.cnt[N],self.mod)%self.mod
            retu%=self.mod
        return retu

    def Fact_Inve(self,N):
        if self.e!=None and self.cnt[N]:
            return None
        return self.factorial_inve[N]

    def Comb(self,N,K,divisible_count=False):
        if K<0 or K>N:
            return 0
        retu=self.factorial[N]*self.factorial_inve[K]%self.mod*self.factorial_inve[N-K]%self.mod
        if self.e!=None:
            cnt=self.cnt[N]-self.cnt[N-K]-self.cnt[K]
            if divisible_count:
                return retu,cnt
            else:
                retu*=pow(self.p,cnt,self.mod)
                retu%=self.mod
        return retu

N,K=map(int,input().split())
P=list(map(int,input().split()))
for k in range(K):
    P[k]-=1
ST=Segment_Tree(N,lambda x,y:x+y,0,[1]*N)
mod=998244353
MD=MOD(mod)
MD.Build_Fact(N)
S0=0
for k in range(K):
    S0+=ST.Fold(0,P[k])*MD.Fact(N-1-k)%mod*MD.Fact_Inve(N-K)%mod
    ST[P[k]]-=1
if N==K:
    for i in range(N):
        print((S0+1)%mod)
    exit()
S1=0
lst=[]
lst1=[]
ST=Segment_Tree(N,lambda x,y:x+y,0,[1]*N)
for k in range(K):
    lst.append(MD.Fact(N-2-k)*MD.Fact_Inve(N-K-1)%mod)
    lst1.append(ST.Fold(0,P[k])*MD.Fact(N-2-k)%mod*MD.Fact_Inve(N-K-1)%mod)
    S1+=ST.Fold(0,P[k])*MD.Fact(N-2-k)%mod*MD.Fact_Inve(N-K-1)%mod
    ST[P[k]]-=1
idx=[K+1]*N
for k in range(K):
    idx[P[k]]=k
ST=Segment_Tree(K+1,lambda x,y:x+y,0,lst+[0])
ST1=Segment_Tree(K+1,lambda x,y:x+y,0,lst1+[0])
ans_lst=[None]*N
for n in range(N):
    ans_lst[n]=S0-S1+ST.Fold(0,idx[n])+ST1.Fold(idx[n]+1,K)
    ans_lst[n]%=mod
    if idx[n]<K:
        ST[idx[n]]=0
        ans_lst[n]+=1
for n in range(N):
    ans_lst[n]%=mod
print(*ans_lst,sep="\n")
