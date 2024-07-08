#!/usr/bin/env python3

MOD = 998244353

def tri(n):
    n %= MOD
    return ((n + 1) * n // 2) % MOD


def solve():
    a1, a2, a3 = map(int, input().split())
    if a1 < a2:
        a1, a2 = a2, a1

    if not a1 <= a3 <= a1 + 1:
        return 0

    p1 = pow(10, a1 - 1, MOD)
    p2 = pow(10, a2 - 1, MOD)

    if a1 == a2:
        ans = tri(8 * p1)
    else:
        ans = (9 * p1 - 10 * p2) * (9 * p2) + tri(9 * p2)

    if a3 == a1:
        return ans
    else:
        return (9 * p1) * (9 * p2) - ans


t = int(input())
for _ in range(t):
    print(solve())
