n = int(input())
ab = [list(map(int,input().split())) for i in range(n-1)]
graph = [[] for i in range(n+1)]
for a,b in ab:
  graph[a].append(b)
  graph[b].append(a)

conn = [len(graph[i]) for i in range(n+1)]
leaf = []
for i in range(1,n+1):
  if conn[i] == 1:
    leaf.append(i)

ans = [0 for i in range(n+1)]

while len(leaf) >= 2:
  x = leaf.pop()
  y = leaf.pop()
  ans[x] = y
  ans[y] = x
  for xy in graph[x]:
    if conn[xy] > 1:
      conn[xy] -= 1
      if conn[xy] == 1:
        leaf.append(xy)
  for yx in graph[y]:
    if conn[yx] > 1:
      conn[yx] -= 1
      if conn[yx] == 1:
        leaf.append(yx)

if len(leaf) == 1:
  ans[leaf[0]] = leaf[0]

print(*ans[1:])