N,M = map(int,input().split())
MOD = 998244353
dp = [[0]*(N+1) for _ in range(N+1)] #dp[i][j]: i個使ってその和がj
dp[0][0] = 1
S = [[0,0] for _ in range(N+1)]
S[0][1] = 1

for i in range(1,N+1):
    for j in range(N+1):
        nj = j-i
        if nj < 0:
            S[j].append(S[j][-1])
            continue
        k_max = min(M,i) #1が最大で何個入っているか
        print(i,j,nj,k_max)
        dp[i][j] = (S[nj][-1]-S[nj][-1-k_max-1])%MOD
        S[j].append((S[j][-1]+dp[i][j])%MOD)

for i in range(1,N+1):
    print(dp[i][-1])