import sys
input = lambda: sys.stdin.readline().strip()

from collections import defaultdict
d = defaultdict(int)

n=int(input())
a=list(map(int,input().split()))
m=200005

for i in range(n):
  d[a[i]]+=1
  
ans=0

for i in range(1,m):
  for j in range(1,1+m//i):
    k=i*j
    ans+=d[k]*d[i]*d[j]
      
print(ans)    