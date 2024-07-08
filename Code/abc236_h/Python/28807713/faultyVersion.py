from math import gcd

import os
import sys

import numpy as np


def solve(inp):
    n = inp[0]
    m = inp[1]
    ddd = inp[2:]
    MOD = 998244353
    n2 = 1 << n
    INF = 1 << 60

    def pop_count_32(n):
        c = (n & 0x55555555) + ((n >> 1) & 0x55555555)
        c = (c & 0x33333333) + ((c >> 2) & 0x33333333)
        c = (c & 0x0f0f0f0f) + ((c >> 4) & 0x0f0f0f0f)
        c = (c & 0x00ff00ff) + ((c >> 8) & 0x00ff00ff)
        c = (c & 0x0000ffff) + ((c >> 16) & 0x0000ffff)
        return c

    def bit_length(n):
        l = 0
        while n:
            n >>= 1
            l += 1
        return l

    lcms = np.ones(n2, np.int64)
    all_same = np.ones(n2, np.int64)
    pop_count = np.zeros(n2, np.int64)

    for i in range(1, n2):
        lsb = i & -i
        ot = lcms[i ^ lsb]
        if ot == INF:
            lcms[i] = INF
        else:
            d = ddd[bit_length(lsb) - 1]
            g = gcd(ot, d)
            if INF // ot <= d // g:
                lcms[i] = INF
            else:
                lcms[i] = d // g * ot
        all_same[i] = (m // lcms[i]) % MOD
        pop_count[i] = pop_count_32(i)

    facts = np.ones(n + 1, np.int64)
    for i in range(2, n + 1):
        facts[i] = facts[i - 1] * i % MOD

    dp = np.zeros(n2, np.int64)
    dp[0] = 1
    for i in range(1, n2):
        lsb = i & -i
        j = i ^ lsb
        k = j

        res = all_same[lsb] * dp[j] % MOD
        while k:
            p = pop_count[k]
            if p % 2 == 0:
                res += all_same[k | lsb] * facts[p] % MOD * dp[j ^ k] % MOD
                # print(f'+ i:{i:04b} j:{j:04b} k:{k:04b} p:{p}', all_same[k | lsb], facts[p], dp[j ^ k])
            else:
                res -= all_same[k | lsb] * facts[p] % MOD * dp[j ^ k] % MOD
                # print(f'- i:{i:04b} j:{j:04b} k:{k:04b} p:{p}', all_same[k | lsb], facts[p], dp[j ^ k])
            res %= MOD
            k = (k - 1) & j
        dp[i] = res
        # print(dp)

    return dp[-1]


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
