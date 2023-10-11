from itertools import permutations as perm
from itertools import combinations, product, combinations_with_replacement, groupby, accumulate
from fractions import Fraction
from collections import *
from sys import stdin
from bisect import *
from heapq import *

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

arr = lambda l:" ".join(str(n) for n in l)  
#arr([1,2,3,...]) -> "1 2 3 ..." 配列で答えをprintする時に使う

mod = int(1e9)+7
inf = 2**30

[T]=gil()
def query():
	[N,K]=gil()
	
	Tree = [[] for i in range(N+1)]
	P = gil()
	A = gil()

	for i,p in enumerate(P,1):
		p -= 1
		Tree[p].append(i)
		Tree[i].append(p)

	P=[-1]+P
	
	for i in range(N):
				
		num = 0
		s = set()
		q = [i]
		while q:
			now = q.pop()
			if A[now] == -1:
				num += 1
			else:
				s.add(A[now])
			
			for nex in Tree[now]:
				if nex == P[now]-1:
					continue
				q.append(nex)
			
		mex = -1
		need = [0]*(K+1)
		for j in range(K+1):
			if j in s:
				need[j]=1
		
		if need[K] or num > 1:
			continue #"Bob"かもしれない
		
		count = 0
		
		for j in range(K):
			if need[j] == 0:
				count += 1
				
		if count == 0:
			return "Alice"
		if count == 1 and num == 1:
			return "Alice"
	
		return "Bob"	
		
def main():
	for _ in range(T):
		print(query())

if __name__ == '__main__':
	main()