n,m = map(int, input().split())

from collections import deque

tele = []
g = [[] for _ in range(n)]
for _ in range(m):
  u,v = map(int, input().split())
  u-=1
  v-=1
  if u == -1:
    tele.append(v)
  else:
    g[u].append(v)
    g[v].append(u)

inf = 10**18
#０からn-1への最短距離
d = deque()
d.append(0)
dist1 = [inf]*n
dist1[0] = 0
while d:
  v = d.popleft()
  for x in g[v]:
    if dist1[x] == inf:
      dist1[x] = dist1[v]+1
      d.append(x)

M = dist1[-1]

m1 = inf
for x in tele:
  m1 = min(m1, dist1[x])

#逆方向の最短距離
d = deque()
d.append(n-1)
dist2 = [inf]*n
dist2[n-1] = 0
while d:
  v = d.popleft()
  for x in g[v]:
    if dist2[x] == inf:
      dist2[x] = dist2[v]+1
      d.append(x)

m2 = inf
for x in tele:
  m2 = min(m2, dist2[x])

ans = []
for i in range(n):
  tmp = min(M, dist1[i]+1+m2, m1+1+dist2[i], m1+2+m2)
  if tmp >= inf:
    tmp = -1
  ans.append(tmp)

print(*ans)