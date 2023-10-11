import sys

read = sys.stdin.read
readline = sys.stdin.readline
readlines = sys.stdin.readlines


T = int(readline())
mod = 998_244_353

for _ in range(T):
    N = int(readline())
    S = readline()

    dp = [[0, 0] for _ in range((N - 1) // 2 + 1 + 1)]
    dp[0][0] = 1
    for i in range((N - 1) // 2 + 1):
        s = S[i]
        dp[i + 1][0] = dp[i][0]
        dp[i + 1][1] = dp[i][1] * 26 + dp[i][0] * (ord(s) - ord('A'))
        dp[i + 1][1] %= mod

    ans = sum(dp[-1]) % mod
    if N % 2:
        tmp = S[:N//2+1] + S[:N//2][::-1]
        if tmp > S:
            ans -= 1
    else:
        tmp = S[:N//2] + S[:N//2][::-1]
        if tmp > S:
            ans -= 1

    print(ans % mod)
