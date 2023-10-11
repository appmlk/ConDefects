import sys
input = sys.stdin.readline
from collections import defaultdict
n,m = map(int,input().split())
a = list(map(int,input().split()))
b = list(map(int,input().split()))
ans = [-1 for i in range(m)]
graph = [[] for i in range(n+1)]
for i in range(m):
  ax,bx = a[i],b[i]
  graph[ax].append((bx,i,0))
  graph[bx].append((ax,i,1))
dc = defaultdict(int)
dcc = defaultdict(int)
seen = [0 for i in range(n+1)]
for i in range(1,n+1):
  if seen[i] == len(graph[i])-1:
    continue
  stack = [(i,-1,-1)]
  while stack:
    x,o,d = stack.pop()
    if seen[x] == len(graph[x])-1:
      continue
    if ans[o] >= 0:
      continue
    if o >= 0:
      ans[o] = d
      seen[x] += 1
    for y,o,d in graph[x]:
      if ans[o] >= 0:
        continue
      if seen[y] < len(graph[y])-1:
        if dc[(x,y)] <= 10:
          stack.append((y,o,d))
          dc[(x,y)] += 1
          dcc[(x,y)] = d
        else:
          ans[o] = dcc[(x,y)]
for i in ans:
  print(max(i,0),end="")
print()
