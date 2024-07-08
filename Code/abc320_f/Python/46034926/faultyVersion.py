import sys
input=sys.stdin.readline

N,H = map(int,input().split())
X = list(map(int,input().split()))
X = [0] + X
P = [0 for i in range(N+1)]
F = [0 for i in range(N+1)]
for i in range(1,N):
  P[i],F[i] = map(int,input().split())

inf = 10**8
dp = [[[inf for i in range(H+1)] for i in range(H+1)] for i in range(N+1)]
for i in range(H+1):
  dp[0][H][i] = 0

for n in range(N-1):
  d = X[n+1] - X[n]
  for h in range(d,H+1):
    for hh in range(H-d+1):
      if dp[n][h][hh] == inf:
        continue
      #使わない
      dp[n+1][h-d][hh+d] = min(dp[n+1][h-d][hh+d],dp[n][h][hh])
      #往路で使う
      hhh = min(H,h-d+F[n+1])
      dp[n+1][hhh][hh+d] = min(dp[n+1][hhh][hh+d],dp[n][h][hh] + P[n+1])
      #復路で使う
      hhh = hh + d
      if hhh == H:
        for k in range(hhh-F[n+1],hhh+1):
          dp[n+1][h-d][k] = min(dp[n+1][hhh][k],dp[n][h][hh] + P[n+1])
      elif hhh >= F[n+1]:
        dp[n+1][h-d][hhh-F[n+1]] = min(dp[n+1][h-d][hhh-F[n+1]],dp[n][h][hh] + P[n+1])
      
d = X[N] - X[N-1]
ans = inf
for h in range(d,H-d+1):
  ans = min(ans,dp[N-1][h+d][h-d])
if ans == inf:
  ans = -1
print(ans)