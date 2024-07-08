from bisect import bisect,bisect_left

from collections import *
from heapq import *
from math import gcd,ceil,sqrt,floor,inf,pi

from itertools import *
from operator import add,mul,sub,xor,truediv,floordiv
from functools import *


#----------------------------------------------------------------------
import os
import sys

from io import BytesIO, IOBase
# region fastio
 
BUFSIZE = 8192
 
class FastIO(IOBase):
    newlines = 0
 
    def __init__(self, file):
        self._fd = file.fileno()
        self.buffer = BytesIO()
        self.writable = "x" in file.mode or "r" not in file.mode
        self.write = self.buffer.write if self.writable else None
 
    def read(self):
        while True:
            b = os.read(self._fd, max(os.fstat(self._fd).st_size, BUFSIZE))
            if not b:
                break
            ptr = self.buffer.tell()
            self.buffer.seek(0, 2), self.buffer.write(b), self.buffer.seek(ptr)
        self.newlines = 0
        return self.buffer.read()
 
    def readline(self):
        while self.newlines == 0:
            b = os.read(self._fd, max(os.fstat(self._fd).st_size, BUFSIZE))
            self.newlines = b.count(b"\n") + (not b)
            ptr = self.buffer.tell()
            self.buffer.seek(0, 2), self.buffer.write(b), self.buffer.seek(ptr)
        self.newlines -= 1
        return self.buffer.readline()
 
    def flush(self):
        if self.writable:
            os.write(self._fd, self.buffer.getvalue())
            self.buffer.truncate(0), self.buffer.seek(0)
 
 
class IOWrapper(IOBase):
    def __init__(self, file):
        self.buffer = FastIO(file)
        self.flush = self.buffer.flush
        self.writable = self.buffer.writable
        self.write = lambda s: self.buffer.write(s.encode("ascii"))
        self.read = lambda: self.buffer.read().decode("ascii")
        self.readline = lambda: self.buffer.readline().decode("ascii")
 
 
sys.stdin, sys.stdout = IOWrapper(sys.stdin), IOWrapper(sys.stdout)
input = lambda: sys.stdin.readline().rstrip("\r\n")
 

#------------------------------------------------------------------------
def RL(): return map(int, sys.stdin.readline().split())
def RLL(): return list(map(int, sys.stdin.readline().split()))
def N(): return int(input())
def A(n):return [0]*n
def AI(n,x): return [x]*n
def A2(n,m): return [[0]*m for i in range(n)]
def G(n): return [[] for i in range(n)]
def GP(it): return [[ch,len(list(g))] for ch,g in groupby(it)]
#------------------------------------------------------------------------


from types import GeneratorType
 
 
def bootstrap(f, stack=[]):
    def wrappedfunc(*args, **kwargs):
        if stack:
            return f(*args, **kwargs)
        else:
            to = f(*args, **kwargs)
            while True:
                if type(to) is GeneratorType:
                    stack.append(to)
                    to = next(to)
                else:
                    stack.pop()
                    if not stack:
                        break
                    to = stack[-1].send(to)
            return to
 
    return wrappedfunc

mod=10**9+7
farr=[1]
ifa=[]
 
def fact(x,mod=0):
    if mod:
        while x>=len(farr):
            farr.append(farr[-1]*len(farr)%mod)
    else:
        while x>=len(farr):
            farr.append(farr[-1]*len(farr))
    return farr[x]
 
def ifact(x,mod):
    global ifa
    fact(x,mod)
    ifa.append(pow(farr[-1],mod-2,mod))
    for i in range(x,0,-1):
        ifa.append(ifa[-1]*i%mod)
    ifa.reverse()
 
def per(i,j,mod=0):
    if i<j: return 0
    if not mod:
        return fact(i)//fact(i-j)
    return farr[i]*ifa[i-j]%mod
    
def com(i,j,mod=0):
    if i<j: return 0
    if not mod:        
        return per(i,j)//fact(j)
    return per(i,j,mod)*ifa[j]%mod
 
def catalan(n):
    return com(2*n,n)//(n+1)
 
def isprime(n):
    for i in range(2,int(n**0.5)+1):
        if n%i==0:
            return False
    return True

def floorsum(a,b,c,n):#sum((a*i+b)//c for i in range(n+1))
    if a==0:return b//c*(n+1)
    if a>=c or b>=c: return floorsum(a%c,b%c,c,n)+b//c*(n+1)+a//c*n*(n+1)//2
    m=(a*n+b)//c
    return n*m-floorsum(c,c-b-1,a,m-1)

def inverse(a,m):
    a%=m
    if a<=1: return a
    return ((1-inverse(m,a)*m)//a)%m

# x.bit_count()
def popcnt(x):
    ans=0
    while x:
        ans+=1
        x&=x-1
    return ans
 
def lowbit(n):
    return n&-n
 
class BIT:
    def __init__(self,arr):
        self.arr=arr
        self.n=len(arr)-1
        
    def update(self,x,v):
        while x<=self.n:
            self.arr[x]+=v
            x+=x&-x
 
    def query(self,x):
        ans=0
        while x:
            ans+=self.arr[x]
            x&=x-1
        return ans

class ST:
    def __init__(self,arr):#n!=0
        n=len(arr)
        mx=n.bit_length()#取不到
        self.st=[[0]*mx for i in range(n)]
        for i in range(n):
            self.st[i][0]=arr[i]
        for j in range(1,mx):
            for i in range(n-(1<<j)+1):
                self.st[i][j]=max(self.st[i][j-1],self.st[i+(1<<j-1)][j-1])
    def query(self,l,r):
        if l>r:return -inf
        s=(r+1-l).bit_length()-1
        return max(self.st[l][s],self.st[r-(1<<s)+1][s])
        
class DSU:#容量+路径压缩
    def __init__(self,n):
        self.c=[-1]*n
 
    def same(self,x,y):
        return self.find(x)==self.find(y)
 
    def find(self,x):
        if self.c[x]<0:
            return x
        self.c[x]=self.find(self.c[x])
        return self.c[x]
 
    def union(self,u,v):
        u,v=self.find(u),self.find(v)
        if u==v:
            return False
        if self.c[u]>self.c[v]:
            u,v=v,u
        self.c[u]+=self.c[v]
        self.c[v]=u
        return True
 
    def size(self,x): return -self.c[self.find(x)]
    
class UFS:#秩+路径
    def __init__(self,n):
        self.parent=[i for i in range(n)]
        self.ranks=[0]*n
 
    def find(self,x):
        if x!=self.parent[x]:
            self.parent[x]=self.find(self.parent[x])
        return self.parent[x]
 
    def union(self,u,v):
        pu,pv=self.find(u),self.find(v)
        if pu==pv:
            return False
        if self.ranks[pu]>=self.ranks[pv]:
            self.parent[pv]=pu
            if self.ranks[pv]==self.ranks[pu]:
                self.ranks[pu]+=1
        else:
            self.parent[pu]=pv

class UF:#秩+路径+容量，边数
    def __init__(self,n):
        self.parent=[i for i in range(n)]
        self.ranks=[0]*n
        self.size=AI(n,1)
        self.edge=A(n)
 
    def find(self,x):
        if x!=self.parent[x]:
            self.parent[x]=self.find(self.parent[x])
        return self.parent[x]
 
    def union(self,u,v):
        pu,pv=self.find(u),self.find(v)
        if pu==pv:
            self.edge[pu]+=1
            return False
        if self.ranks[pu]>=self.ranks[pv]:
            self.parent[pv]=pu
            self.edge[pu]+=self.edge[pv]+1
            self.size[pu]+=self.size[pv]
            if self.ranks[pv]==self.ranks[pu]:
                self.ranks[pu]+=1
        else:
            self.parent[pu]=pv
            self.edge[pv]+=self.edge[pu]+1
            self.size[pv]+=self.size[pu]
 
def Prime(n):
    c=0
    prime=[]
    flag=[0]*(n+1) 
    for i in range(2,n+1):
        if not flag[i]:
            prime.append(i)
            c+=1
        for j in range(c):
            if i*prime[j]>n: break
            flag[i*prime[j]]=prime[j]
            if i%prime[j]==0: break
    return flag
 
def dij(s,graph):
    d=AI(n,inf)
    d[s]=0
    heap=[(0,s)]
    while heap:
        dis,u=heappop(heap)
        if dis>d[u]:
            continue
        for v,w in graph[u]:
            if d[v]>d[u]+w:
                d[v]=d[u]+w
                heappush(heap,(d[v],v))
    return d

def bell(s,g):#bellman-Ford
    dis=AI(n,inf)
    dis[s]=0
    for i in range(n-1):
        for u,v,w in edge:
            if dis[v]>dis[u]+w:
                dis[v]=dis[u]+w
    change=A(n)
    for i in range(n):
        for u,v,w in edge:
            if dis[v]>dis[u]+w:
                dis[v]=dis[u]+w
                change[v]=1
    return dis

def lcm(a,b): return a*b//gcd(a,b)
def lis(nums):
    res=[]
    for k in nums:
        i=bisect_left(res,k)
        if i==len(res):
            res.append(k)
        else:
            res[i]=k
    return len(res)

def RP(nums):#逆序对
    n = len(nums)
    s=set(nums)
    d={}
    for i,k in enumerate(sorted(s),1):
        d[k]=i
    bi=BIT([0]*(len(s)+1))
    ans=0
    for i in range(n-1,-1,-1):
        ans+=bi.query(d[nums[i]]-1)
        bi.update(d[nums[i]],1)
    return ans

def michange(a,b):
    d=defaultdict(deque)
    for i,x in enumerate(b):
        d[x].append(i)
    order=A(len(a))
    for i,x in enumerate(a):
        if not d:
            return -1
        order[i]=d[x].popleft()
    return RP(order)

class DLN:
    def __init__(self,val):
        self.val=val
        self.pre=None
        self.next=None

def nb(i,j,n,m):
    for ni,nj in [[i+1,j],[i-1,j],[i,j-1],[i,j+1]]:
        if 0<=ni<n and 0<=nj<m:
            yield ni,nj

def topo(n):
    q=deque()
    res=[]
    for i in range(1,n+1):
        if ind[i]==0:
            q.append(i)
            res.append(i)
    while q:
        u=q.popleft()
        for v in g[u]:
            ind[v]-=1
            if ind[v]==0:
                q.append(v)
                res.append(v)
    return res

@bootstrap
def gdfs(r,p):
    for ch in g[r]:
        if ch!=p:
            yield gdfs(ch,r)
    yield None


'''
from random import randint,shuffle
def ra(n,a,b):
    return [randint(a,b) for i in range(n)]

def check():
    vis=0
    arr=[]
    dfs(0,arr)


'''


mod=998244353

t=1
for i in range(t):
    n=N()
    l=A(n)
    r=A(n)
    for i in range(n):
        l[i],r[i]=RL()
    up=A(n)
    dw=A(n)
    up[-1],dw[-1]=r[-1],l[-1]
    for i in range(n-2,-1,-1):
        if r[i]<dw[i+1]:
            dw[i]=up[i]=r[i]
        elif l[i]>up[i+1]:
            dw[i]=up[i]=l[i]
        else:
            dw[i]=max(dw[i+1],l[i])
            up[i]=min(r[i],up[i+1])
    ans=A(n)
    ans[0]=dw[0]
    for i in range(1,n):
        opt=min(ans[i-1],up[i])
        ans[i]=max(dw[i],opt)
    print(*ans)
                

    
'''
sys.setrecursionlimit(200000)
import threading
threading.sta1ck_size(10**8)
t=threading.Thr
ead(target=main)
t.start()
t.join()

2
1 1
1 1
1
1
1 1

'''

