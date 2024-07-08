from collections import deque

N, M = map(int, input().split())
E = [[] for _ in range(N)]
for _ in range(M):
  u, v = map(int, input().split())
  E[u - 1].append(v - 1)
  E[v - 1].append(u - 1)

S = tuple(map(int, input()))

stack = deque([(0, None, True)])
visited = [False] * N
P = []
while stack:
  q, p, first = stack.pop()
  if visited[q]:
    if not first and P[-1] != q: P.append(q)
    continue
  visited[q] = True
  P.append(q)

  if first:
    for e in E[q]:
      if e == p: continue
      stack.append((q, None, False))
      stack.append((e, q, True))

L = [None] * N
for i, p in enumerate(P):
  L[p] = i

C = [0] * N
ans = []
for i, p in enumerate(P[1:], 1):
  pprev = P[i - 1]

  ans.append(p)
  C[p] += 1
  C[p] %= 2

  if C[pprev] != S[pprev] and L[pprev] == i - 1:
    ans.append(pprev)
    C[pprev] += 1
    C[pprev] %= 2

    ans.append(p)
    C[p] += 1
    C[p] %= 2

if C[0] != S[0]:
  ans = ans[:-1]

print(len(ans))
print(' '.join(map(str, [a + 1 for a in ans])))
