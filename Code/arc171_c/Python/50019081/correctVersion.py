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
import numpy as np
import copy
import random
  
from collections import defaultdict

class LazySegTree_RUQ:
    def __init__(self,init_val,segfunc,ide_ele):
        n = len(init_val)
        self.segfunc = segfunc
        self.ide_ele = ide_ele
        self.num = 1<<(n-1).bit_length()
        self.tree = [ide_ele]*2*self.num
        self.lazy = [None]*2*self.num
        for i in range(n):
            self.tree[self.num+i] = init_val[i]
        for i in range(self.num-1,0,-1):
            self.tree[i] = self.segfunc(self.tree[2*i],self.tree[2*i+1])
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
            if v is None:
                continue
            self.lazy[i] = None
            self.lazy[2*i] = v
            self.lazy[2*i+1] = v
            self.tree[2*i] = v
            self.tree[2*i+1] = v
    def update(self,l,r,x):
        ids = self.gindex(l,r)
        self.propagates(*self.gindex(l,r))
        l += self.num
        r += self.num
        while l<r:
            if l&1:
                self.lazy[l] = x
                self.tree[l] = x
                l += 1
            if r&1:
                self.lazy[r-1] = x
                self.tree[r-1] = x
            r >>= 1
            l >>= 1
        for i in ids:
            self.tree[i] = self.segfunc(self.tree[2*i], self.tree[2*i+1])
    def query(self,l,r):
        ids = self.gindex(l,r)
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
  
def flws(d): # Floyd-Warshall法で各頂点i,jについてiからjへの最短経路を求める。dには(i,j)成分が辺i->jの重み(ない場合はinf,i=jなら0)になっている行列を渡す。nを頂点数としてO(n^3) 
  n=len(d)
  dwf=[d for __ in range(n+1)]
  for i in range(1,n+1):
    for j in range(n):
      for k in range(n):
        dwf[i][j][k]=min(dwf[i-1][j][k],dwf[i-1][j][i-1]+dwf[i-1][i-1][k])
  return dwf[-1]
  

def dijk(n,e,start):# n:num of vertices, e:edges are one-way, dict-form of a:[(b_i,c_i)] c_i:cost of a->b_i, 0-index vertices 
  d=[inf]*n
  d[start]=0
  q=[(0,start)]
  prev=[-1]*n
  heapq.heapify(q)
  while len(q)>0:
    xt=heapq.heappop(q)
    dx,x=xt
    for yc in e[x]:
      y,c=yc
      if d[y]>d[x]+c:
        d[y]=d[x]+c
        prev[y]=x
        heapq.heappush((d[y],y))
  return d,prev

def long_mul(a,b): #a,bはstring
  f=[int(a[max(0,len(a)-(i+1)*5):len(a)-i*5]) for i in range((len(a)-1)//5+1)]
  g=[int(b[max(0,len(b)-(i+1)*5):len(b)-i*5]) for i in range((len(b)-1)//5+1)]
  fft_len = 1
  while 2 * fft_len < len(f) + len(g):
      fft_len *= 2
  fft_len *= 2

  # フーリエ変換
  Ff = np.fft.rfft(f, fft_len)
  Fg = np.fft.rfft(g, fft_len)

  # 各点積
  Fh = Ff * Fg

  # フーリエ逆変換
  h = np.fft.irfft(Fh, fft_len)

  # 小数になっているので、整数にまるめる
  h = np.rint(h).astype(np.int64)
  for i in range(len(h)-1):
    h[i+1]+=h[i]//(10**5)
    h[i]%=(10**5)
  h=[str(hi).zfill(5) for hi in h]
  return "".join(h[::-1]).lstrip("0")

def long_mod(a,m):
  z=0
  for i in range(len(a)):
    z=(z*10+int(a[i]))%m
  return z

# factorial()
#############


# inf=1<<30


def main(n,uv):
  z=[[0]*n for _ in range(n)]
  d=[set() for _ in range(n)]
  for i in range(n-1):
    d[uv[i][0]-1].add(uv[i][1]-1)
    d[uv[i][1]-1].add(uv[i][0]-1)
  l=[len(d[i]) for i in range(n)]
  q=deque([i  for i in range(n) if l[i]==1])
  for i in range(n):
    z[i][0]=1
  last=-1
  while len(q)>0:
    x=q.popleft()
    last=x
    sx1=sum([z[x][i]*(i+1) for i in range(n)])
    sx0=sum(z[x])
    for y in d[x]:
      l[y]-=1
      if l[y]==1:
        q.append(y)
      for i in range(n-1)[::-1]:
        z[y][i+1]=z[y][i]*sx1*(i+1)+z[y][i+1]*sx0
        z[y][i+1]%=mod
      z[y][0]*=sx0
      z[y][0]%=mod
      # print(z)
  print(sum(z[last])%mod)
  # print(z)
  return
    




n,=map(int,input().split())
# x=list(map(int,input().split()))
# a=list(map(int,input().split()))
# b=list(map(int,input().split()))
# n,m,k=input().split()
# nxy=list(input().split())
# a=[input() for _ in range(n)]
# x=[int(input()) for _ in range(q)]
# s=input()
# t=input()
# cp=[input().split() for __ in range(q)]
uv=[list(map(int,input().split())) for __ in range(n-1)]
# c=list(map(int,input().split()))
# n=1000
# a=[str(random.randint(1,10**20)) for _ in range(n)]
main(n,uv)

# for _ in range(t):
#   n,a,b=map(int,input().split())
# #   a=list(map(int,input().split()))
# #   b=list(map(int,input().split()))
#   main(n,a,b)