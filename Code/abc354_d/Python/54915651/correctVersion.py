import sys
import time
import math
import bisect
import heapq
import itertools
from collections import defaultdict
from collections import deque

cur_time = time.perf_counter()

sys.setrecursionlimit( 10 ** 8 )
INF = sys.maxsize
NUL = -1
MOD = 998244353
dy=[0,1,0,-1]
dx=[1,0,-1,0]

def searchL( ls, x ):return bisect.bisect_left( ls, x )
def searchR( ls, x ):return bisect.bisect_right( ls, x )
def factorial( i ): return math.factorial( i )
permIte = itertools.permutations
combIte = itertools.combinations
def arr1( size0, init=0 ):return [init]*size0
def arr2( size0, size1, init=0 ):return [[init]*size1 for _ in range(size0)] if 0<=size1 else [[] for _ in range(size0)]
def arr3( size0, size1, size2, init=0 ):return [[[init]*size2 for _ in range(size1)] for _ in range(size0)] if 0<=size2 else [[[] for _ in range(size1)] for _ in range(size0)]
def arr4( size0, size1, size2, size3, init=0 ):return [[[init]*size2 for _ in range(size1)] for _ in range(size0)] if 0<=size2 else [[[] for _ in range(size1)] for _ in range(size0)]
def ini1( a, init ):
    for i in range(len(a)):a[i]=init
def ini2( a, init ):
    for i in range(len(a)):ini1(a[i],init)
def ini3( a, init ):
    for i in range(len(a)):ini2(a[i],init)
def cp2( arr2 ):return [i[:] for i in arr2]
def cp3( arr3 ):return [cp2(i) for i in arr3]
def joinInt( a ): return ' '.join( [ str(i) for i in a ] )
def readStr():return input()
def readStrX():return [ i for i in input().split() ]
def readStrY(n):return [ input() for i in range( n ) ]
def readStrXY(n):return [ [ i for i in input().split() ] for _ in range( n ) ]
def readChr():return list(input())
def readChrY(n):return [ list(input()) for i in range( n ) ]
def readInt():return int(input())
def readIntX():return [ int(i) for i in input().split() ]
def readIntY(n):return [ int(input()) for i in range( n ) ]
def readIntXY(n):return [ [ int(i) for i in input().split() ] for _ in range( n ) ]

UA=1,2,1,0,4
DA=2,1,0,1,4
US=[1,2,1,0]
DS=[2,1,0,1]
A,B,C,D=readIntX()
hq=(D-B)//2
wq=C//4-(A+3)//4
h0,h1=B%2,D%2
if h0==1 and h1==1:
    h0,h1=0,0
w0,w1=A%4,C%4
SU=4*wq
SD=4*wq
if w0!=0:
    for i in range(w0,4):
        SU+=US[i]
        SD+=DS[i]
if w1!=0:
    for i in range(0,w1):
        SU+=US[i]
        SD+=DS[i]

ans=hq*(SU+SD)
if h0==1:
    ans+=SU
if h1==1:
    ans+=SD
print(ans)


