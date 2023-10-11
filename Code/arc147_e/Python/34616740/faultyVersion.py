import bisect
import copy
import decimal
import fractions
import heapq
import itertools
import math
import random
import sys
import time
from collections import Counter,deque,defaultdict
from functools import lru_cache,reduce
from heapq import heappush,heappop,heapify,heappushpop,_heappop_max,_heapify_max
def _heappush_max(heap,item):
    heap.append(item)
    heapq._siftdown_max(heap, 0, len(heap)-1)
def _heappushpop_max(heap, item):
    if heap and item < heap[0]:
        item, heap[0] = heap[0], item
        heapq._siftup_max(heap, 0)
    return item
from math import gcd as GCD
read=sys.stdin.read
readline=sys.stdin.readline
readlines=sys.stdin.readlines

def Compress(lst):
    decomp=sorted(list(set(lst)))
    comp={x:i for i,x in enumerate(decomp)}
    return comp,decomp

class Dual_Segment_Tree:
    def __init__(self,N,f_act,e_act,operate,lst):
        self.N=N
        self.f_act=f_act
        self.e_act=e_act
        self.operate=operate
        self.lst=[None]*self.N
        for i,x in enumerate(lst):
            self.lst[i]=x
        self.segment_tree_act=[self.e_act]*(self.N+self.N)

    def __getitem__(self,i):
        if type(i) is int:
            if -self.N<=i<0:
                i+=self.N*2
            elif 0<=i<self.N:
                i+=self.N
            else:
                raise IndexError("list index out of range")
            self.Propagate_Above(i)
            return self.Operate_At(i)
        else:
            a,b,c=i.start,i.stop,i.step
            if a==None or a<-self.N:
                a=0
            elif self.N<=a:
                a=self.N
            elif a<0:
                a+=self.N
            if b==None or self.N<=b:
                b=self.N
            elif b<-self.N:
                b=0
            elif b<0:
                b+=self.N
            return self.lst[slice(a,b,c)]

    def Operate_At(self,i):
        return self.operate(self.lst[i-self.N],self.segment_tree_act[i])

    def Propagate_At(self,i):
        self.segment_tree_act[i<<1]=self.f_act(self.segment_tree_act[i<<1],self.segment_tree_act[i])
        self.segment_tree_act[i<<1|1]=self.f_act(self.segment_tree_act[i<<1|1],self.segment_tree_act[i])
        self.segment_tree_act[i]=self.e_act

    def Propagate_Above(self,i):
        H=i.bit_length()-1
        for h in range(H,0,-1):
            self.Propagate_At(i>>h)

    def Operate_Range(self,a,L=None,R=None):
        if L==None:
            L=self.N
        else:
            L+=self.N
        if R==None:
            R=self.N*2
        else:
            R+=self.N
        L0=L//(L&-L)
        R0=R//(R&-R)-1
        self.Propagate_Above(L0)
        self.Propagate_Above(R0)
        while L<R:
            if L&1:
                self.segment_tree_act[L]=self.f_act(self.segment_tree_act[L],a)
                L+=1
            if R&1:
                R-=1
                self.segment_tree_act[R]=self.f_act(self.segment_tree_act[R],a)
            L>>=1
            R>>=1

    def Update(self):
        for i in range(1,self.N):
            self.Propagate_At(i)
            self.segment_tree_act[i]=self.e_act

    def __str__(self):
        import copy
        segment_tree_act=copy.deepcopy(self.segment_tree_act)
        for i in range(1,self.N):
            segment_tree_act[i<<1]=self.f_act(segment_tree_act[i<<1],segment_tree_act[i])
            segment_tree_act[i<<1|1]=self.f_act(segment_tree_act[i<<1|1],segment_tree_act[i])
            segment_tree_act[i]=self.e_act
            segment_tree_act[i]=self.e_act
        return "["+", ".join(map(str,[self.operate(x,a) for x,a in zip(self.lst,segment_tree_act[self.N:])]))+"]"

N=int(readline())
A,B=[],[]
for _ in range(N):
    a,b=map(int,readline().split())
    A.append(a)
    B.append(b)
comp,decomp=Compress(A+B)
for i in range(N):
    A[i]=comp[A[i]]
    B[i]=comp[B[i]]
sorted_A=sorted(A)
sorted_B=sorted(B)
for a,b in zip(sorted_A,sorted_B):
    if a<b:
        print(-1)
        exit()
le=len(comp)
imos=[0]*le
ans=N
for a,b in zip(A,B):
    if a<b:
        imos[a]-=1
        imos[b]+=1
        ans-=1
for i in range(1,le):
    imos[i]+=imos[i-1]
AB=[[] for a in range(le)]
for a,b in zip(A,B):
    if a>b:
        AB[b].append(a)
queue=[]
DST=Dual_Segment_Tree(le,lambda x,y:x+y,0,lambda x,a:x+a,imos)
for a in range(le-1):
    for b in AB[a]:
        _heappush_max(queue,b)
    while DST[a]<0:
        b=heappop(queue)
        DST.Operate_Range(1,a,b)
        ans-=1
print(ans)