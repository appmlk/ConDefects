import os
import sys

import numpy as np


def solve(inp):
    def mod_pow(x, a, MOD):
        ret = 1
        cur = x
        while a > 0:
            if a & 1:
                ret = ret * cur % MOD
            cur = cur * cur % MOD
            a >>= 1
        return ret

    def prepare_factorials(n, MOD):
        factorials = np.ones(n + 1, np.int64)
        for m in range(1, n + 1):
            factorials[m] = factorials[m - 1] * m % MOD
        inversions = np.ones(n + 1, np.int64)
        inversions[n] = mod_pow(factorials[n], MOD - 2, MOD)
        for m in range(n, 1, -1):
            inversions[m - 1] = inversions[m] * m % MOD
        return factorials, inversions

    n, m = inp
    MOD = 998244353
    INV2 = 499122177
    facts, finvs = prepare_factorials(n * n, MOD)

    def ncr(n, r):
        if n < r:
            return 0
        return facts[n] * finvs[r] % MOD * finvs[n - r] % MOD

    n2 = min(m, (n // 2) * (n - n // 2))
    # i 頂点 j 辺 の単純二部グラフ、各頂点が属するグループも区別
    dp1 = np.zeros((n + 1, n2 + 1), np.int64)

    # i 頂点 j 辺 の単純連結二部グラフ、各頂点が属するグループも区別
    dp2 = np.zeros((n + 1, n2 + 1), np.int64)

    dp2[1, 0] = 2
    for i in range(1, n + 1):
        dp1[i, 0] = mod_pow(2, i, MOD)
        for j in range(1, n2 + 1):
            tmp = 0
            for k in range(1, i):
                tmp += ncr(i, k) * ncr(k * (i - k), j) % MOD
            dp1[i, j] = tmp % MOD
            for k in range(1, i):
                for l in range(j + 1):
                    tmp -= dp2[k, l] * dp1[i - k, j - l] % MOD * ncr(i - 1, k - 1)
                    tmp %= MOD
            dp2[i, j] = tmp % MOD

    # i 頂点 j 辺 の単純連結二部グラフ、各頂点が属するグループは区別しない場合に変換
    dp2 *= INV2
    dp2 %= MOD

    # i 頂点 j 辺 の単純二部グラフ、各頂点が属するグループは区別しない
    #   残っている最も頂点番号の小さい頂点は必ず選ぶようにして、追加していく
    dp3 = np.zeros((n + 1, n2 + 1), np.int64)
    dp3[0, 0] = 1
    for i in range(n):  # 遷移元 i
        for j in range(n2 + 1):  # 遷移元 j
            if dp3[i, j] == 0:
                continue
            for k in range(1, n - i + 1):  # 何個選ぶか
                for l in range(n2 - j + 1):  # 何辺選ぶか
                    dp3[i + k, j + l] += dp3[i, j] * dp2[k, l] % MOD * ncr(n - i - 1, k - 1)
                    dp3[i + k, j + l] %= MOD

    # i 個の無向辺を少なくとも全て1つ以上選びつつ、区別できる M 個の主体が、向きを決めてそれぞれ1つずつ選ぶ方法の個数
    dp4 = np.zeros(n2 + 1, np.int64)
    for i in range(1, n2 + 1):
        res = 0
        coef = 1
        for j in range(i, 0, -1):
            res += coef * mod_pow(j * 2, m, MOD) * ncr(i, j)
            res %= MOD
            coef *= -1
        dp4[i] = res

    ans = 0
    for j in range(1, n2 + 1):
        ans += dp4[j] * dp3[n, j]
        ans %= MOD

    return ans


SIGNATURE = '(i8[:],)'
if sys.argv[-1] == 'ONLINE_JUDGE':
    from numba.pycc import CC

    cc = CC('my_module')
    cc.export('solve', SIGNATURE)(solve)
    cc.compile()
    exit()

if os.name == 'posix':
    # noinspection PyUnresolvedReferences
    from my_module import solve
else:
    from numba import njit

    solve = njit(SIGNATURE, cache=True)(solve)
    print('compiled', file=sys.stderr)

inp = np.fromstring(sys.stdin.read(), dtype=np.int64, sep=' ')
ans = solve(inp)
print(ans)
