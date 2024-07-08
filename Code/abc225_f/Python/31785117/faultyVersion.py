N, K = map(int, input().split())
S = [input() for _ in range(N)]

rule = []
for _ in range(N):
    for i in range(N-1):
        if S[i] + S[i+1] <= S[i+1] + S[i]:
            pass
        else:
            S[i+1], S[i] = S[i], S[i+1]


dp = [[None]*N for _ in range(N)]

for i in range(N):
    dp[i][0] = min(S[i:])

def dpfunc(i,j):
    if dp[i][j] is not None:
        return dp[i][j]
    
    if i + j == N:
        dp[i][j] = "".join(S[i:])
        return dp[i][j]

    ans = min(dpfunc(i+1,j), S[i] + dpfunc(i+1,j-1))
    dp[i][j] = ans
    return ans

ans = dpfunc(0,K-1)
print(ans)