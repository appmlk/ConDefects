import sys,os
from math import gcd,log 
from bisect import bisect as bi
from collections import defaultdict,Counter
input=sys.stdin.readline
I = lambda : list(map(int,input().split()))
from heapq import heappush,heappop
import random as rd

tree = [0]*(10**6)

def merge(xx,yy):
	if xx[0]=="":
		return yy
	if yy[0]=='':
		return xx
	x,k,lt,rt = xx 
	x1,k1,lt1,rt1 = yy
	if lt>lt1:
		x,k,lt,rt = yy 
		x1,k1,lt1,rt1 = xx
	k+=k1
	if x[-1]!=x1[0]:
		k+=1
	return [x[0]+x1[-1],k,lt,rt1]

from math import ceil, log2

class segment_tree:
    # merge(left, right): function used to merge the two halves
    # basef(value): function applied on individual values
    # basev: identity for merge function, merger(value, basev) = value
    # update(node_value, old, new): function to update the nodes
    def __init__(self, array, merge=lambda x,y:x+y, basef=lambda x:x, basev = 0):
        self.merge = merge
        self.basef = basef
        self.basev = basev
        self.n = len(array)
        self.array = array
        self.tree = [0] * ( 2**ceil(log2(len(array))+1) - 1 )
        self.build(array)
    
    def __str__(self):
        return ' '.join([str(x) for x in self.tree])

    def _build_util(self, l, r, i, a):
        if(l==r):
            self.tree[i] = self.basef(l)
            return self.tree[i]
        mid = (l+r)//2
        self.tree[i] = self.merge(self._build_util(l,mid, 2*i+1, a), self._build_util(mid+1, r, 2*i+2, a))
        return self.tree[i]

    def build(self, a):
        self._build_util(0, len(a)-1, 0, a)

    def _query_util(self, i, ln, rn, l, r):
        if ln>=l and rn<=r:
            return self.tree[i]
        if ln>r or rn<l:
            return self.basev
        return self.merge( self._query_util( 2*i+1, ln, (ln+rn)//2, l, r ), self._query_util( 2*i+2, (ln+rn)//2+1, rn, l, r ) )

    def query(self, l, r):
        return self._query_util( 0, 0, self.n-1, l, r )

    def _update_util(self, i, ln, rn, x, v):
        if x>=ln and x<=rn:
            if ln != rn:
                self._update_util( 2*i+1, ln, (ln+rn)//2, x, v )
                self._update_util( 2*i+2, (ln+rn)//2 + 1, rn, x, v )
                self.tree[i] = self.merge(self.tree[2*i+1], self.tree[2*i+2])
            else:
                self.tree[i] = self.basef(ln)

    def update(self, x, v):
        self._update_util( 0, 0, self.n-1, x, v )   
        self.array[x] =v         


# def buildTree(a,n):
#     for i in range(n):
#         tree[n + i] = [s[i],int(a[i]=='A'),int(a[i]=='B'),int(a[i]=='C'),i,i]

#     for i in range(n - 1, 0, -1):
#     	tree[i] = merge(tree[2*i],tree[2*i+1])

# #function to find sum on different range 
# def queryTree(l, r):
#     su = ["",0,0,0,0,0]
#     l += n
#     r += n
#     while l < r:

#         if ((l & 1)>0):
#             su = merge(su,tree[l])
#             l += 1
#         print(su)
#         if ((r & 1)>0):
#             r -= 1
#             su = merge(su,tree[r])
#         print(su)
#         l =l//2
#         r =r//2
#     x,a,b,c,i,j = su
#     return a+b+c - max(a,b,c)
n,q = I()
s = input().strip()
st = segment_tree(s,merge = merge,basef = lambda i: [s[i],0,i,i] ,basev = ["",0,0,0])
#buildTree(s,n)
#print(tree[:40])
for i in range(q):
	l,r = I()
	val,k,_,_ = st.query(l-1,r-1)
	if s[l-1]!=s[r-1]:
		k+=1
	print((k+1)//2)
