from itertools import permutations as perm
from itertools import combinations, product, combinations_with_replacement, groupby, accumulate
from fractions import Fraction
from collections import *
from sys import *
from bisect import *
from heapq import *

#@再起回数の上限を上げる
#sys.setrecursionlimit(10**7) # PyPy の再起関数は遅くなるので注意

#import numpy as np
# from math import *
 
g   = lambda : stdin.readline().strip()
#[gl[0], dl[1], ...]
gl  = lambda : g().split()
#gl -> int
gil = lambda : [int(var) for var in gl()]
#[n] = gil("A") -> n = A
#[n,m] = gil("A B") -> n = A, M = B
#n=gil("A B C D ...") -> n = [A, B, C, D, ...]

gfl = lambda : [float(var) for var in gl()]

gcl = lambda : list(g())

gbs = lambda : [int(var) for var in g()]
#[n]= gbs("A") -> n = A

# A=[1,2,3,...] -> "1 2 3 ..." 配列で答えをprintする時に使う
# -> print(*A) ("1 2 3 ...")
# -> print(*A,sep="") ("123...")
INF = 10**10
MOD = 7 + 10**9
[N]=gil()
H=gil()
def main():
	A=[]
	Q=deque()
	S=0
	for i in range(N):
		cc = 1
		while Q and Q[-1][0]<=H[i]:
			(v,c) = Q.pop()
			cc += c
			S -= v*c
		Q.append((H[i],cc))
		S += H[i]*cc
		A.append(S+1)
		#print(Q)

	print(*A)
			
		
		
			
		
			
						
			
			
			
if __name__ == "__main__":
	main()


			

			


