import sys
import math
# import pypyjit
# pypyjit.set_param('max_unroll_recursion=-1')
MOD = 998_244_353
INF = 10**9

sys.setrecursionlimit(10**6)  # ネスト数制限

N, A1, A2, A3 = map(int, sys.stdin.readline().rstrip().split())

dp = [[[[[0 for _ in range(A3)] for _ in range(A2)] for _ in range(A1)] for _ in range(8)] for _ in range(64)]
dp[0][0][0][0][0] = 1

D = {}
# n, x, f
D[(0, 0, 0)] = 0
D[(0, 1, 0)] = 1
D[(0, 0, 1)] = 1
D[(0, 1, 1)] = 1
D[(1, 0, 0)] = 0
D[(1, 1, 0)] = 0
D[(1, 0, 1)] = 0
D[(1, 1, 1)] = 1

for i in range(63):

    n = N >> i & 1
    # print(n)
    k = pow(2, i)
    p1 = k % A1
    p2 = k % A2
    p3 = k % A3

    for x in range(8):
        x1 = x >> 0 & 1
        x2 = x >> 1 & 1
        x3 = x >> 2 & 1

        y = x1 ^ x2 ^ x3
        # print(f" x {x3}{x2}{x1} -> {y}")

        for f in range(8):
            f1 = f >> 0 & 1
            f2 = f >> 1 & 1
            f3 = f >> 2 & 1

            nf = 4 * D[(n, f3, x3)] + 2 * D[(n, f2, x2)] + D[(n, f1, x1)]
            nf1 = nf >> 0 & 1
            nf2 = nf >> 1 & 1
            nf3 = nf >> 2 & 1
            # print(f"n {n} / f {f3} {x3} -> {nf3} : {f2} {x2} -> {nf2} : {f1} {x1} -> {nf1}")

            for r1 in range(A1):
                nr1 = (p1 * x1 + r1) % A1
                for r2 in range(A2):
                    nr2 = (p2 * x2 + r2) % A2
                    for r3 in range(A3):
                        nr3 = (p3 * x3 + r3) % A3
                        dp[i + 1][nf][nr1][nr2][nr3] += dp[i][f][r1][r2][r3] * (1 - y)
                        dp[i + 1][nf][nr1][nr2][nr3] %= MOD

ans = dp[63][0][0][0][0]
# print(ans)
# マイナス分
ans -= 1  # 0 0 0


def h(A1, A2):
    g = math.gcd(A1, A2)
    # g1 = pow(g, MOD - 2, MOD)
    lcm = ((A1 * A2) // g) % MOD
    # lcm1 = pow(lcm, MOD - 2, MOD)
    print(A1, A2, lcm, N // lcm)
    return N // lcm


ans -= h(A1, A2)
ans -= h(A2, A3)
ans -= h(A3, A1)

ans %= MOD

print(ans)
