import bisect

N = int(input())
P = list(map(int, input().split()))
Q = list(map(int, input().split()))
Qinv = {}
for i, q in enumerate(Q):
    Qinv[q] = i

S = []
for i, p in enumerate(P):
    A = []
    for x in range(p, N + 1, p):
        j = Qinv[x]
        A.append(j)
    A.sort()
    A.reverse()
    S += A
# print(S)
inf = 10 ** 18
dp = [inf] * len(S)
ans = 1
for i, y in enumerate(S):
    if i == 0:
        dp[0] = y
        continue

    "dp[m] >= y"
    m = bisect.bisect_left(dp, y)
    dp[m] = y
    ans = max(ans, m)
# print(dp)
print(ans + 1)