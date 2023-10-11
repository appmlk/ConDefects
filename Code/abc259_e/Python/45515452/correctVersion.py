n=int(input())
from collections import defaultdict as df
d=df(list)
exist=set()
num=[[] for i in range(n)]
for i in range(n):
  m=int(input())
  for __ in range(m):
    a,b=map(int,input().split())
    num[i].append([a,b])
    d[a].append([i,b])
    if a not in exist:
      exist.add(a)
for k in exist:
  d[k].sort(key=lambda p:p[1])
ans=1
for i in range(n):
  for k in num[i]:
    if d[k[0]][-1][1]==k[1] and ((len(d[k[0]])>1 and d[k[0]][-2][1]<k[1]) or len(d[k[0]])==1):
      ans+=1
      break
if ans>n:
  ans-=1
print(ans)
  
  