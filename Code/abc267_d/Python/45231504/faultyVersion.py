N,M = map(int,input().split())
A = list(map(int,input().split()))

dp = [[0]*N for i in range(M)]
dp[0][0]=A[0]
for i in range(1,N):
    dp[0][i]=max(A[i],dp[0][i-1])

for i in range(1,M):
    for j in range(i,N):
        dp[i][j]=max(dp[i-1][j-1]+A[j]*(i+1),dp[i][j-1])

print(dp[-1][-1])