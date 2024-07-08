import sys
readline=sys.stdin.readline
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

def Parallel_Bisect(ok_list,ng_list,is_ok):
    while True:
        mid_list=[]
        for i,(ok,ng) in enumerate(zip(ok_list,ng_list)):
            if abs(ok-ng)>=2:
                mid_list.append(((ok+ng)//2,i))
        if not mid_list:
            break
        for (mid,i),bl in zip(mid_list,is_ok(mid_list)):
            if bl:
                ok_list[i]=mid
            else:
                ng_list[i]=mid
    return ok_list

N=int(readline())
M=10**5
Y=[[] for x in range(2*M+2)]
for n in range(N):
    x,y=map(int,readline().split())
    x,y=x+y,x-y+M
    Y[x].append(y)
Q=int(readline())
A,B,K=[],[],[]
for q in range(Q):
    a,b,k=map(int,readline().split())
    a,b=a+b,a-b+M
    A.append(a)
    B.append(b)
    K.append(k)
ok_list=[-1]*Q
ng_list=[2*M+10]*Q
def is_ok(mid_list):
    query=[[] for m in range(2*M+2)]
    cnt=[0]*len(mid_list)
    for q,(mid,i) in enumerate(mid_list):
        query[max(0,A[i]-mid)].append((q,max(0,B[i]-mid),min(2*M+1,B[i]+mid+1),-1))
        query[min(2*M+1,A[i]+mid+1)].append((q,max(0,B[i]-mid),min(2*M+1,B[i]+mid+1),1))
    ST=Segment_Tree(2*M+1,lambda x,y:x+y,0)
    for x in range(2*M+2):
        for q,l,r,s in query[x]:
            cnt[q]+=s*ST.Fold(l,r)
        for y in Y[x]:
            ST[y]+=1
    return [c<K[i] for c,(mid,i) in zip(cnt,mid_list)]
ans_lst=Parallel_Bisect(ok_list,ng_list,is_ok)
for ans in ans_lst:
    print(ans+1)