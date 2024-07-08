import bisect
import sys
readline=sys.stdin.readline

def Compress(lst):
    decomp=sorted(list(set(lst)))
    comp={x:i for i,x in enumerate(decomp)}
    return comp,decomp

def SA_IS(lst,compressed=False):
    N=len(lst)
    if not compressed:
        decomp=sorted(list(set(lst)))
        comp={x:i for i,x in enumerate(decomp)}
        lst=[comp[s]+1 for s in lst]+[0]
    else:
        lst=[s+1 for s in lst]+[0]

    def induced_sort(N,lst,type,LMS,bucket_count,sorted_LMS_index=None):
        buckets_left=[[] for b in range(bucket_count)]
        buckets_right=[[] for b in range(bucket_count)]
        if sorted_LMS_index==None:
            for i in LMS:
                buckets_right[lst[i]].append(i)
        else:
            for i in sorted_LMS_index[::-1]:
                buckets_right[lst[LMS[i]]].append(LMS[i])
        for b in range(bucket_count):
            for i in buckets_left[b]:
                if i and type[i-1]=="L":
                    buckets_left[lst[i-1]].append(i-1)
            for i in buckets_right[b][::-1]:
                if i and type[i-1]=="L":
                    buckets_left[lst[i-1]].append(i-1)
            buckets_right[b]=[]
        for b in range(bucket_count-1,-1,-1):
            for i in buckets_right[b]:
                if i and type[i-1]=="S":
                    buckets_right[lst[i-1]].append(i-1)
            for i in buckets_left[b][::-1]:
                if i and type[i-1]=="S":
                    buckets_right[lst[i-1]].append(i-1)
        suffix_array=[N]
        for b in range(1,bucket_count):
            suffix_array+=buckets_left[b]+buckets_right[b][::-1]
        return suffix_array
    
    stack=[]
    while N>=1:
        type=[None]*(N+1)
        type[N]="S"
        for i in range(N-1,-1,-1):
            if lst[i]<lst[i+1]:
                type[i]="S"
            elif lst[i]>lst[i+1]:
                type[i]="L"
            else:
                type[i]=type[i+1]
        LMS=[i for i in range(1,N) if type[i-1]=="L" and type[i]=="S"]+[N]
        bucket_count=max(lst)+1
        stack.append((N,lst,type,LMS,bucket_count))
        suffix_array=induced_sort(N,lst,type,LMS,bucket_count)
        LMS_substring=[None]*(N+1)
        for i in range(len(LMS)-1):
            LMS_substring[LMS[i]]=lst[LMS[i]:LMS[i+1]]
        LMS_substring[N]=lst[N:N+1]
        num=0
        prev=[0]
        for i in suffix_array:
            if LMS_substring[i]!=None:
                if prev!=LMS_substring[i]:
                    prev=LMS_substring[i]
                    num+=1
                LMS_substring[i]=num
        lst=[LMS_substring[i] for i in LMS]
        N=len(lst)-1
    sorted_LMS_index=[0]
    for N,lst,type,LMS,bucket_count in stack[::-1]:
        suffix_array=induced_sort(N,lst,type,LMS,bucket_count,sorted_LMS_index)
        sorted_LMS_index=suffix_array
    suffix_array=suffix_array[1:]
    return suffix_array

def LCP(lst,suffix_array):
    N=len(lst)
    idx=[None]*N
    for i in range(N):
        idx[suffix_array[i]]=i
    lcp=[None]*(N-1)
    r=0
    for l in range(N):
        if idx[l]==N-1:
            continue
        ll=suffix_array[idx[l]+1]
        while r<l or r<N and ll+r-l<N and lst[r]==lst[ll+r-l]:
            r+=1
        lcp[idx[l]]=r-l
    return lcp

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

def Multiple_String_Search(S,T,substring=False,minimize=False):
    N=len(S)
    M=len(T)
    comp,decomp=Compress([s for s in S]+[t for m in range(M) for t in T[m]])
    lst=[]
    for s in S:
        lst.append(comp[s]+1)
    T_position=[None]*M
    lst.append(len(comp)+1)
    for m in range(M):
        T_position[m]=len(lst)
        for t in T[m]:
            lst.append(comp[t]+1)
        lst.append(0)
    T_position_idx={T_position[m]:m for m in range(M)}
    SA=SA_IS(lst,compressed=True)
    SA_idx=[None]*len(SA)
    for i in range(len(SA)):
        SA_idx[SA[i]]=i
    if minimize:
        LA=LCP(lst,suffix_array=SA)
        seen=[False]*len(lst)
        retu=[None]*N
        for m in sorted([m for m in range(M)],key=lambda m:len(T[m])):
            i=SA_idx[T_position[m]]
            lcp=len(lst)
            while i+1<len(lst) and not seen[i+1]:
                lcp=min(lcp,LA[i])
                if lcp<len(T[m]):
                    break
                i+=1
                seen[i]=True
                if SA[i]<N:
                    retu[SA[i]]=(len(T[m]),m)
    else:
        LA=Segment_Tree(len(lst)-1,min,len(lst),LCP(lst,suffix_array=SA))
        S_SA_idx=[SA_idx[i] for i in range(N)]
        T_SA_idx=[SA_idx[i] for i in T_position]
        sorted_T_SA_idx=sorted(T_SA_idx)
        retu=[(0,None)]*N
        for i in range(N):
            l=bisect.bisect_left(sorted_T_SA_idx,S_SA_idx[i])
            if l:
                ma=LA.Fold(sorted_T_SA_idx[l-1],S_SA_idx[i])
                if substring:
                    if retu[i][0]<ma:
                        retu[i]=(ma,T_position_idx[SA[sorted_T_SA_idx[l-1]]])
                else:
                    if len(T[T_position_idx[SA[sorted_T_SA_idx[l-1]]]])==ma:
                        if retu[i][0]<ma:
                            retu[i]=(ma,T_position_idx[SA[sorted_T_SA_idx[l-1]]])
            r=bisect.bisect_right(sorted_T_SA_idx,S_SA_idx[i])
            if r<len(sorted_T_SA_idx):
                if substring:
                    ma=LA.Fold(S_SA_idx[i],sorted_T_SA_idx[r])
                    if retu[i][0]<ma:
                        retu[i]=(ma,T_position_idx[SA[sorted_T_SA_idx[l-1]]])
    return retu

S=readline().rstrip()
le=len(S)
N=int(readline())
T=[readline().rstrip() for n in range(N)]
MSS=Multiple_String_Search(S,T,minimize=True)
queue=[]
for l in range(le-1,-1,-1):
    if MSS[l]!=None and (not queue or l+MSS[l][0]<=queue[-1]):
        queue.append(l)
ans=len(queue)
print(ans)