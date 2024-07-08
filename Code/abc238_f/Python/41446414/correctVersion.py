n,k = map(int, input().split())
pq = []
p = list(map(int, input().split()))
q = list(map(int, input().split()))
for i in range(n):
  pq.append((p[i], q[i]))
pq.sort()

a = []
for i in range(n):
  a.append(pq[i][1])

#dp[i][j][m] = 数列のi番目まで考えて、今後jより大の数字はNGとなるようなもので数字をm個選んでいるような選び方
dp = [[[0]*(n+1) for _ in range(n+2)] for _ in range(n)]
mod = 998244353

for i in range(n):
  x = a[i]
  if i == 0:
    dp[i][n+1][1] = 1
    dp[i][x][0] = 1

  else:
    #xを採用する場合
    for j in range(x+1,n+2):
      for m in range(n):
        dp[i][j][m+1] += dp[i-1][j][m]
        dp[i][j][m+1] %= mod
    #xを採用しない場合
    for j in range(n+2):
      for m in range(n+1):
        dp[i][min(x,j)][m] += dp[i-1][j][m]  
        dp[i][min(x,j)][m] %= mod

ans = 0
for j in range(n+2):
  ans += dp[-1][j][k]
  ans %= mod

print(ans)

