N, M = map(int, input().split())
S = input()
C = list(map(int, input().split()))
ans = list(S)

Cs = [[] for _ in range(M+1)]

for i in range(N):
  Cs[C[i]].append(i)

for i in range(1,M+1):
  for j in range(len(Cs[i])):
    ans[Cs[i][j]] = S[Cs[i][j-1]]

print(*ans, sep="")