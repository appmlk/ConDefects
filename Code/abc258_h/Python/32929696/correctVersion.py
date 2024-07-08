import sys


def mul(A, v, mod):
    m = len(A)
    n = len(v)
    w = [0]*m
    for i in range(m):
        s = 0
        for k in range(n):
            s += A[i][k] * v[k]
        w[i] = s % mod
    return w

def p2(A, mod):
    n = len(A)
    C = [[0]*n for _ in range(n)]
    for i in range(n):
        for j in range(n):
            s = 0
            for k in range(n):
                s += A[i][k] * A[k][j]
            s %= mod
            C[i][j] = s
    return C

def pow(A, v, e, mod):
    MUL = A
    while e > 0:
        if e&1:
            v = mul(MUL, v, mod)
        MUL = p2(MUL, mod)
        e >>= 1
    return v


def solve():
    sys.setrecursionlimit(500005)
    stdin = sys.stdin

    ni = lambda: int(ns())
    na = lambda: list(map(int, stdin.readline().split()))
    ns = lambda: stdin.readline().strip()

    # dp = [0] * 70
    # dp[0] = 1
    # for i in range(1, len(dp)):
    #     for j in range(1, i+1, 2):
    #         dp[i] += dp[i-j]
    n, S = na()
    a = [0] + na() + [S]
    ep = [0] * len(a)
    ep[0] = -1
    M = [[1, 1], [1, 0]]
    v = [-1, 0]
    mod = 998244353
    for i in range(1, len(a)):
        v = pow(M, v, a[i] - a[i-1], mod)
        ep[i] -= v[1]
        ep[i] %= 998244353
        v[0] += ep[i]
        # v[1] -= ep[i]
    # 3a+5b
    # 5a+8b
    print(ep[-1])

    # ep = [0] * len(a)
    # ep[0] = -1
    # M = [[1, 1], [1, 0]]
    # mod = 998244353
    # for i in range(1, len(a)):
    #     for j in range(i):
    #         u = pow(M, [1, 0], a[i] - a[j], mod)
    #         # print(a[i] - a[j], u)
    #         ep[i] -= ep[j] * u[1]
    #     ep[i] %= 998244353
    # print(ep)
    # print(dp)



if __name__ == "__main__":
    solve()
