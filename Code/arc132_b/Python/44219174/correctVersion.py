#from collections import defaultdict
#d = defaultdict(int)
#from collections import deque
#import math
#import heapq
#from queue import Queue
import numpy as np
#Mo=998244353

#s=input()
n=int(input())
#l,r = list(input().split())
a=list(map(int, input().split()))
#a= [int(input()) for _ in range(n)]

c=0
for i in range(1,n):
  c += a[i-1]<a[i]
j=np.argmin(a)
if c == n-1:
  print(0)
elif c==0:
  print(1)
elif c >1:
  an=min(j, n-j+2)
  print(an)
else:
  an=min(j+2, n-j)
  print(an)
  
  
#print(1) if a+n <= m else print(0)
#print(' '.join(map(str,d)))
#print('Yes') if b else print('No')
#print('YES') if b else print('NO')