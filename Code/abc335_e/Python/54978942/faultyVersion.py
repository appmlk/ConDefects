def chmax(A, idx, val):
   if A[idx] < val: A[idx] = val

from atcoder.dsu import DSU

INF = float("INF")
N, M = map(int, input().split())
A = list(map(int, input().split()))
G = [[] for v in range(N)]
uf = DSU(N)
for _ in range(M):
   a, b = map(lambda x: int(x) - 1, input().split())
   G[a].append(b)
   G[b].append(a)
   if A[a] == A[b]:
      uf.merge(a, b)


def solve(N, M, A, G, uf):
   def contract(G, uf: DSU):
      N = len(G)
      nG = [[] for v in range(N)]
      for v in range(N):
         for a in G[v]:
            nG[uf.leader(v)].append(uf.leader(a))
      return nG

   G = contract(G, uf)
   dp = [-(INF)]*N
   dp[0] = 1
   for v in sorted(range(N), key=lambda i: A[i]):
      for a in G[v]:
         if A[v] < A[a]:
            chmax(dp, a, dp[v] + 1)
   ans = dp[uf.leader(N-1)]

   return ans


ans = solve(N, M, A, G, uf)
if ans == -INF:
   print(0)
else:
   print(ans)
