from math import gcd
import sys
input = sys.stdin.readline
sys.setrecursionlimit(10**9)

MOD = 998244353
DDD = 64
#DDD = 10

def lcm(a, b):
    return a // gcd(a, b) * b

N, A1, A2, A3 = map(int, input().split())

binNs = bin(N)[2:].zfill(DDD)[::-1]
#print('# binNs:', binNs)

dp = [[[[[[0]*(A3) for m in range(A2)] for l in range(A1)] for k in range(3)] for j in range(3)] for i in range(3)]
dp[0][0][0][0][0][0] = 1
coef = 1
for d in range(DDD):
    Nd = int(binNs[d])
#    print('\n##### d:', d, '/ Nd:', Nd)
#    for i in range(3):
#        for j in range(3):
#            for k in range(3):
#                print('# (i, j, k):', (i, j, k), '/ dp[i][j][k]:', dp[i][j][k])
    dp2 = [[[[[[0]*(A3) for m in range(A2)] for l in range(A1)] for k in range(3)] for j in range(3)] for i in range(3)]
    for st1 in range(3):
        for st2 in range(3):
            for st3 in range(3):
                for r1 in range(A1):
                    for r2 in range(A2):
                        for r3 in range(A3):
                            dpNow = dp[st1][st2][st3][r1][r2][r3]
                            if dpNow == 0:
                                continue
                            for x1 in range(2):
                                nst1 = st1
                                if x1 > Nd:
                                    nst1 = 1
                                elif x1 < Nd:
                                    nst1 = -1
                                nr1 = (r1 + x1*coef) % A1
                                for x2 in range(2):
                                    nst2 = st2
                                    if x2 > Nd:
                                        nst2 = 1
                                    elif x2 < Nd:
                                        nst2 = -1
                                    nr2 = (r2 + x2*coef) % A2
                                    x3 = x1^x2
                                    nst3 = st3
                                    if x3 > Nd:
                                        nst3 = 1
                                    elif x3 < Nd:
                                        nst3 = -1
                                    nr3 = (r3 + x3*coef) % A3
                                    dp2[nst1][nst2][nst3][nr1][nr2][nr3] += dpNow
                                    dp2[nst1][nst2][nst3][nr1][nr2][nr3] %= MOD
    dp = dp2
    coef <<= 1
    coef %= MOD

ans = 0
for st1 in [-1, 0]:
    for st2 in [-1, 0]:
        for st3 in [-1, 0]:
            ans += dp[st1][st2][st3][0][0][0]
            ans %= MOD
#print('# ans:', ans)

L = lcm(A1, A2)
ans -= N//L
L = lcm(A1, A3)
ans -= N//L
L = lcm(A2, A3)
ans -= N//L
ans -= 1
ans %= MOD

print(ans)
