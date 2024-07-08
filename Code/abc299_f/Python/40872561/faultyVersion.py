import sys
input = sys.stdin.readline

mod = 998244353
S = list(map(lambda c: ord(c)-ord('a'), input().rstrip()))
N = len(S)
ans = 0

for k in range(1, N):  # beginning of the second T
    l = -1
    for i in range(k):
        if S[i] == S[k]:
            l = i
            break
    if l == -1:
        continue

    dp = [[0] * N for _ in range(k)]
    last1 = [0] * 26
    last1[S[l]] = l
    dp[l][k] = 1
    cumsum = [[0] * (N+1) for _ in range(k+1)]
    for j in range(k+1, N+1):
        cumsum[l+1][j] = 1
    for i in range(l+1, k):
        cumsum[i+1][k+1] = dp[i][k] + cumsum[i][k+1] + \
            cumsum[i+1][k] - cumsum[i][k]
        last2 = [0] * 26
        last2[S[k]] = k
        for j in range(k+1, N):
            if S[i] == S[j]:
                ii = last1[S[i]]
                jj = last2[S[j]]
                dp[i][j] = cumsum[i][j] - cumsum[ii][j] - \
                    cumsum[i][jj] + cumsum[ii][jj]
            cumsum[i+1][j+1] = dp[i][j] + cumsum[i][j+1] + \
                cumsum[i+1][j] - cumsum[i][j]
            last2[S[j]] = j
        last1[S[i]] = i
    ans += cumsum[k][N] - cumsum[last1[S[k]]][N]
print(ans)
