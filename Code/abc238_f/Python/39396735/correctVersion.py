n,k = map(int,input().split())
p = list(map(int,input().split()))
q = list(map(int,input().split()))
mod = 998244353

a = [0]*(n)
for i in range(n):
  a[p[i]-1] = q[i]-1


dp = [[0]*(n+1) for i in range(k+1)] 
dp[0][n] = 1
for i in range(n):
  ai = a[i]
  new = [[0]*(n+1) for i in range(k+1)] 
  for j in range(k+1):
    for mini in range(n+1):
      if mini >= ai and j >= 1:
        new[j][mini] += dp[j-1][mini]
      if mini >= ai:
        new[j][ai] += dp[j][mini]
      else:
        new[j][mini] += dp[j][mini]
      new[j][mini] %= mod
      new[j][ai] %= mod
  dp = new

print(sum(dp[-1])%mod)


