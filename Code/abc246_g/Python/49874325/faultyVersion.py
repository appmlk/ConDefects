N = int(input())
A = list(map(int,input().split()))
A = [-1] + A
G = [[] for i in range(N)]
for _ in range(N-1):
  u,v = map(int,input().split())
  u -= 1
  v -= 1
  G[u].append(v)
  G[v].append(u)

dist = [-1 for i in range(N)]
dist[0] = 0
todo = [0]
RG = [[] for i in range(N)]
H = []
while len(todo):
  u = todo.pop()
  for v in G[u]:
    if dist[v] >= 0:
      continue
    dist[v] = dist[u] + 1
    RG[u].append(v)
    todo.append(v)
for u in range(N):
  H.append((dist[u],u))
H.sort()
l = 0
r = 10**9
while r - l > 1:
  X = (l + r)//2
  B = [0 for i in range(N)]
  for i in range(N):
    if A[i] >= X:
      B[i] = 1
  dp = [0 for i in range(N)]
  for h in H:
    todo.append(h)
  while len(todo):
    d,u = todo.pop()
    res = -1
    for v in RG[u]:
      res += dp[v]
    res = max(res,0)
    dp[u] = res + B[u]
  if dp[0] == 0:
    r = X
  else:
    l = X
print(l)