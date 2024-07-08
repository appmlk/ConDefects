S = input()

def inverse(n, d):
    return n * pow(d, -1, MOD) % MOD

MOD = 998244353
comb = [[0]*len(S) for _ in range(len(S))]
for i in range(len(S)):
    for j in range((i+1)//2+1):
        if j == 0:
            comb[i][j] = i+1
        else:
            comb[i][j] = comb[i][j-1]
            comb[i][j] *= inverse(i+1-j, j+1)
            comb[i][j] %= MOD

cnt = [0]*26
for i in range(len(S)):
    cnt[ord(S[i])-ord("a")] += 1

dp = [[0]*(len(S)+1) for _ in range(27)]
dp[0][0] = 1
SUM = cnt[0]
for i in range(1, 27):
    for j in range(SUM+1):
        dp[i][j] += dp[i-1][j]
        dp[i][j] %= MOD
        for k in range(1, min(j+1, cnt[i-1]+1)):
            J, K = j, k
            if J == K:
                dp[i][j] += dp[i-1][j-k]
                dp[i][j] %= MOD
            else:
                if j % 2 == 0 and K > j//2:
                    K -= (K-j//2)*2
                elif j & 2 == 1 and K > j//2:
                    K -= (K-j//2-1)*2+1
                dp[i][j] += comb[J-1][K-1]*dp[i-1][j-k]
                dp[i][j] %= MOD
    if i < 26:
        SUM += cnt[i]

ans = 0
for i in range(1, len(S)+1):
    ans += dp[-1][i]
    ans %= MOD

print(ans)