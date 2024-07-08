N = int(input())
A = list(map(int,input().split()))
x = sum(A)//N
for i in range(N):
  A[i] -= x
S = [A[0]]
for i in range(1,N):
  S.append(S[-1] + A[i])
r = S[-1]

dp = [[10**18 for j in range(r+1)] for i in range(N+1)]
dp[0][0] = 0
for i in range(N):
  s = S[i]
  for rr in range(r+1):
    dp[i+1][rr] = min(dp[i][rr],dp[i][max(0,rr-1)]) + abs(rr - s)
print(dp[N][r])