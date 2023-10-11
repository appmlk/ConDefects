import sys
sys.setrecursionlimit(10**6)

def f(x):
    if x in dp:
        return dp[x]
    if x // 2 not in dp:
        dp[x // 2] = f(x // 2)
    if x // 3 not in dp:
        dp[x // 3] = f(x // 3)
    return dp[x // 2] + dp[x // 3]

N = int(input())
dp = {0: 1}
print(f(N))