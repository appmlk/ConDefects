n,m,k = map(int,input().split())
l = [list(map(int,input().split())) for i in range(m)]
e = list(map(int,input().split()))
dp = [float('inf')] * (n+1)
dp[1] = 0

for i in range(k):
  a,b,c = l[e[i]-1]
  if dp[a] != float('inf'):
    dp[b] = min(dp[b],dp[a]+c)
print(dp[n])