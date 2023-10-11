N=int(input())
from collections import *
class SegTree: #右開区間による実装
    def __init__(self,init_val,segfunc,ide_ele):
        n=len(init_val)
        self.segfunc=segfunc
        self.ide_ele=ide_ele
        self.num=1<<(n-1).bit_length()
        self.tree=[ide_ele]*2*self.num
        for i in range(n):
            self.tree[self.num+i]=init_val[i]
        for i in range(self.num-1,0,-1):
            self.tree[i]=self.segfunc(self.tree[2*i],self.tree[2*i+1])
    def add(self,k,x):
        k+=self.num
        self.tree[k]+=x
        k>>=1
        while k>0:
            self.tree[k]=self.segfunc(self.tree[2*k],self.tree[2*k+1])
            k>>=1
    def update(self,k,x):
        k+=self.num
        self.tree[k]=x
        k>>=1
        while k>0:
            self.tree[k]=self.segfunc(self.tree[2*k],self.tree[2*k+1])
            k>>=1
    def query(self,l,r):
        l=max(l,0)
        r=min(r,self.num)
        res=self.ide_ele
        l+=self.num
        r+=self.num
        while l<r:
            if l&1:
                res=self.segfunc(res,self.tree[l])
                l+=1
            if r&1:
                r-=1
                res=self.segfunc(res,self.tree[r])
            l>>=1
            r>>=1
        return res

xd=defaultdict(int)
Q=[]
for i in range(N):
    lr=list(map(int,input().split()))
    Q.append(lr)

for i,j in Q:
    xd[i]=0
    xd[j]=0

for i,j in enumerate(sorted(list(xd.keys()))):
    xd[j]=i
else:
    nor=SegTree([-10**18]*(i+2),max,-10**18)
    pot=SegTree([-10**18]*(i+2),max,-10**18)

for l,r in Q:
    length=r-l+1
    left=nor.query(0,xd[l])+length
    middle=pot.query(xd[l],xd[r])+r
    if left<0 and middle<0:
        g=nor.query(xd[r],xd[r]+1)
        if g<length:
            nor.update(xd[r],length)
            pot.update(xd[r],length-r)
    else:
        ml=max(left,middle)
        g=nor.query(xd[r],xd[r]+1)
        if g<ml:
            nor.update(xd[r],ml)
            pot.update(xd[r],ml-r)

print(nor.query(0,i+2))