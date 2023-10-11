import sys
sys.setrecursionlimit(10**8)
from sys import stdin
#import numba as nb
#from numba import b1, i4, i8, f8
from collections import defaultdict
from collections import Counter
from collections import deque
import heapq
#import networkx as nx
from itertools import combinations,permutations
from functools import cmp_to_key
import math 
import bisect
#import numpy as np
import copy
import random
  
from collections import defaultdict

class LazSeg:
  def __init__(self,n,func,ide,mul,add):
    self.sizelog=self.intlog2(n-1)+2
    self.size=2**(self.sizelog)-1
    self.func=func
    self.ide=ide #identity element
    self.mul=mul #1 if func==sum, 0 if func==min, max
    self.add=add #0 if replacing, 1 if adding
    self.lazide= 0 if self.add==1 else None
    self.maintree=[self.ide for _ in range(self.size)]
    self.subtree=[self.lazide for _ in range(self.size)]
  
  def is2pow(self,x):
    return x==2**(self.intlog2(x))
  
  def intlog2(self,x):
    return len(bin(x))-3
  
  def ind2seg(self,ind):
    segl=2**(self.sizelog-1-self.intlog2(ind+1))
    segb=(ind+1-2**(self.intlog2(ind+1)))*segl
    return segb,segl
  
  def seg2ind(self,segb,segl):
    ind=2**(self.sizelog-1-self.intlog2(segl))+segb//segl-1
    return ind
    
  
  def propagate(self, ind):
    if self.subtree[ind]!=self.lazide:
      if self.add==1:
        self.maintree[ind]+=self.subtree[ind]
      else:
        self.maintree[ind]=self.subtree[ind]
      if 2*ind+2<self.size:
        for i in range(2):
          if self.add==1:
            self.subtree[2*ind+1+i]+=self.subtree[ind]//(2**(self.mul))
          else:
            self.subtree[2*ind+1+i]=self.subtree[ind]//(2**(self.mul))
      self.subtree[ind]=self.lazide
  
  def propagate_root(self,ind):
    l=[ind]
    z=ind
    while z>0:
      l.append((z-1)//2)
      z=(z-1)//2
    while len(l)>0:
      q=l.pop()
      self.propagate(q)
  
  def segmentize(self,a,b):
    v=[]
    while b>a:
      bb=min(2**self.intlog2(b-a),((b^(b-1))+1)//2)
      v.append((b-bb,bb))
      b-=bb
    return v
  
  def update(self,a,b,x):
    v=self.segmentize(a,b)
    if len(v)==0:
      pass
    elif len(v)==1:
      ind=self.seg2ind(a,b-a)
      self.propagate_root(ind)
      self.subtree[ind]=x*((b-a)**self.mul)
      while ind>0:
        ind=(ind-1)//2
        self.maintree[ind]=self.func(self.maintree[ind*2+1]*self.add+self.subtree[ind*2+1],\
                                     self.maintree[ind*2+2]*self.add+self.subtree[ind*2+2])
    else:
      for vi in v:
        self.update(vi[0],vi[0]+vi[1],x)     
      
  def query(self,a,b):
    v=self.segmentize(a,b)
    if len(v)==0:
      return self.ide
    elif len(v)==1:
      ind=self.seg2ind(v[0][0],v[0][1])
      self.propagate_root(ind)
      return self.maintree[ind]
    else:
      ind=self.seg2ind(v[0][0],v[0][1])
      self.propagate_root(ind)
      m=self.maintree[ind]
      for vi in v[1:]:
        ind=self.seg2ind(vi[0],vi[1])
        self.propagate_root(ind)
        m=self.func(m,self.maintree[ind])
      return m

class UnionFind():
    def __init__(self, n):
        self.n = n
        self.parents = [-1] * n

    def find(self, x):
        if self.parents[x] < 0:
            return x
        else:
            self.parents[x] = self.find(self.parents[x])
            return self.parents[x]

    def union(self, x, y):
        x = self.find(x)
        y = self.find(y)

        if x == y:
            return

        if self.parents[x] > self.parents[y]:
            x, y = y, x

        self.parents[x] += self.parents[y]
        self.parents[y] = x

    def size(self, x):
        return -self.parents[self.find(x)]

    def same(self, x, y):
        return self.find(x) == self.find(y)

    def members(self, x):
        root = self.find(x)
        return [i for i in range(self.n) if self.find(i) == root]

    def roots(self):
        return [i for i, x in enumerate(self.parents) if x < 0]

    def group_count(self):
        return len(self.roots())

    def all_group_members(self):
        group_members = defaultdict(list)
        for member in range(self.n):
            group_members[self.find(member)].append(member)
        return group_members

    def __str__(self):
        return '\n'.join(f'{r}: {m}' for r, m in self.all_group_members().items())

def z_alg(s):
  l=len(s)
  d=[0]*(l+1)
  d[0]=l
  i=1
  while i<l:
    j=d[i]
    while i+j<l and s[i+j]==s[j]:
      j+=1
    d[i]=j
    k=1
    while d[k]<j-k:
      d[i+k]=d[k]
      k+=1
    d[i+k]=max(0,j-k)
    i+=k
  return d

class Bit:
    def __init__(self, n):
        self.size = n
        self.tree = [0] * (n + 1)
 
    def sum(self, i):
        s = 0
        while i > 0:
            s += self.tree[i]
            i -= i & -i
        return s
 
    def add(self, i, x):
        while i <= self.size:
            self.tree[i] += x
            i += i & -i
            
def prime_f(n):
  k=2
  z=defaultdict(int)
  while n>k:
    if n%k==0:
      z[k]+=1
      n//=k
    else:
      k+=1
  if n>1:
    z[n]+=1
  return z            

def pr_fac(n):
    counter = Counter()
    p=2
    while p**2<n:
        while n % p == 0:
            counter[p] += 1
            n //= p
        p+=1
    if n != 1:
        counter[n] += 1
    s=[]
    for key, value in counter.items():
        for i in range(value):
            s.append(key)
    return s 

def base_change(n,nb,ob=10):
  n10=int(str(n),ob)
  dig="0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ"
  q=[]
  while n10>0:
    q.append(dig[n10%nb])
    n10//=nb
  return "".join(q[::-1])
  
mod=998244353
lim=15*10**5
fact=[1,1]
inv=[0,1]
factinv=[1,1]
alp="abcdefghijklmnopqrstuvwxyz"

def factorial():
  for i in range(2,lim):
    fact.append((fact[-1]*i)%mod)
    inv.append((-(mod//i)*inv[mod%i])%mod)
    factinv.append((factinv[-1]*inv[-1])%mod)
  return
  
def binom(a,b):
  if 0<=b<=a:
    return (fact[a]*factinv[b]*factinv[a-b])%mod
  else:
    return 0
  
#factorial()
#############



def main(n,t):
  d={"+":1,"-":-1,"?":0}
  dp=[0]*(2*(n+1)*(2*n+1))
  dp[0]=1
  for i in range(1,2*n+1):
    l=d[t[i-1]]
    for j in range(max(0,i-n),min(i,n)+1):
      b10=dp[(i-1)*2*(n+1)+(j-1)*2]
      b11=dp[(i-1)*2*(n+1)+(j-1)*2+1]
      b00=dp[(i-1)*2*(n+1)+(j)*2]
      b01=dp[(i-1)*2*(n+1)+(j)*2+1]
      if l>=0 and j>0:
        dp[i*2*(n+1)+j*2]+=b10
        if j>i-j:
          dp[i*2*(n+1)+j*2+1]+=(b11-b10*i)%mod
        else:
          dp[i*2*(n+1)+j*2+1]+=(b11+b10*i)%mod
      if l<=0 and j<i:
        dp[i*2*(n+1)+j*2]+=b00
        if i-j>j:
          dp[i*2*(n+1)+j*2+1]+=(b01-b00*i)%mod
        else:
          dp[i*2*(n+1)+j*2+1]+=(b01+b00*i)%mod
      dp[i*2*(n+1)+j*2]%=mod
      dp[i*2*(n+1)+j*2+1]%=mod
  print(dp[-1])
  return
                  
                  
n,=map(int,input().split())
#p=list(map(int,input().split()))
t=input()
#l=list(map(int,input().split()))
#d=list(map(int,input().split()))
#q,=map(int,input().split())
#ab=[]
#for _ in range(n):
#  n,k=map(int,input().split())
#  s=input()
#  main(n,k)
#  s.append(input())
#  ab.append(list(map(int,input().split())))  
#  xy.append(list(map(int,input().split())))
#  a=list(map(int,input().split()))
#  main(n,k,p,a)
#s=[]
#for i in range(h):
#  s.append(input())
#  uv.append(list(map(int,input().split())))
#k,=map(int,input().split())
#xy=[]
#for i in range(k):
#  xy.append(list(map(int,input().split())))
#q,=map(int,input().split())
#pq=[]
#for i in range(q):
#  pq.append(list(map(int,input().split())))
#a=list(map(int,input().split()))
#c=list(map(int,input().split()))
#for i in range(m):
#  n,=map(int,input().split())
#  s=input()
#  main(n,k)
#s=input()
#t=input()
#pq=[]
#a=[]
#for _ in range(n):
#  si,ai=input().split()
#  s.append(si)
#  a.append(int(ai))
#  pq.append(list(map(int,input().split())))
#an=int(input())
#a=list(map(int,input().split()))
#bn=int(input())
#b=list(map(int,input().split()))
#for i in range(n-1):
#  uv.append(list(map(int,input().split())))
#  a.append(int(input()))
#  s.append(set(map(int,input().split())))
#  s.append(input())
#s=input()
n=3000
t="?"*(2*n)
main(n,t)
