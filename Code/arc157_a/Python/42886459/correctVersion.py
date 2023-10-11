from collections import deque
import sys
import math
import heapq
import random
import itertools
from functools import cmp_to_key
from fractions import Fraction

def gs():
    return sys.stdin.readline().split()[0]

def gd():
    return float(sys.stdin.readline())

def gi():
    return int(sys.stdin.readline())

def gia():
    return list(map(int,sys.stdin.readline().split()))

def gsa():
    return sys.stdin.readline().split()            

def readGraph(N,M, idx=0, hasDirect=False, hasCost=False):
    G=[]
    if(hasCost):
        G=Graph(N)
    else:
        for i in range(N):
            G.append([])
    for i in range(M):
        E=gia()
        if(not hasCost):
            u=E[0]-(1-idx)
            v=E[1]-(1-idx)
            G[u].append(v)
            if(not hasDirect):
                G[v].append(u)
        else:
            u=E[0]-(1-idx)
            v=E[1]-(1-idx)
            c=E[2]
            G.addEdge(u,v,c)
            if(not hasDirect):
                G.addEdge(v,u,c)
    return G

def ceil_pow2(n):
    x=0
    while((1<<x)<n):
        x+=1
    return x

def uclid(m, n):
    if(n==0):
        return m
    else:
        return uclid(n, m%n)
    
#拡張ユークリッドの互除法
def invGcd(a, b):
    a%=b
    if a==0: return b,0
    s, t=b, a
    m0, m1=0,1
    
    while(t):
        u=s//t
        s-=t*u
        m0-=m1*u
        s,t=t,s
        m0,m1=m1,m0
        
    if m0<0: m0+=b//s
    return s,m0

# 逆元を求める。存在しないときは-1を返す
def invMod(x, m):
    z=invGcd(x,m)
    if(z[0]!=1):return -1
    return z[1]
    
#約数取得
def yakusu(n):
    l=[]
    for i in range(1, n+1):
        if(i*i>n):
            break;
        if(n%i==0):
            l.append(i)
            if(n/i!=i):
                l.append(n//i)
    
    return l

def insuB(n):
    l=[]
    i=2
    while(i*i<=n):
        if(n%i==0):
            l.append(i)
            n=n//i
        else:
            i+=1
            
    if(n!=1):
        l.append(n)
    
    return l

def insuBm(n):
    m=dict()
    for i in range(2, n):
        if(i*i>n):break
        while(n%i==0):
            if(not(i in m)):
                m[i]=1
            else:
                m[i]+=1
            n=n//i
    if(n!=1):
        if(not(n in m)):
            m[n]=1
        else:
            m[n]+=1
    return m

KAIJO_DP=[0]*4000000

def kaijo(n, mod):
    if(n<=1):
        return 1
    if(KAIJO_DP[n]!=0):
        return KAIJO_DP[n]
    ans=n*kaijo(n-1, mod)
    ans%=mod
    KAIJO_DP[n]=ans
    return ans

g1 = [1, 1] # 元テーブル
g2 = [1, 1] #逆元テーブル
inverse = [0, 1] #逆元テーブル計算用テーブル
def beforeCmb(num,mod):
    for i in range( 2, num + 1 ):
        g1.append( ( g1[-1] * i ) % mod )
        inverse.append( ( -inverse[mod % i] * (mod//i) ) % mod )
        g2.append( (g2[-1] * inverse[-1]) % mod )

def cmb(n, r, mod):
    if ( r<0 or r>n ):
        return 0
    r = min(r, n-r)
    return g1[n] * g2[r] * g2[n-r] % mod

def isP(n):
    if(n==1):
        return False
    for i in range(2, math.floor(math.sqrt(n))+1):
        if(n%i==0):
            return False
    return True

def nextCombination(sub):
    x=sub & (-sub)
    y=sub+x
    return (((sub & ~y) // x) >> 1) | y      

class FenwickTree:
    
    def __init__(self, n):
        self.N = n
        self.data = [0] * n
        
    def add(self, p, x):
        if(p<0 or p >= self.N):
            return None
        
        p+=1
        while(p<=self.N):
            self.data[p-1]+=x
            p+=p&-p;

    def get(self, l, r):
        if(l<0 or l>r or r>self.N):
            return -(1<<64)
        
        return self._innerSum(r) - self._innerSum(l)
    
    def _innerSum(self, r):
        s=0
        while(r>0):
            s+=self.data[r-1]
            r-=r&-r
            
        return s
    
class FenwickTreeImos:
    
    def __init__(self, n):
        self.fw = FenwickTree(n+1)
        
    def add(self, s, t, x):
        self.fw.add(s, x)
        self.fw.add(t, -x)

    def get(self, i):
        return self[i]
    
    def __getitem__(self, key):
        return self.fw.get(0, key+1)
    
class Edge:
    
    def __init__(self, f, t, c):
        self._from=f
        self._to=t
        self._cost=c
        
    def getStart(self):
        return self._from
    
    def getEnd(self):
        return self._to
    
    def getDistance(self):
        return self._cost
    
    def setDistance(self, c):
        self._cost =c

class Graph:
    
    def __init__(self, n):
        self.gla=[]
        self.prev=[-1]*n
        for i in range(n):
            self.gla.append([])

    def addEdge(self, u, v, l):
        e=Edge(u, v, l)
        self.gla[u].append(e)
        
    def removeEdge(self, u, v):
        l=self.gla[u]
        for edge in l:
            if(edge.getStart() == u and edge.getEnd()==v):
                l.remove(edge)
                
    def changeLength(self, u, v, d):
        l=self.gla[u]
        for edge in l:
            if(edge.getStart() == u and edge.getEnd()==v):
                edge.setDistance(d)
                break
                
    def getVertexNum(self):
        return len(self.gla)
    
    def getEdgeLength(self, u, v):
        l=self.gla[u]
        for edge in l:
            if(edge.getStart() == u and edge.getEnd()==v):
                return edge.getDistance()
                
        return 1<<64
    
    def dijkstra(self, start):
        d=[1<<64] * self.getVertexNum()
        d[start]=0
        q=[]
        heapq.heappush(q, (0, start))
        self.prev[start]=-1
        while(len(q)!=0):
            p=heapq.heappop(q)
            if(p[0]>d[p[1]]):
                continue
            el=self.gla[p[1]]
            for edge in el:
                to=edge.getEnd()
                fr=edge.getStart()
                cost=edge.getDistance()
                if(d[to]>d[fr]+cost):
                    d[to]=d[fr]+cost
                    self.prev[to]=fr
                    heapq.heappush(q, (d[to], to))
        
        return d
    
    def getPath(self, v):
        path=[]
        while(v!=-1):
            path.append(v)
            v=self.prev[v]
            
        path.reverse()
        return path

class SegTree:
    
    def __init__(self, v, op, e):
        self.n=len(v)
        self.log=ceil_pow2(self.n)
        self.size=1<<self.log
        self.op=op
        self.e=e
        self.d=[e]*(2*self.size)
        for i in range(self.n):
            self.d[self.size+i]=v[i]
        for i in range(self.size-1, 0, -1):
            self._update(i)
        
    def setVal(self, p, x):
        p+=self.size
        self.d[p]=x
        for i in range(1, self.log+1):
            self._update(p>>i)
            
    def getVal(self, p):
        return self.d[p+self.size]
    
    def prod(self, l, r):
        sml=self.e
        smr=self.e
        l+=self.size
        r+=self.size
        while(l<r):
            if(l&1 != 0):
                sml=self.op(sml, self.d[l])
                l+=1
            if(r&1 != 0):
                r-=1
                smr=self.op(self.d[r], smr)
            l>>=1
            r>>=1
                
        return self.op(sml,smr)
    
    def allProd(self):
        return self.d[1]
    
    def maxRight(self, l, f):
        if(l==self.n):return self.n
        l+=self.size
        sm=self.e
        while True:
            while(l%2==0):
                l>>=1
            if(not f(self.op(sm,self.d[l]))):
                while(l<self.size):
                    l=2*l
                    if(f(self.op(sm, self.d[l]))):
                        sm=self.op(sm, self.d[l])
                        l+=1
                        
                return l-self.size
            
            sm=self.op(sm, self.d[l])
            l+=1
            if((l & -l) == l):break
            
        return self.n
    
    def minLeft(self, r, f):
        if(r==0):return 0
        r+=self.size
        sm=self.e
        while(True):
            r-=1
            while(r>1 and r%2==1):
                r>>=1
            if(not f(self.op(self.d[r], sm))):
                while(r<self.size):
                    r=2*r+1
                    if(f(self.op(self.d[r],sm))):
                        sm=self.op(self.d[r],sm)
                        r-=1
                
                return r+1-self.size
            
            sm=self.op(self.d[r], sm)
            if((r&-r)==r):break
            
        return 0
    
    def _update(self, k):
        self.d[k]=self.op(self.d[2*k], self.d[2*k+1])         

    
def main_():
    N,A,B,C,D=gia()
    sa=abs(B-C)
    ans="Yes"
    if(sa>=2):
        ans="No"
    if(B==0 and C==0 and A>0 and D>0):
        ans="No"
    
    print(ans)        
        
        
    
main_()