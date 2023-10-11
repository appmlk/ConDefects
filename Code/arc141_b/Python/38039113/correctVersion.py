import math


N, M = map(int, input().split())
Mod = 998244353
if math.floor(math.log2(M)) + 1 < N:
    print(0)
    exit()

#dp[i] = 最上位ビットがiになる場合の数
a = [0] * 70
for i in range(70):
    if 2 * 2 ** i <= M:
        a[i] = 2 ** i
    else:
        a[i] = max(0, M - 2 ** i + 1)
a = [0] + a
dp = a[:]

for i in range(N - 1):
    _dp = [0] * 70
    sumdp = [0] * 70
    for j in range(70):
        sumdp[j] = sumdp[j - 1] + dp[j]
    for k in range(i, 70):
        _dp[k] = (sumdp[k - 1] * a[k]) % Mod
    dp = _dp

print(sum(dp) % Mod)