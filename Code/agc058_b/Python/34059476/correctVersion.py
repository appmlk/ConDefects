from collections import Counter, defaultdict
# from sortedcontainers import SortedSet, SortedList

MOD = 998244353
n = int(input())
arr = list(map(int, input().split()))

dp = [0] * (n+1)
dp[0] = 1
for i in range(n):
    l, r = i, i+1
    while l > 0 and arr[l-1] < arr[i]:
        l -= 1
    while r < n and arr[r] < arr[i]:
        r += 1
    for k in range(l, r):
        dp[k+1] += dp[k]
        dp[k+1] %= MOD
print(dp[n])