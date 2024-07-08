from itertools import accumulate
from bisect import bisect_left


def solve(N):
    p = str(N)
    M = len(str(N))
    dp = [[[0] * 10 for _ in range(2)] for _ in range(M + 1)]
    dp[0][0][0] = 1
    for i in range(M):
        di = int(p[i])
        for smaller in range(2):
            Lim = 10 if smaller else di + 1
            for j in range(10):
                for x in range(Lim):
                    if j == x:
                        continue
                    dp[i + 1][smaller | (x < di)][x] += dp[i][smaller][j]
    s = sum(dp[M][0]) + sum(dp[M][1])
    return s


nines = []
nines_val = []
tmp = 0
for i in range(13):
    tmp *= 10
    tmp += 9
    nines.append(tmp)
    nines_val.append(solve(tmp))
cum = list(accumulate(nines_val, initial=0))


def check(p):
    idx = bisect_left(nines, p)
    res = cum[idx]
    res += solve(p)
    return res


def solve_Test(K):
    l = 1
    r = 1 << 60
    while r - l > 1:
        mid = (l + r) // 2
        if check(mid) >= K:
            r = mid
        else:
            l = mid
    return r


T = int(input())
ans = []
for _ in range(T):
    ans.append(solve_Test(int(input())))
print(*ans, sep="\n")
