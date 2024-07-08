from itertools import permutations
n, m = map(int, input().split())
graph = [[0]*(n+1) for i in range(n+1)]

for i in range(m):
  a,b,c = map(int, input().split())
  graph[a][b] = c
  graph[b][a] = c

# for i in range(1,n+1):
#   print(*graph[i])

P = permutations(range(1,n+1))
ans = 0
for p in P:
  s = 0
  for j in range(1,n):
    c = graph[p[j-1]][p[j]]
    if c == 0:
      break
    s += c
  # print(p, s)
  ans = max(ans, s)
print(ans)