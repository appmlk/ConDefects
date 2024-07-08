n, m = map(int, input().split())
aaa = list(map(int, input().split()))
MOD = 998244353

pos = [-1] * n
for i, a in enumerate(aaa):
    pos[a] = i
min_aaa = min(aaa)

leftmost = [0] * n
rightmost = [0] * n
l = pos[min_aaa]
r = l + 1
for a in range(min_aaa + 1, n):
    if pos[a] == -1:
        leftmost[a] = l
        rightmost[a] = r
    else:
        l = min(l, pos[a])
        r = max(r, pos[a] + 1)

dp = [[0] * (m + 2) for _ in range(m + 1)]
dp[0][-1] = 1
for a in range(n - 1, min_aaa, -1):
    ndp1 = [[0] * (m + 2) for _ in range(m + 1)]  # l が増える方向の累積和
    ndp2 = [[0] * (m + 2) for _ in range(m + 1)]  # r が減る方向の累積和
    if pos[a] != -1:
        continue
    lm = leftmost[a]
    rm = rightmost[a]
    for l in range(lm + 1):
        for r in range(rm, m + 1):
            val = dp[l][r]
            if val == 0:
                continue
            ndp1[l][r] += val
            ndp1[l][r] %= MOD
            ndp1[lm + 1][r] -= val
            ndp1[lm + 1][r] %= MOD

            ndp2[l][r] += val
            ndp2[l][r] %= MOD
            ndp2[l][rm - 1] -= val
            ndp2[l][rm - 1] %= MOD

    for r in range(rm, m + 1):
        for l in range(1, lm + 2):
            ndp1[l][r] += ndp1[l - 1][r]
            ndp1[l][r] %= MOD
    for l in range(lm + 1):
        for r in range(m - 1, rm - 2, -1):
            ndp2[l][r] += ndp2[l][r + 1]
            ndp2[l][r] %= MOD
    for l in range(lm + 1):
        for r in range(rm, m + 1):
            ndp1[l][r] += ndp2[l][r]
            ndp1[l][r] %= MOD

    dp = ndp1


def precompute_factorials(n, MOD):
    f = 1
    factorials = [1]
    for m in range(1, n + 1):
        f = f * m % MOD
        factorials.append(f)
    f = pow(f, MOD - 2, MOD)
    finvs = [1] * (n + 1)
    finvs[n] = f
    for m in range(n, 1, -1):
        f = f * m % MOD
        finvs[m - 1] = f
    return factorials, finvs


if min_aaa == 0:
    ans = 0
    for row in dp:
        ans += sum(row)
    ans %= MOD
else:
    facts, finvs = precompute_factorials(n, MOD)
    x = pow(2, min_aaa - 1, MOD)
    ans = 0
    for l in range(m):
        for r in range(l + 1, m + 1):
            w = r - l
            tmp = facts[w + min_aaa] * finvs[w] % MOD * finvs[min_aaa] % MOD
            ans += tmp * dp[l][r] % MOD * x % MOD
            ans %= MOD

print(ans)
