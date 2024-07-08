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
      


class LazySegTree_RAQ: #0-index
    def __init__(self,init_val,segfunc,ide_ele):
        n = len(init_val)
        self.segfunc = segfunc
        self.ide_ele = ide_ele
        self.num = 1<<(n-1).bit_length()
        self.tree = [ide_ele]*2*self.num
        self.lazy = [0]*2*self.num
        for i in range(n):
            self.tree[self.num+i] = init_val[i]
        for i in range(self.num-1,0,-1):
            self.tree[i] = self.segfunc(self.tree[2*i], self.tree[2*i+1])
    def gindex(self,l,r):
        l += self.num
        r += self.num
        lm = l>>(l&-l).bit_length()
        rm = r>>(r&-r).bit_length()
        while r>l:
            if l<=lm:
                yield l
            if r<=rm:
                yield r
            r >>= 1
            l >>= 1
        while l:
            yield l
            l >>= 1
    def propagates(self,*ids):
        for i in reversed(ids):
            v = self.lazy[i]
            if v==0:
                continue
            self.lazy[i] = 0
            self.lazy[2*i] += v
            self.lazy[2*i+1] += v
            self.tree[2*i] += v
            self.tree[2*i+1] += v
    def add(self,l,r,x):
        ids = self.gindex(l,r)
        l += self.num
        r += self.num
        while l<r:
            if l&1:
                self.lazy[l] += x
                self.tree[l] += x
                l += 1
            if r&1:
                self.lazy[r-1] += x
                self.tree[r-1] += x
            r >>= 1
            l >>= 1
        for i in ids:
            self.tree[i] = self.segfunc(self.tree[2*i], self.tree[2*i+1]) + self.lazy[i]
    def query(self,l,r):
        self.propagates(*self.gindex(l,r))
        res = self.ide_ele
        l += self.num
        r += self.num
        while l<r:
            if l&1:
                res = self.segfunc(res,self.tree[l])
                l += 1
            if r&1:
                res = self.segfunc(res,self.tree[r-1])
            l >>= 1
            r >>= 1
        return res
        
class SegTree:
    """
    init(init_val, ide_ele): 配列init_valで初期化 O(N)
    update(k, x): k番目の値をxに更新 O(N)
    query(l, r): 区間[l, r)をsegfuncしたものを返す O(logN)
    """
    def __init__(self, init_val, segfunc, ide_ele):
        """
        init_val: 配列の初期値
        segfunc: 区間にしたい操作
        ide_ele: 単位元
        n: 要素数
        num: n以上の最小の2のべき乗
        tree: セグメント木(1-index)
        """
        n = len(init_val)
        self.segfunc = segfunc
        self.ide_ele = ide_ele
        self.num = 1 << (n - 1).bit_length()
        self.tree = [ide_ele] * 2 * self.num
        # 配列の値を葉にセット
        for i in range(n):
            self.tree[self.num + i] = init_val[i]
        # 構築していく
        for i in range(self.num - 1, 0, -1):
            self.tree[i] = self.segfunc(self.tree[2 * i], self.tree[2 * i + 1])

    def update(self, k, x):
        """
        k番目の値をxに更新
        k: index(0-index)
        x: update value
        """
        k += self.num
        self.tree[k] = x
        while k > 1:
            self.tree[k >> 1] = self.segfunc(self.tree[k], self.tree[k ^ 1])
            k >>= 1

    def query(self, l, r):
        """
        [l, r)のsegfuncしたものを得る
        l: index(0-index)
        r: index(0-index)
        """
        res = self.ide_ele

        l += self.num
        r += self.num
        while l < r:
            if l & 1:
                res = self.segfunc(res, self.tree[l])
                l += 1
            if r & 1:
                res = self.segfunc(res, self.tree[r - 1])
            l >>= 1
            r >>= 1
        return res
        
class UnionFind():# 0-index
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

class Bit:# 1-index
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
  while n>=k**2:
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
  if n10==0:
    return "0"
  while n10>0:
    q.append(dig[n10%nb])
    n10//=nb
  return "".join(q[::-1])
  
mod=998244353
inf=1<<60
lim=3*10**6
fact=[1,1]
inv=[0,1]
factinv=[1,1]
alp="abcdefghijklmnopqrstuvwxyz"
dalp={alp[i]:i for i in range(26)}

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

def cumsum(l):
  c=[0]
  for li in l:
    c.append(c[-1]+li)
  return c
  
#def bfs(edges,cost,vertices,start):# edges are one-way,
#  d={i:[] for i in vertices}
#  for i in range(len(edges)):
#    edges
  
  
  
factorial()
#############


    
  

def main(n,a,b):
  a=sorted(a)
  b=sorted(b)
  d=[[] for _ in range(n)]
  for i in range(n):
    r=bisect.bisect(a,b[i])
    d[i]=sorted(range(max(r-3,0),min(r+3,n)),key=lambda x:abs(b[i]-a[x]))[:2]
  mx=max([abs(b[i]-a[d[i][0]]) for i in range(n)])
  m=[mx]*n
  for i in range(n):
    m[d[i][0]]=max(m[d[i][0]],abs(b[i]-a[d[i][1]]))
  # print(m)
  for i in range(n):  
    yr=bisect.bisect_left(a,b[0]+a[i])
    yl=bisect.bisect_right(a,a[i]-b[0])
    if  (b[0]<a[i] and ((yl<yr and yl!=i) or  yl<yr-1)) and a[i]>m[i]:
      print("Alice")
      return
  print("Bob")
  return
    




t,=map(int,input().split())
# s=list(map(int,input().split()))
# p=list(map(int,input().split()))
# n,m,k=input().split()
# nxy=list(input().split())
# s=[input() for _ in range(h)]
# x=[int(input()) for _ in range(q)]
# s=input()
# t=input()
# cp=[input().split() for __ in range(q)]
# uv=[list(map(int,input().split())) for __ in range(m)]
# c=list(map(int,input().split()))
# main(n,m,s)

for _ in range(t):
  n,=map(int,input().split())
  a=list(map(int,input().split()))
  b=list(map(int,input().split()))
  main(n,a,b)