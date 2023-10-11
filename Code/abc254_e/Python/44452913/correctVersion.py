# coding: utf-8
#import
import random
import os
import operator
from operator import add,sub,mul,xor,or_,and_
import time
import sys
import re
import string
import math
from fractions import Fraction
import inspect
from math import sqrt,ceil,floor,gcd,log,log2
import collections
from collections import defaultdict,deque,Counter
from fractions import Fraction
import bisect
from bisect import bisect_left,bisect_right
import itertools
from itertools import accumulate,permutations
import functools
from functools import cmp_to_key
from copy import deepcopy,copy
import heapq
from heapq import heappush,heappop,heapify
from functools import lru_cache,reduce
readline=sys.stdin.buffer.readline
sys.setrecursionlimit(10**8)
inf=int(10**18)
#データ構造
class Edge:
    def __init__(self,x,y,cost=1):
        self.x=x
        self.y=y
        self.cost=cost
class UnionFind:
    def __init__(self,n):
        self.n=n
        self.size_=n
        self.P=[-1 for i in range(n)]#根ノードの場合:ノード数×-1。それ以外：親ノードのindex
    def root(self,x):
        if self.P[x]>=0:
            self.P[x]=self.root(self.P[x])
            return self.P[x]
        else:
            return x
    def same(self,x,y):
        return self.root(x)==self.root(y)
    def union(self,x,y):
        x=self.root(x)
        y=self.root(y)
        if x==y:return
        if -self.P[x]<-self.P[y]:x,y=y,x
        self.P[x]+=self.P[y]
        self.P[y]=x
        self.size_-=1
    def unionSize(self,x):
        return -self.P[self.root(x)]#P[根ノード]=同じ集合のノード数×-1
    def roots(self):
        ret=set()
        for i in range(self.n):
            ret.add(self.root(i))
        return list(ret)
    def getMembersByRoot(self):
        ret=defaultdict(list)
        for u in range(self.n):
            root=self.root(u)
            ret[root].append(u)
        return ret
    def size(self):
        return self.size_
    def __str__(self):
        ret=[]
        for i in range(self.n):
            ret.append((i,self.root(i)))
        ret=str(ret)
        return ret
class BinaryTrie:
    def __init__(self,depth):
        self.root=[None,None,0] #0-child 1-child count
        self.bit_start=1<<(depth-1)
    def insert(self,x):
        node=self.root
        b=self.bit_start
        while b:
            i=bool(x&b)
            if node[i]==None:
                node[i]=[None,None,1]
            else:
                node[i][2]+=1
            node=node[i]
            b>>=1
    def pop_min(self,xor_mask=0):
        node=self.root
        b=self.bit_start
        ret=0
        node[2]-=1
        while b:
            i=bool(b&xor_mask)
            ret<<=1
            if node[i]==None:
                i^=1
                ret+=1
            node=node[i]
            b >>= 1

            if node[i][2] > 1:
                node[i][2] -= 1
                node = node[i]
            else:
                tmp = node[i]
                node[i] = None
                node = tmp
        return ret
    def get_min(self,xor_mask=0):
        node=self.root
        b=self.bit_start
        ret=0
        while b:
            i=bool(b&xor_mask)
            ret<<=1
            if node[i]==None:
                i^=1
                ret+=1
            node=node[i]
            b>>=1
        return ret
    def get_kth_min(self,k):
        b = self.bit_start
        node = self.root
        ret = 0
        while b:
            ret = ret << 1
            b >>= 1
            if node[0] is None:
                node = node[1]
                ret += 1
            elif node[1] is None:
                node = node[0]
            elif k <= node[0][2]:
                node = node[0]
            else:
                k -= node[0][2]
                node = node[1]
                ret += 1
        return ret
    def erase(self, x):
        b = self.bit_start
        node = self.root
        node[2] -= 1
        while b:
            i = bool(x & b)
            if node[i][2] > 1:
                node[i][2] -= 1
                node = node[i]
            else:
                tmp = node[i]
                node[i] = None
                node = tmp
            b >>= 1
    def lower_bound(self, bound=0):
        ret = self.get_kth_min(self.less_x(bound)+1)
        if ret > bound:return ret
    def upper_bound(self, bound=0):
        ret = self.get_kth_min(self.less_x(bound+1)+1)
        if ret < bound:return ret
    def less_x(self, x):
        if x < 0:return 0
        b = self.bit_start
        node = self.root
        ret = 0
        while b:
            i = bool(x & b)
            if node[i] is None:
                if i == 1:
                    ret += node[0][2]
                return ret
            else:
                if i == 1:
                    if node[0] is not None:
                        ret += node[0][2]
            node = node[i]
            b >>= 1
        return ret
    def is_exist(self, x):
        b = self.bit_start
        node = self.root
        while b:
            i = bool(x & b)
            if node[i] is None:
                return False
            node = node[i]
            b >>= 1
        return True
class SegTree:#[l,r) query(i),query(i,i+1)がO(1)
    def __init__(self, n, ide_ele, func):
        #要素数 簡単のため2の冪乗にする
        self.n=1
        while self.n<n:self.n*=2
        self.func = func
        self.ide_ele = ide_ele
        self.bottomStartPos = 1 << (n - 1).bit_length()
        self.tree = [ide_ele] * 2 * self.bottomStartPos #1-indexd
        """初期値に配列を渡す実装の場合
        for i in range(self.bottomStartPos - 1, 0, -1):
            self.tree[i] = self.func(self.tree[2 * i], self.tree[2 * i + 1])
        """

    def update(self, k, x):
        k += self.bottomStartPos
        self.tree[k] = x
        while k > 1:
            self.tree[k//2] = self.func(self.tree[k], self.tree[k ^ 1])
            k//=2

    def query(self, L,R=None):
        if R==None or L+1==R:return self.tree[self.bottomStartPos+L]
        L=max(0,L)
        R=min(self.n,R)
        #非再帰
        res = self.ide_ele
        L += self.bottomStartPos
        R += self.bottomStartPos
        while L < R:
            if L & 1:
                res = self.func(res, self.tree[L])
                L += 1
            if R & 1:
                res = self.func(res, self.tree[R - 1])
            L//=2
            R//=2
        return res
    
    def __str__(self):
        return str(self.tree[self.bottomStartPos:])
class Heap:#heapqと違い要素の削除ができる
    def __init__(self,List=[],selfInvFunc=lambda x:x):
        self.h=[]
        self.d=defaultdict(int)
        self.len=0
        self.selfInvFunc=selfInvFunc #自己逆関数
        for l in List:self.add(l)
    
    def __len__(self):
        return self.len
    
    def __str__(self):
        return str(sorted(self.getValues()))
    
    def add(self,val):
        val=self.selfInvFunc(val)
        heapq.heappush(self.h,val)
        self.d[val]+=1
        self.len+=1
    
    def __cleanup(self):
        top=self.h[0]
        while self.d[top]==0:
            heappop(self.h)
            top=self.h[0]
    
    def pop(self):
        self.__cleanup()
        top=heappop(self.h)
        self.remove(self.selfInvFunc(top))
        return self.selfInvFunc(top)
    
    def remove(self,val):
        val=self.selfInvFunc(val)
        if val not in self.d or self.d[val]==0:
            raise ValueError(f"{self.selfInvFunc(val)} is not in Heap")
        else:
            self.d[val]-=1
            self.len-=1
    
    def exists(self,val):
        val=self.selfInvFunc(val)
        return val in self.d and self.d[val]!=0

    def top(self):
        self.__cleanup()
        val=self.h[0]
        return self.selfInvFunc(val)
    
    def getValues(self):
        return [self.selfInvFunc(val) for val,cnt in self.d.items() for _ in range(cnt)]
class LazyRMQ:#[l,r)
    def __init__(self, n, ide_ele, maximum=True):#Falseならminimum
        #要素数 簡単のため2の冪乗にする
        self.n=1
        while self.n<n:self.n*=2
        self.func = lambda x,y:max(x,y) if maximum else lambda x,y:min(x,y)
        self.ide_ele = ide_ele
        self.bottomStartPos = 1 << (n - 1).bit_length()
        self.tree = [ide_ele] * 2 * self.bottomStartPos #1-indexd
        self.lazy = [ide_ele] * 2 * self.bottomStartPos #1-indexd
    
    def eval(self,k):
        if self.lazy[k]==self.ide_ele:return
        if k<self.bottomStartPos:#葉ではないか
            self.lazy[k*2]=self.lazy[k]
            self.lazy[k*2+1]=self.lazy[k]
        self.tree[k]=self.lazy[k]
        self.lazy[k]=self.ide_ele
    
    def update(self,L,R,x,k=None,l=None,r=None):
        L=max(0,L)
        R=min(self.n,R)
        if k==None:return self.update(L,R,x,1,0,self.n)
        self.eval(k)
        if L<=l and r<=R:#範囲内
            self.lazy[k]=x
            self.eval(k)
        elif L<r and l<R:#
            self.update(L,R,x,k*2,l,(l+r)//2)
            self.update(L,R,x,k*2+1,(l+r)//2,r)
            self.tree[k]=self.func(self.tree[k*2],self.tree[k*2+1])
        else:#範囲外
            pass

    def query(self, L,R,k=None,l=None,r=None):
        L=max(0,L)
        R=min(self.n,R)
        #再帰
        if k==None:return self.query(L,R,1,0,self.n)
        self.eval(k)
        if r<=L or R<=l:#範囲外
            return self.ide_ele
        elif L<=l and r<=R:#範囲内
            return self.tree[k]
        else:
            leftVal=self.query(L,R,k*2,l,(l+r)//2)
            rightVal=self.query(L,R,k*2+1,(l+r)//2,r)
            return self.func(leftVal,rightVal)
class LazyRangeUpdateQuery:#[l,r)
    def __init__(self, n, ide_ele):#
        #要素数 簡単のため2の冪乗にする
        self.n=1
        while self.n<n:self.n*=2
        self.ide_ele = ide_ele
        self.bottomStartPos = 1 << (n - 1).bit_length()
        self.tree = [ide_ele] * 2 * self.bottomStartPos #1-indexd
        self.lazy = [ide_ele] * 2 * self.bottomStartPos #1-indexd
    
    def eval(self,k):
        if self.lazy[k]==self.ide_ele:return
        if k<self.bottomStartPos:#葉ではないか
            self.lazy[k*2]=self.lazy[k]//2
            self.lazy[k*2+1]=self.lazy[k]//2
        self.tree[k]=self.lazy[k]
        self.lazy[k]=self.ide_ele
    
    def update(self,L,R,x,k=None,l=None,r=None):
        L=max(0,L)
        R=min(self.n,R)
        if k==None:return self.update(L,R,x,1,0,self.n)
        self.eval(k)
        if L<=l and r<=R:#範囲内
            self.lazy[k]=x*(r-l)
            self.eval(k)
        elif L<r and l<R:
            self.update(L,R,x,k*2,l,(l+r)//2)
            self.update(L,R,x,k*2+1,(l+r)//2,r)
            self.tree[k]=self.tree[k*2]+self.tree[k*2+1]
        else:#範囲外
            pass

    def query(self, L,R,k=None,l=None,r=None):
        L=max(0,L)
        R=min(self.n,R)
        #再帰
        if k==None:return self.query(L,R,1,0,self.n)
        self.eval(k)
        if r<=L or R<=l:#範囲外
            return self.ide_ele
        elif L<=l and r<=R:#範囲内
            return self.tree[k]
        else:
            leftVal=self.query(L,R,k*2,l,(l+r)//2)
            rightVal=self.query(L,R,k*2+1,(l+r)//2,r)
            return leftVal+rightVal
class LazyRangeAddQuery:#[l,r)
    def __init__(self, n, ide_ele):#
        #要素数 簡単のため2の冪乗にする
        self.n=1
        while self.n<n:self.n*=2
        self.ide_ele = ide_ele
        self.bottomStartPos = 1 << (n - 1).bit_length()
        self.tree = [ide_ele] * 2 * self.bottomStartPos #1-indexd
        self.lazy = [ide_ele] * 2 * self.bottomStartPos #1-indexd
    
    def eval(self,k):
        if self.lazy[k]==self.ide_ele:return
        if k<self.bottomStartPos:#葉ではないか
            self.lazy[k*2]+=self.lazy[k]//2
            self.lazy[k*2+1]+=self.lazy[k]//2
        self.tree[k]+=self.lazy[k]
        self.lazy[k]=self.ide_ele
    
    def add(self,L,R,x,k=None,l=None,r=None):
        L=max(0,L)
        R=min(self.n,R)
        if k==None:return self.add(L,R,x,1,0,self.n)
        self.eval(k)
        if L<=l and r<=R:#範囲内
            self.lazy[k]=x*(r-l)
            self.eval(k)
        elif L<r and l<R:
            self.add(L,R,x,k*2,l,(l+r)//2)
            self.add(L,R,x,k*2+1,(l+r)//2,r)
            self.tree[k]=self.tree[k*2]+self.tree[k*2+1]
        else:#範囲外
            pass

    def query(self, L,R,k=None,l=None,r=None):
        L=max(0,L)
        R=min(self.n,R)
        #再帰
        if k==None:return self.query(L,R,1,0,self.n)
        self.eval(k)
        if r<=L or R<=l:#範囲外
            return self.ide_ele
        elif L<=l and r<=R:#範囲内
            return self.tree[k]
        else:
            leftVal=self.query(L,R,k*2,l,(l+r)//2)
            rightVal=self.query(L,R,k*2+1,(l+r)//2,r)
            return leftVal+rightVal
class SkipList:#順序付き集合(重複可)
    def __init__(self,A=[],maxHeight=30):
        self.length=len(A)
        self.maxHeight=maxHeight #十分な数 0-indexed
        self.cnt=defaultdict(int)
        #ノードは配列の方が高速
        self.valIndex=0
        self.nextIndex=1
        self.sentinel=[None,[None]*(self.maxHeight+1)]
        for a in A:
            self.add(a)
    def __str__(self):
        elems=[k for k,v in self.cnt.items() for _ in range(v)]
        return str(elems)
    def __len__(self):
        return self.length
    def __getMaxLTE(self,x):
        now=self.sentinel
        for h in range(self.maxHeight,-1,-1):
            while now[self.nextIndex][h]!=None and now[self.nextIndex][h][self.valIndex]<=x:
                now=now[self.nextIndex][h]
        return now
    def exists(self,x):
        node=self.__getMaxLTE(x)
        return node[self.valIndex]==x
    def minGTE(self,x):#x以上の最小値
        node=self.__getMaxLTE(x)
        if node[self.valIndex]==x:
            return x
        else:
            if node[self.nextIndex][0]==None:
                return None
            else:
                return node[self.nextIndex][0][self.valIndex]
    def minGT(self,x):#xを超える最小値
        node=self.__getMaxLTE(x)
        if node[self.nextIndex][0]==None:
            return None
        else:
            return node[self.nextIndex][0][self.valIndex]
    def maxLTE(self,x):#x以下の最大値
        node=self.__getMaxLTE(x)
        return node[self.valIndex]
    def __pickHeight(self):#0-indexed
        h=0
        while random.randint(0,1)==0:
            h+=1
        return h
    def add(self,x):
        self.cnt[x]+=1
        if self.exists(x):
            return
        self.length+=1
        newNodeHeight=self.__pickHeight()
        newNode=[x,[None]*(newNodeHeight+1)]
        now=self.sentinel
        for h in range(self.maxHeight,-1,-1):#newNodeHeightから始めると最初のwhile文の時間がかかる
            while now[self.nextIndex][h]!=None and now[self.nextIndex][h][self.valIndex]<x:
                now=now[self.nextIndex][h]
            #リンクの更新
            if h<=newNodeHeight:
                maxLessThanXNode=now
                newNode[self.nextIndex][h]=maxLessThanXNode[self.nextIndex][h]
                maxLessThanXNode[self.nextIndex][h]=newNode
    def discard(self,x):
        if not self.exists(x):
            return
        self.cnt[x]-=1
        if self.cnt[x]!=0:
            return
        del self.cnt[x]
        self.length-=1
        now=self.sentinel
        for h in range(self.maxHeight,-1,-1):
            while now[self.nextIndex][h]!=None and now[self.nextIndex][h][self.valIndex]<x:
                now=now[self.nextIndex][h]
            maxLessThanXNode=now
            #リンクの更新(xが存在する高さなら)
            if maxLessThanXNode[self.nextIndex][h]!=None and maxLessThanXNode[self.nextIndex][h][self.valIndex]==x:
                removeNode=maxLessThanXNode[self.nextIndex][h]
                maxLessThanXNode[self.nextIndex][h]=removeNode[self.nextIndex][h]
class BIT:
    def __init__(self,N):
        self.N=N
        self.values=[0]*(N+1)#1-indexed
    def add(self,i,x):
        i+=1 #1-indexed
        while i<=self.N:
            self.values[i]+=x
            i+=i&(-i)
    def query(self,i):#[0,i]
        i+=1 #1-indexed
        ret=0
        while i!=0:
            ret+=self.values[i]
            i-=i&(-i)
        return ret
#文字列
alphabet=string.ascii_lowercase
ALPHABET=string.ascii_uppercase
alphaToIdx={c:i if c<"A" else i-26 for i,c in enumerate(alphabet+ALPHABET)}
def join(A,s=""):
    return s.join(list(map(str,A)))
def sortStr(s):
    return "".join(sorted(s))
#数学系
mod=998244353
mod2=1000000007
def invsign(num):
    return -num
def gcd(*args):
    return reduce(math.gcd,args)
def lcm(*args):
    return reduce(lambda a,b:a*b//math.gcd(a,b),args)
def getPrimes(n,returnNums=False):#O(n log log n)
    if n==0:return []
    ret=[True]*(n+1)
    ret[0]=False
    ret[1]=False
    for a in range(2,n+1):
        if ret[a]:
            for b in range(a*2,n+1,a):
                ret[b]=False
    if returnNums:
        ret=[i for i,flag in enumerate(ret) if flag]
    return ret
def isPrime(n):#O(√n)
    if(n<=1):return False
    i=2
    while i*i<=n:
       if(n%i==0):return False
       i+=1
    return True
def factorize(n):#O(√n)
    b = 2
    ret=defaultdict(int)
    while b * b <= n:
        while n % b == 0:
            n //= b
            ret[b]+=1
        b+=1
    if n > 1:ret[n]+=1
    return ret
class EratosthenesFactorization:#O(n log log n)
    def __init__(self,N):
        self.primeFactor=[None]*(N+1)
        for i in range(2,N+1):
            if self.primeFactor[i]==None:
                self.primeFactor[i]=i
                for j in range(i*2,N+1,i):
                    self.primeFactor[j]=i
    def factorize(self,N):#O(logN)
        now=N
        primes=[]
        while now!=1:
            primes.append(self.primeFactor[now])
            now=now//self.primeFactor[now]
        return Counter(primes)
def getPowList(base,maxExponent,mod):
    ret=[1]*(maxExponent+1)
    for i in range(1,maxExponent+1):
        ret[i]=ret[i-1]*base%mod
    return ret
def getFactorials(n,mod):
    ret=[1]*(n+1)
    for a in range(2,len(ret)):
        ret[a]=ret[a-1]*a
        if mod!=None:
            ret[a]%=mod
    return ret
def getFactorialModInvs(n,mod):
    return [pow(factorial,mod-2,mod) for factorial in getFactorials(n,mod)]
def comb_(n,r,factorials,mod=None,factorialModInvs=None):#O(1)
    if n-r<0:return 0
    if mod==None:
        return factorials[n]//(factorials[n-r]*factorials[r])
    else:
        if factorialModInvs:
            return (factorials[n]*factorialModInvs[n-r]%mod)*factorialModInvs[r]%mod
        else:
            return (factorials[n]*pow(factorials[n-r],mod-2,mod)%mod)*pow(factorials[r],mod-2,mod)%mod
class Comb:
    def __init__(self,n,mod,factorials=None,factorialModInvs=None):
        self.n=n
        self.mod=mod
        if factorials:
            self.factorials=factorials
        else:
            self.factorials=getFactorials(n,mod)
        if factorialModInvs:
            self.factorialModInvs=factorialModInvs
        else:
            self.factorialModInvs=getFactorialModInvs(n,mod)
    def comb(self,n,r):
        if n-r<0:return 0
        return (self.factorials[n]*self.factorialModInvs[n-r]%self.mod)*self.factorialModInvs[r]%self.mod
def comb2(n,mod=None):
    if n-2<0:return 0
    if mod==None:
        return (n*(n-1))//2
    else:
        return (n*(n-1)%mod)*pow(2,mod-2,mod)
def baseConversion(numbers,base,nextbase):
    N=len(numbers)
    if nextbase==10:
        sum_=0
        for i,number in enumerate(numbers):
            p=(N-i-1)
            sum_+=number*base**p
        ret=list(map(int,list(str(sum_))))
    elif base==10:
        number=int(join(numbers))
        ret=deque()
        while 0<number:
            ret.appendleft(number%nextbase)
            number//=nextbase
        if len(ret)==0:ret.append(0)
        ret=list(ret)
    else:
        ret=baseConversion(numbers,base,10)
        ret=baseConversion(ret,10,nextbase)
    return ret
def getDigitSum(numAsStr):
    A=list(map(int,list(str(numAsStr))))
    return sum(A)
def getDivisors(number):#O(√n log √n)
    ret=[]
    i=1
    while i**2<=number:
        if number%i==0:
            ret.append(i)
            ret.append(number//i)
            if i==number//i:ret.pop()
        i+=1
    ret.sort()
    return ret
def iceil(a,b,mul=False):
    return int(((a+(b-1))//b)*(b if mul else 1))
def ifloor(a,b,mul=False):
    return int((a//b)*(b if mul else 1))
def mex(S):#集合で受け取る
    ret=0
    while ret in S:
        ret+=1
    return ret
def isqrt(num):#pypyではisqrtは使えない
    return int(math.sqrt(num))
def sumTousa(l,r,d,mod=None):
    if mod==None:
        N=((r-l)//d+1)
        return N*(l+r)//2
    else:
        N=((r-l)*pow(d,mod-2,mod)+1)
        return (N*(l+r)*pow(2,mod-2,mod))%mod
def sign(num):
    if num>=0:
        return 1
    elif num<0:
        return -1
    else:
        return 0
def inc(x):
    return x+1
def dec(x):
    return  x-1
def minmax(A):
    m,M=A[0],A[0]
    for a in A:
        m=min(m,a)
        M=max(M,a)
    return m,M
def getDigits(num):
    if num==0:return 1
    ret=0
    while num>0:
        num//=10
        ret+=1
    return ret 
def phi(num):#O(log N)
    P=factorize(num).keys()
    return (num//(reduce(mul,P)))*reduce(mul,[p-1 for p in P])
def modinv(num,mod,isprime=True):
    if isprime:
        return pow(num,mod-2,mod)
    else:
        if gcd(num,mod)==1:
            return pow(num,phi(mod)-1,mod)
        else:
            return None
def nearlyEqual(a,b,delta):
    return abs(a-b)<=delta
def getSetTeams(numOfMems,MinNumOfTeams,MaxNumOfTeams):
    ret=[]
    teams=[]
    def f(i):
        if i==numOfMems:
            if MinNumOfTeams<=len(teams)<=MaxNumOfTeams:
                ret.append([team[:] for team in teams])
        else:
            for team in teams:
                team.append(i)
                f(i+1)
                team.pop()
            if len(teams)<MaxNumOfTeams:
                teams.append([i])
                f(i+1)
                teams.pop()
    f(0)
    return ret
def getMedian(A,isSortedList=False):
    sortedList=A[:]
    N=len(A)
    if isSortedList==False:
        sortedList.sort()
    return (sortedList[N//2]+sortedList[N-N//2-1])/2
#二分探索
def intBS(left,right,func):
    while right-left>1:
        mid=(left+right)//2
        if func(mid):
            left=mid
        else:
            right=mid
    return left,right
def floatBS(left,right,func,delta):
    while right-left>delta:
        mid=(left+right)/2
        if func(mid):
            left=mid
        else:
            right=mid
    return left,right
def bisectLeft(A,x,left=0,right=None,func=lambda x:x):
    left=left-1 #含まない
    if right==None:right=len(A)#含む
    return intBS(left,right,lambda i:A[i]<x)[1]
def bisectRight(A,x,left=0,right=None,func=lambda x:x):
    left=left-1 #含まない
    if right==None:right=len(A)#含む
    return intBS(left,right,lambda i:A[i]<=x)[1]
def rvBisectLeft(A,x):
    return intBS(-1,len(A),lambda i:x<A[i])[1]
def rvBisectRight(A,x):
    return intBS(-1,len(A),lambda i:x<=A[i])[1]
def binarySearch(A,x):
    i=bisect.bisect_left(A,x)
    if i==len(A):
        ret=-1
    else:
        if A[i]==x:
            ret=i
        else:
            ret=-1
    return ret
#ビット演算
def getCombs(bits,length):
    if bits==0:return [0]
    ret=[]
    num=2**bits-1
    while num<(1<<length):
        ret.append(num)
        x=num&(-num)
        y=num+x
        z=num&(~y)
        z//=x
        z=z>>1
        num=(y|z)
    return ret
def getPopCountList(length):
    list_length=2**length
    popcount=[0]*list_length
    for i in range(1,list_length):
        popcount[i]=i%2+popcount[i//2]
    return popcount
def getBinSubs(num):
    ret=[]
    T=num
    while True:
        ret.append(T)
        if T==0:break
        T=(T-1)&num
    return ret
def getbit(num,i):
    return (num>>i)&1   
def cntbits(num):
    ret=0
    while num:
        ret+=num&1
        num>>=1
    return ret
def getSetBitPos(num):
    return [i for i in range(num.bit_length()) if getbit(num,i)]
def getUnSetBitPos(num,N):
    return [i for i in range(N) if getbit(num,i)==0]
#座標
rd=[[0,1],[1,0]]
crossmove=[[0,-1],[0,1],[1,0],[-1,0]]
xmove=[[-1,-1],[1,1],[-1,1],[1,-1]]
arround=crossmove+xmove
def getDist(x1,y1,x2,y2,sq=False):
    ret=(x1-x2)**2+(y1-y2)**2
    if not sq:ret**=0.5
    return ret
def getManhattanDist(x1,y1,x2,y2):
    return abs(x1-x2)+abs(y1-y2)
def lrudToYXMat(d):
    if d.upper()=="L":return [0,-1]
    elif d.upper()=="R":return [0,1]
    elif d.upper()=="U":return [-1,0]
    elif d.upper()=="D":return [1,0]
def lrudToYXCoor(d):
    if d.upper()=="L":return [0,-1]
    elif d.upper()=="R":return [0,1]
    elif d.upper()=="U":return [1,0]
    elif d.upper()=="D":return [-1,0]
def within(y,x,H,W):
    return 0<=y<H and 0<=x<W
def rot45(y,x):#√2の倍率は無視
    return x+y,x-y
def rot90(y,x):
    return x,-y
def rot180(y,x):
    return -y,-x
def rot270(y,x):
    return -x,y
def newPoints(y,x,H,W,move):
    ret=[]
    for dy,dx in move:
        newy=y+dy
        newx=x+dx
        if within(newy,newx,H,W):
            ret.append([newy,newx])
    return ret
#行列
def getIdMat(width):
    ret=[[0]*width for i in range(width)]
    for i in range(width):
        ret[i][i]=1
    return ret
def transpose(mat):
    if mat==[]:return []
    ret=[[None for j in range(len(mat))] for i in range(len(mat[0]))]
    for i in range(len(mat)):
        for j in range(len(mat[0])):
            ret[j][i]=mat[i][j]
    return ret
def dot(A,B):
    B_=copyMat(B)
    if type(B[0])!=list:
        B_=transpose([B_])
    if len(A[0])!=len(B_):raise ValueError
    H=len(A)
    W=len(B_[0])
    ret=[[0 for j in range(W)] for i in range(H)]
    for i in range(H):
        for j in range(W):
            for k in range(len(B_)):
                ret[i][j]+=A[i][k]*B_[k][j]
    return ret
def getMatrixPowDict(A,max_,mod=None):
    D={}
    D[1]=A
    now=2
    while now<=max_:
        D[now]=dot(D[now//2],D[now//2])
        if mod!=None:
            for j in range(len(D[now])):
                D[now]=list(map(lambda x:x%mod,D[now]))
        now*=2
    return D
def matrixPow(A,i,mod=None,matrixPowDict=None):
    if matrixPowDict==None:matrixPowDict=getMatrixPowDict(A,i)
    B=bin(i)[2:][::-1]
    I=[[1 if k==j else 0 for k in range(len(A))] for j in range(len(A))]
    ret=I
    for j,v in enumerate(B):
        if v=="1":
            ret=dot(ret,matrixPowDict[2**j])
    if mod!=None:
        for j in range(len(ret)):
            ret[j]=list(map(lambda x:x%mod,ret[j]))

    return ret
def modHakidashi(A,mod,isExtended=False):#return rank
    rows=len(A)
    cols=len(A[0])
    rank=0
    for col in range(cols):
        #行を入れ替える
        if isExtended and col==cols-1:break
        pivot=None
        for row in range(rank,rows):
            if A[row][col]!=0:
                pivot=row
                break
        if pivot==None:continue
        A[rank],A[pivot]=A[pivot],A[rank]

        #ピボットの値を1にする
        A[rank]=list(map(lambda x:x*pow(A[rank][col],mod-2,mod),A[rank]))
        #ピボットのある列の値をすべて0にする
        for row in range(rows):
            if row==rank:continue
            constantFactor=A[row][col]
            for col2 in range(cols):
                A[row][col2]-=(A[rank][col2]*constantFactor)%mod
                A[row][col2]%=mod
        rank+=1
    return rank
def hakidashi(A,eps,isExtended=False):#未検証
    rows=len(A)
    cols=len(A[0])
    rank=0
    for col in range(cols):
        #行を入れ替える
        if isExtended and col==cols-1:break
        pivot=None
        maxAbs=-inf
        for row in range(rank,rows):
            if abs(A[row][col])>eps and abs(A[row][col])>maxAbs:#計算誤差を小さくするため絶対値が大きいものを優先する
                pivot=row
                maxAbs=abs(A[row][col])
                break
        if pivot==None:continue
        A[rank],A[pivot]=A[pivot],A[rank]

        #ピボットの値を1にする
        A[rank]=list(map(lambda x:x/A[rank][col],A[rank]))
        #ピボットのある列の値をすべて0にする
        for row in range(rows):
            if row==rank and abs(A[row][col])>eps:continue
            constantFactor=A[row][col]
            for col2 in range(cols):
                A[row][col2]-=A[rank][col2]*constantFactor
        rank+=1
    return rank
#一次元配列
class wideRangeCumSum:
    def __init__(self):
        self.deltaList=defaultdict(int)
        self.cumsum={}
        self.keys_=[]
    def keys(self):
        return self.keys_
    def add(self,i,x):
        self.deltaList[i]+=x
    def updateCumSum(self):
        self.keys_=sorted(self.deltaList.keys())
        for i,curKey in enumerate(self.keys_):
            self.cumsum[curKey]=self.deltaList[curKey]
            if 0<i:
                prevKey=self.keys_[i-1]
                self.cumsum[curKey]+=self.cumsum[prevKey]
    def get(self,i):
        if i in self.cumsum:#O(1)
            return self.cumsum[i]
        else:#O(log len(cumsumKeys))
            j=bisect_right(self.keys_,i)-1
            if j==-1:
                return 0
            else:
                return self.cumsum[self.keys_[j]]
    def getAllRanges(self,includingEnd=False):
        ret=[]
        for i in range(len(self.keys_)-1):
            left=self.keys_[i]
            right=self.keys_[i+1]
            ret.append([left,right])
        if includingEnd:
            ret.append([self.keys[-1],None])
        return ret
def zaatsu(A):#座標圧縮
    B=list(set(A))
    B.sort()
    ret=[]
    for a in A:
        ret.append(bisect.bisect_left(B,a))
    return ret
def getCumSum(A):
    N=len(A)
    ret=[0]*(N+1)
    for i in range(1,N+1):
        ret[i]=ret[i-1]+A[i-1]
    return ret
def imos(deltaList):
    ret=deltaList[:]
    for i in range(1,len(deltaList)):
        ret[i]=ret[i-1]+deltaList[i]
    return ret
def nextPermutation(A):
    N=len(A)
    for i in range(N-1-1,-1,-1):
        if A[i]<A[i+1]:
            for j in range(N-1,-1,-1):
                if A[i]<A[j]:
                    A[i],A[j]=A[j],A[i]
                    A[i+1:]=A[i+1:][::-1]
                    return True
    return None
def listmap(A,func):
    return list(map(func,A))
def getValsWithIdx(A):
    return [[val,i] for i,val in enumerate(A)]
def getPos(A,isInjective=False):
    if isInjective:
        ret=defaultdict(int)
        for i,a in enumerate(A):
            ret[a]=i
    else:
        ret=defaultdict(list)
        for i,a in enumerate(A):
            ret[a].append(i)
    return ret
def cntIf(A,func):
    ret=0
    for a in A:
        if func(a):
            ret+=1
    return ret
def cntUnique(A):
    return len(Counter(A))
def linkedsort(*args,key=None,reverse=False):
    args=list(args)
    mat=list(zip(*args))
    if key==None:
        mat.sort(reverse=reverse)
    else:
        mat.sort(key=key,reverse=reverse)
    mat=list(zip(*mat))
    for i in range(len(args)):
        args[i][:]=mat[i]
def findIf(A,func):
    for i,a in enumerate(A):
        if func(a):
            return i
    return -1
def rfindIf(A,func):
    for i in range(len(A)-1,-1,-1):
        if func(A[i]):
            return i
    return -1
def isSub(a,A):
    now=0
    N=len(A)
    for i in range(len(a)):
        while now<N:
            if a[i]==A[now]:
                now+=1
                break
            else:
                now+=1
        else:
            return False
    return True
def rfind(A,val):
    for i in range(len(A)-1,-1,-1):
        if A[i]==val:
            return i
    return -1
def cntInv(A):#O(N log N)
    ret=0
    A=zaatsu(A)
    bit=BIT(len(A))
    for i in range(len(A)):
        ret+=i-bit.query(A[i])
        bit.add(A[i],1)
    return ret
def cntInv2v(A):#O(N)
    if A==[]:return 0
    maxval=max(A)
    cnt=0
    ret=0
    for a in A:
        if a==maxval:
            cnt+=1
        else:
            ret+=cnt
    return ret
def RLE(A):
    ret=[]
    prev=None
    for a in A:
        if prev==a:
            ret[-1][1]+=1
        else:
            ret.append([a,1])
        prev=a
    return ret
def LIS(A):#最長増加部分列 O(N log N)
    dp=[inf]*len(A) #dp[i]=部分列の長さが(i+1)の時の最終要素の最小値
    for a in A:
        dp[bisect.bisect_left(dp,a)]=a
    return bisect.bisect_left(dp,inf-1)
def replace(A,curVal,newVal):
    return [newVal if a==curVal else a for a in A]
def getLoopIdx(*args):
    ret=[]
    code=""
    for i,arg in enumerate(args):
        code+=f"{'    '*i}for {alphabet[i]} in {arg}:\n"
    code+=f"{'    '*len(args)}ret.append(({','.join(alphabet[:len(args)])}))"
    exec(code)
    return ret
def zalgo(A):#O(N)
    N=len(A)
    ret=[0]*N
    ret[0]=N
    i=1
    j=0
    while i<N:
        while i+j<N and A[j]==A[i+j]:
            j+=1
        ret[i]=j
        if j==0:
            i+=1
        else:
            k=1
            while i+k<N and k+ret[k]<j:
                ret[i+k]=ret[k]
                k+=1
            i+=k
            j-=k
    return ret
def getTrueIdx(A):
    return [i for i,a in enumerate(A) if a]
def getUnique(A):
    return list(Counter(A).keys())
def rvsort(A):
    return A.sort(reverse=True)
#多次元配列
def copyMat(A):
    N=len(A)
    dim=1
    cur=A
    while len(cur)>0 and type(cur[0]) in (tuple,list):
        dim+=1
        cur=cur[0]
    if dim==1:
        return A[:]
    else:
        ret=[None]*N
        for i in range(N):
            ret[i]=copy(A[i])
        return ret
def sqlist(H,W,initVal=None):
    return [[initVal]*W for i in range(H)]
def cblist(H,W,Z,initVal=None):
    return [[[initVal]*Z for j in range(W)] for i in range(H)]
def getCol(A,col):
    return [A[i][col] for i in range(len(A))]
def getRangeSumCumSumMat(cumSum,y1,x1,y2,x2):#1-indexed 閉区間
    ret=cumSum[y2][x2]
    ret-=cumSum[y2][x1-1]
    ret-=cumSum[y1-1][x2]
    ret+=cumSum[y1-1][x1-1]
    return ret
def getCumSumMat(mat):
    H,W=len(mat),len(mat[0])
    ret=sqlist(H+1,W+1,0)
    for i in range(1,H+1):
        for j in range(1,W+1):
            ret[i][j]+=ret[i][j-1]+mat[i-1][j-1]
    for j in range(1,W+1):
        for i in range(1,H+1):
            ret[i][j]+=ret[i-1][j]
    return ret
class CumSumByValue:#メモリO(N) 1-indexed
    def __init__(self,A):
        self.cumsum=defaultdict(None)
        for a in set(A):
            self.cumsum[a]=wideRangeCumSum()
        for i,a in enumerate(A):
            self.cumsum[a].add(i+1,1)
        for a in set(A):
            self.cumsum[a].updateCumSum()
    def get(self,key,i):
        if key in self.cumsum:
            return self.cumsum[key].get(i)
        else:
            return 0
def imosMat(deltaList):
    H,W=len(deltaList),len(deltaList[0])
    ret=copyMat(deltaList)
    for i in range(H):
        for j in range(1,W):
            ret[i][j]+=ret[i][j-1]
    for j in range(W):
        for i in range(1,H):
            ret[i][j]+=ret[i-1][j]
    return ret
def yxToX(y,x,H,W):
    return y*W+x
def yxzToX(y,x,z,H,W,D):
    return y*(W*D)+x*D+z
def cntMat(mat,val):
    cnt=0
    for i in range(len(mat)):
        for j in range(len(mat[i])):
            if mat[i][j]==val:
                cnt+=1
    return cnt
def mapping(A,func):
    N=len(A)
    dim=1
    cur=A
    while len(cur)>0 and type(cur[0]) in (tuple,list):
        dim+=1
        cur=cur[0]
    if dim==1:
        return list(map(func,A))
    else:
        ret=[None]*N
        for i in range(N):
            ret[i]=mapping(A[i],func)
        return ret
def rot90Mat(mat):
    H=len(mat[0])
    W=len(mat)
    ret=sqlist(H,W)
    for i in range(H):
        for j in range(W):
            ret[i][j]=mat[j][H-1-i]
    return ret
def addWall(A,chr="#"):
    H=len(A)
    W=len(A[0])
    type_=type(A[0])
    if type_==str:
        for i in range(H):
            A[i]+=chr
        A.append(chr*(W+1))
    else:
        for i in range(H):
            A[i].append(chr)
        A.append([chr]*(W+1))
def findMat(mat,val):
    for i in range(len(mat)):
        for j in range(len(mat[i])):
            if mat[i][j]==val:return (i,j)
    return None
def flatten(mat):
    return list(itertools.chain.from_iterable(mat))
def listToMat(A,H,W):
    N=len(A)
    ret=sqlist(H,W)
    for i in range(H):
        for j in range(W):
            ret[i][j]=A[i*W+j]
    return ret
def sliceMat(mat,top,bottom,left,right):#閉区間
    ret=[]
    for i in range(top,bottom+1):
        if 0<=i<len(mat):
            ret.append(mat[i][left:right+1])
            if ret[-1]==[]:ret.pop()
    return ret
def listToTuple(A):
    ret=[]
    for item in A:
        if isinstance(item, list):
            ret.append(tuple(listToTuple(item)))
        elif isinstance(item, tuple):
            ret.append(listToTuple(item))
        else:
            ret.append(item)
    ret=tuple(ret)
    return ret
#グラフ
class Doubling:#パスグラフ用 O(log dist * N)
    def __init__(self,pathGraph,maxDist):
        self.bitLength=len(bin(maxDist)[2:])
        self.N=len(pathGraph)
        self.D=sqlist(self.bitLength,self.N,list)
        for i in range(self.bitLength):
            if i==0:
                self.D[0]=pathGraph[:]
            else:
                for j in range(self.N):
                    self.D[i][j]=self.D[i-1][self.D[i-1][j]]
    def getVal(self,start,dist):
        ret=start
        distBitLength=len(bin(dist)[2:])
        for i in range(distBitLength):
            if ((dist>>i)&1)==1:
                ret=self.D[i][ret]
        return ret
class CyclicPath:#入力はパス(パスグラフではない) O(N)
    def __init__(self,path):
        visited=defaultdict(bool)
        cycleStartVal=None
        cycleEndIndex=None
        for i,u in enumerate(path):
            if visited[u]:
                cycleStartVal=u
                cycleEndIndex=i
                break
            visited[u]=True
        cycleStartIndex=path.index(cycleStartVal)
        self.acyclicPath=path[:cycleStartIndex]
        self.cyclicPath=path[cycleStartIndex:cycleEndIndex]
    def getVal(self,index):#O(1)
        if index<=len(self.acyclicPath)-1:
            return self.acyclicPath[index]
        else:
            index-=len(self.acyclicPath)
            index%=len(self.cyclicPath)
            return self.cyclicPath[index]
    def getPath(self):
        return self.acyclicPath,self.cyclicPath
class FordFulkerson:
    def __init__(self,edges,V,s,t):
        self.V=V
        self.used=[False]*V
        self.G=[[] for a in range(V)]
        self.s=s
        self.t=t
        for edge in edges:
            self.G[edge.x].append({"x":edge.x,"y":edge.y,"cap":edge.cost,"rev":len(self.G[edge.y])})
            self.G[edge.y].append({"x":edge.y,"y":edge.x,"cap":0,"rev":len(self.G[edge.x])-1})
    def dfs(self,v,t,f=inf):
        if v==t:return f
        self.used[v]=True
        for a in range(len(self.G[v])):
            x=self.G[v][a]["x"]
            y=self.G[v][a]["y"]
            cap=self.G[v][a]["cap"]
            rev=self.G[y][self.G[x][a]["rev"]]
            if self.used[y] or cap==0:continue
            f2=self.dfs(y,t,min(f,cap))
            if f2>0:
                self.G[v][a]["cap"]-=f2
                rev["cap"]+=f2
                return f2
        return 0
    def maxflow(self):
        flow=0
        while True:
            self.used=[False]*self.V
            zouka=self.dfs(self.s,self.t)
            if zouka==0:break
            flow+=zouka
        return flow
def edgesToUG(edges,N):
    G=[[] for i in range(N)]
    for edge in edges:
        u,v=edge
        G[u].append(v)
        G[v].append(u)
    return G
def edgesToDG(edges,N):#未検証
    G=[[] for i in range(N)]
    for edge in edges:
        u,v=edge
        G[u].append(v)
    return G
def UGToEdges(G):
    N=len(G)
    edges=[]
    for i in range(N):
        for j in G[i]:
            if i<j:
                edges.append((i,j))
    return edges
def DGToEdges(G):#未検証
    N=len(G)
    edges=[]
    for i in range(N):
        for j in G[i]:
            edges.append((i,j))
    return edges
def dijkstra(edges,N,start):#O(V+E*logV)
    G=[[] for a in range(N)]
    for edge in edges:
        G[edge.x].append([edge.cost,edge.y])
    mincost=[inf]*N
    Q=[[0,start]]
    while len(Q)>0:
        curCost,cur=heappop(Q)
        if mincost[cur]!=inf:continue
        mincost[cur]=curCost
        for edgeCost,nxt in G[cur]:
            newCost=curCost+edgeCost
            if mincost[nxt]==inf:#高速化のため
                heappush(Q,[newCost,nxt])
    return mincost
def dictDijkstra(edges,start):
    mincost=defaultdict(lambda x:inf)
    visited=defaultdict(bool)
    G=defaultdict(list)
    for edge in edges:
        G[edge.x].append([edge.cost,edge.y])
    Q=[]
    heappush(Q,[0,start])
    while len(Q)>0:
        nowcost,nowx=heappop(Q)
        if visited[nowx]:continue
        visited[nowx]=True
        mincost[nowx]=nowcost
        for cost,y in G[nowx]:
            if visited[y]:continue #高速化
            newcost=nowcost+cost
            heappush(Q,[newcost,y])
    return mincost
def dictBFS01(edges,start):
    G=defaultdict(list)
    for edge in edges:
        G[edge.x].append((edge.y,edge.cost))
    mincost=defaultdict(lambda:inf)
    Q=deque([[start,0]])
    while Q:
        cur,cost=Q.popleft()
        if mincost[cur]!=inf:continue
        mincost[cur]=cost
        for nxt,edgeCost in G[cur]:
            if edgeCost==1:
                Q.append([nxt,cost+edgeCost])
            else:
                Q.appendleft([nxt,cost+edgeCost])
    return mincost
def shortestBFS(adjList,start):
    N=len(adjList)
    mincost=[inf]*N
    mincost[start]=0
    Q=deque([start])
    while Q:
        now=Q.popleft()
        for tugi in adjList[now]:
            if mincost[tugi]==inf:
                mincost[tugi]=mincost[now]+1
                Q.append(tugi)
    return mincost
def dictShortedBFS(adjList,start):
    mincost=defaultdict(lambda:inf)
    mincost[start]=0
    Q=deque([start])
    while Q:
        now=Q.popleft()
        for tugi in adjList[now]:
            if mincost[tugi]==inf:
                mincost[tugi]=mincost[now]+1
                Q.append(tugi)
    return mincost
def warshallFloyd(adjMat):#O(N**3)
    V=len(adjMat)
    mincost=[[inf for j in range(V)] for i in range(V)]
    for i in range(V):
        for j in range(V):
            mincost[i][j]=adjMat[i][j]
    for i in range(V):mincost[i][i]=0 #この行をコメントすると同じ頂点を一回以上辺を通ってたどり着く場合の最短距離が分かる
    for k in range(V):
        for s in range(V):
            for t in range(V):
                if mincost[s][k]==inf or mincost[k][t]==inf:continue
                mincost[s][t]=min(mincost[s][t],mincost[s][k]+mincost[k][t])
    return mincost
def bellemanFord(edges,N,start):
    mincost=[inf]*N
    mincost[start]=0
    for _ in range(N):
        for edge in edges:
            if mincost[edge.x]==inf:continue
            mincost[edge.y]=min(mincost[edge.y],mincost[edge.x]+edge.cost)
    return mincost
def getConnectedNodes(adjList,start):
    S=[start]
    connectedNodes=set([start])
    while len(S)>0:
        cur=S.pop()
        for nxt in adjList[cur]:
            if nxt not in connectedNodes:
                connectedNodes.add(nxt)
                S.append(nxt)
    return connectedNodes
def topologicalSort(adjList):#閉路がある場合Noneを返す
    path=[]
    #入次数
    indeg=[0]*len(adjList)
    for u in range(len(adjList)):
        for v in adjList[u]:
            indeg[v]+=1

    isolatedNodes=[]

    #初めから入次数が0の頂点をスタックに入れる
    for u in range(len(adjList)):
        if indeg[u]==0:
            isolatedNodes.append(u)
    
    while isolatedNodes:
        u=isolatedNodes.pop()
        path.append(u)
        for v in adjList[u]:
            indeg[v]-=1
            if indeg[v]==0:
                isolatedNodes.append(v)
                
    return path if len(path)==len(adjList) else None
def SSC(edges,N):#強連結成分分解 O(VlogV+E)
    #DFS一回目
    G=[[] for i in range(N)]
    for edge in edges:
        G[edge.x].append(edge.y)
    nokori=set(list(range(N)))
    visited=defaultdict(bool)
    kaerigake=[]
    while nokori:
        start=nokori.pop() 
        S=[(start,True)]#(start,行き道か)
        while S:
            now,isIki=S.pop()
            if visited[(now,isIki)]:continue
            visited[(now,isIki)]=True
            nokori.discard(now)
            if isIki:
                S.append((now,False))
                for tugi in G[now]:
                    if visited[(tugi,True)]:
                        pass
                    else:
                        S.append((tugi,True))
            else:
                kaerigake.append(now)
    numbers=[None]*N
    for i,v in enumerate(kaerigake):
        numbers[v]=i
    #DFS二回目
    G=[[] for i in range(N)]
    for edge in edges:
        G[edge.y].append(edge.x)
    maxQ=[(numbers[i],i) for i in range(N)]
    maxQ.sort(reverse=True)
    visited=[False]*N
    ret=[]
    for tmp,start in maxQ:
        if visited[start]:continue#既に訪れていたら
        visited[start]=True
        S=[start]
        ret.append([])
        while S:
            now=S.pop()
            ret[-1].append(now)
            for tugi in G[now]:
                if not visited[tugi]:
                    visited[tugi]=True
                    S.append(tugi)
    return ret #上流の強連結成分から
def isConnected(adjList):
    S=[0]
    V=[False]*len(adjList)
    V[0]=True
    while S:
        now=S.pop()
        for tugi in adjList[now]:
            if not V[tugi]:
                V[tugi]=True
                S.append(tugi)
    return all(V)
def isNibuGraph(adjList):
    V=len(adjList)
    colors=[None]*V
    for s in range(V):
        if colors[s]==None:
            S=[s]
            colors[s]=0
            while S:
                cur=S.pop()
                for nxt in adjList[cur]:
                    if colors[nxt]==None:
                        colors[nxt]=1-colors[cur]
                        S.append(nxt)
                    elif colors[nxt]==colors[cur]:
                        return False
                    else:
                        pass
    return True
def paintNibuGraph(adjList):
    N=len(adjList)
    colors=[None]*N
    for start in range(N):
        if colors[start]==None:
            S=[start]
            colors[start]=0
            while S:
                cur=S.pop()
                for nxt in adjList[cur]:
                    if colors[nxt]==None:
                        colors[nxt]=1-colors[cur]
                        S.append(nxt)
                    elif colors[nxt]==colors[cur]:
                        return None
                    else:
                        pass
    return colors
def diam(adjMat):#グラフの直径を求める
    mincost=warshallFloyd(adjMat)
    ret=-inf
    for i in range(len(adjMat)):
        for j in range(len(adjMat)):
            ret=max(ret,mincost[i][j])
    return ret
def reverseDegree(adjList):
    N=len(adjList)
    ret=[[] for i in range(N)]
    for i in range(N):
        for next_ in adjList[i]:
            ret[next_].append(i)
    return ret
def gridToGraph(A,move,obstacle="#"):
    H=len(A)
    W=len(A[0])
    G=defaultdict(list)
    for y in range(H):
        for x in range(W):
            for d in move:
                nextY=y+d[0]
                nextX=x+d[1]
                if within(nextY,nextX,H,W) and A[nextY][nextX]!=obstacle:
                    G[(y,x)].append((nextY,nextX))
    return G
def TSP(adjMat,start=None):#O(N**2 * 2**N)
    #dp[bin_][i]=bin_のbitが立っている箇所を通る&最後に頂点iに到達する場合の最小値
    N=len(adjMat)
    dp=sqlist(2**N,N,inf)
    dp[0][start]=0
    for curbin in range(2**N-1):
        for cur in range(N):
            for nxt in range(N):
                if getbit(curbin,nxt)==0:
                    nxtbin=curbin|(1<<nxt)
                    if dp[curbin][cur]!=inf:
                        dp[nxtbin][nxt]=min(dp[nxtbin][nxt],dp[curbin][cur]+adjMat[cur][nxt])
    return dp
def getConnectedComponents(edges,N,updateVertexNumbers=True):
    U=UnionFind(N)
    for edge in edges:
        u=edge.x
        v=edge.y
        U.union(u,v)
    ret={}
    for edge in edges:
        u=edge.x
        v=edge.y
        root=U.root(u)
        if root not in ret:
            ret[root]={"N":U.unionSize(root),"edges":[]}
        ret[root]["M"]+=1
        ret[root]["edges"].append(Edge(u,v,edge.cost))
    for u in range(N):
        if U.unionSize(u)==1:
            ret[u]={"N":1,"edges":[]}
    if updateVertexNumbers:
        for root in U.roots():
            vertexNums=set()
            for edge in ret[root]["edges"]:
                vertexNums.add(edge.x)
                vertexNums.add(edge.y)
            vertexNums=sorted(vertexNums)
            pos=getPos(vertexNums,isInjective=True)
            for i in range(len(ret[root]["edges"])):
                ret[root]["edges"][i].x=pos[ret[root]["edges"][i].x]
                ret[root]["edges"][i].y=pos[ret[root]["edges"][i].y]
    ret=list(ret.values())
    return ret
def getPostOrder(adjList,start):
    ret=[]
    S=[(start,True)]
    departed=[False]*len(adjList)
    while S:
        cur,iki=S.pop()
        if iki:
            departed[cur]=True
            S.append((cur,False))
            for nxt in adjList[cur]:
                if departed[nxt]:continue
                S.append((nxt,True))
        else:
            ret.apend(cur)
    return ret
#グラフ(木)
def edgesToTree(N,edges,root):
    parents=[None]*N
    children=[[] for i in range(N)]
    G=edgesToUG(edges,N)
    S=[root]
    V=[False]*N
    V[root]=True
    while S:
        cur=S.pop()
        for nxt in G[cur]:
            if not V[nxt]:
                V[nxt]=True
                S.append(nxt)
                children[cur].append(nxt)
                parents[nxt]=cur
    return parents,children
def treeToUG(parents,children):
    N=len(parents)
    ret=[[] for i in range(N)]
    for i in range(N):
        if parents[i]!=None:
            ret[i].append(parents[i])
        ret[i]+=children[i]
    return ret
def getLeafs(parents,children):
    ret=[]
    N=len(parents)
    for i in range(N):
        deg=0
        if parents[i]!=None:deg+=1
        deg+=len(children[i])
        if deg==1:
            ret.append(i)
    return ret
def getTreeDepths(parents,children):
    root=parents.index(None)
    N=len(parents)
    S=[root]
    ret=[None]*N
    ret[root]=0
    while S:
        cur=S.pop()
        for child in children[cur]:
            S.append(child)
            ret[child]=ret[cur]+1
    return ret
def doubleSweep(edges,N):#木の直径を求める
    G=[[] for i in range(N)]
    for e in edges:
        G[e.x].append((e.y,e.cost))
    start=0
    maxmincost=None
    for _ in range(2):
        S=[start]
        mincost=[inf]*N
        mincost[start]=0
        while S:
            now=S.pop()
            nowcost=mincost[now]
            for e in G[now]:
                if mincost[e[0]]==inf:
                    mincost[e[0]]=nowcost+e[1]
                    S.append(e[0])
        maxmincost=max(mincost)
        start=mincost.index(maxmincost) 
    return maxmincost
def kruskal(N,Edges):#最小全域木 O(M logN)
    U=UnionFind(N)
    Edges.sort(key=lambda x:x.cost)
    treeEdges=[]
    for i in range(len(Edges)):
        edge=Edges[i]
        x=edge.x
        y=edge.y
        cost=edge.cost
        if not U.same(x,y):
            treeEdges.append(Edge(x,y,cost))
            U.union(x,y)
    return treeEdges
def extractSimpleUGCycle(G):#O(N log N)
    N=len(G)
    deg=[len(G[u]) for u in range(N)]
    ret=copyMat(G)
    Q=[u for u in range(N) if deg[u]==1]
    for u in range(N):
        ret[u]=set(ret[u])
    
    while Q:
        u=Q.pop()
        v=ret[u].pop()
        ret[v].remove(u)
        deg[v]-=1
        if deg[v]==1:
            Q.append(v)
    
    for u in range(N):
        ret[u]=list(ret[u])
    return ret
#他
class MinMaxWithIndex:
    def __init__(self,values=[],indices=[]):
        self.minVal=None
        self.minIndex=None
        self.maxVal=None
        self.maxIndex=None
        for i in range(len(values)):
            self.update(values[i],indices[indices[i]])
    def update(self,val,index):
        if self.minVal==None or self.minVal>val:
            self.minVal=val
            self.minIndex=index
        if self.maxVal==None or self.maxVal<val:
            self.maxVal=val
            self.maxIndex=index
    def min(self):
        return self.minVal,self.minIndex
    def max(self):
        return self.maxVal,self.maxIndex
def getMaxIndex(A):
    max_=-inf
    idx=None
    for i,a in enumerate(A):
        if max_<a:
            max_=a
            idx=i
    return idx
def getMinIndex(A):
    min_=-inf
    idx=None
    for i,a in enumerate(A):
        if a<min_:
            min_=a
            idx=i
    return idx
#入力
def inputs(row=None,func=None):
    ret=None
    if row==None:
        ret=input().split()
    else:
        ret=[input() for _ in range(row)]
    if func!=None:ret=mapping(ret,func)
    return ret
def inputmat(row,func=None,transpose=False):
    if transpose:
        ret=None
        col=None
        for i in range(row):
            A=inputs()
            if i==0:
                col=len(A)
                ret=[[None]*row for _ in range(col)]
            for j in range(col):
                ret[j][i]=A[j]
    else:
        ret=[inputs() for _ in range(row)]
    if func!=None:ret=mapping(ret,func)
    return ret
def inputint():
    return int(input())
def inputints(row=None,func=None):
    ret=inputs(row,int)
    if func!=None:ret=mapping(ret,func)
    return ret
def inputintmat(row,func=None,transpose=False):
    ret=inputmat(row,int,transpose)
    if func!=None:ret=mapping(ret,func)
    return ret
def inputUG(N,M):
    G=[[] for i in range(N)]
    for i in range(M):
        u,v=inputints()
        u-=1
        v-=1
        G[u].append(v)
        G[v].append(u)
    return G
def inputDG(N,M):
    G=[[] for i in range(N)]
    for i in range(M):
        u,v=inputints()
        u-=1
        v-=1
        G[u].append(v)
    return G
def inputUEdges(M):
    Edges=[]
    for i in range(M):
        a,b=inputints()
        a-=1
        b-=1
        Edges.append(Edge(a,b,1))
        Edges.append(Edge(b,a,1))
    return Edges
def inputDEdges(M):
    Edges=[]
    for i in range(M):
        a,b=inputints()
        a-=1
        b-=1
        Edges.append(Edge(a,b,1))
    return Edges
def inputTree(N,root):
    parents=[None]*N
    children=[[] for i in range(N)]
    M=N-1
    G=inputUG(N,M)
    S=[root]
    V=[False]*N
    V[root]=True
    while S:
        now=S.pop()
        for next_ in G[now]:
            if not V[next_]:
                V[next_]=True
                S.append(next_)
                children[now].append(next_)
                parents[next_]=now
    return parents,children
def fillNone(mat,width):
    N=len(mat)
    ret=copyMat(mat)
    for i in range(N):
        for _ in range(width-len(ret[i])):
            ret[i].append(None)
    return ret
#出力
def yn(flag,trueOutput="Yes",falseOutput="No"): 
    print(trueOutput if flag else falseOutput)
def YN(flag):
    print("YES" if flag else "NO")
def no(*args): 
    print(f"{inspect.currentframe().f_back.f_lineno}: {' '.join(list(map(str,args)))}")
def printmat(A,sep=" ",fill=0):
    if sep==" " and fill==0:
        for a in A:
            print(*a)
    else:
        for a in A:
            print(sep.join(list(map(lambda x:str(x).center(fill),a))))
###############################################################################
N,M=inputints()
G=inputUG(N,M)
Q=inputint()
XK=inputintmat(Q)
dp=sqlist(4,N)
for cnt in range(4):
    for u in range(N):
        dp[cnt][u]={u+1}
        if cnt>0:
            for nxt in G[u]:
                dp[cnt][u]|=dp[cnt-1][nxt]
for x,k in XK:
    x-=1
    print(sum(dp[k][x]))