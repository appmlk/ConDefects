#from collections import defaultdict
#d = defaultdict(int)
#from collections import deque
#import math
#import heapq
#from queue import Queue
#import numpy as np
#Mo=998244353

#t=input()
#n=int(input())
#l,r = list(input().split())
n,m=list(map(int, input().split()))
A = list(map(int, input().split()))
#a= [int(input()) for _ in range(n)]

an=10000000000000
amin = A[0]  
amax = A[1]
B = []
for i in range(2,n):
  B.append(A[i])
B.sort()

for i in range(n-2-m):
  val = max(amin-B[i],0)+max(B[i+m-1]-amax,0)
  if an >val:
    an=val
print(an)

#print(1) if a+n <= m else print(0)
#print(' '.join(map(str,d)))
#print('Yes') if b else print('No')
#print('YES') if b else print('NO')