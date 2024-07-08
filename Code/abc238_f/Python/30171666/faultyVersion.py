''' F - Two Exams
https://atcoder.jp/contests/abc238/tasks/abc238_f
'''

import io, os, sys
input = io.BytesIO(os.read(0,os.fstat(0).st_size)).readline  # decode().strip() if str
output = sys.stdout.write

DEBUG = os.environ.get('debug') not in [None, '0']

if DEBUG:
    from inspect import currentframe, getframeinfo
    from re import search

def debug(*args):
    if not DEBUG: return
    frame = currentframe().f_back
    s = getframeinfo(frame).code_context[0]
    r = search(r"\((.*)\)", s).group(1)
    vnames = r.split(', ')
    var_and_vals = [f'{var}={val}' for var, val in zip(vnames, args)]
    prefix = f'{currentframe().f_back.f_lineno:02d}: '
    print(f'{prefix}{", ".join(var_and_vals)}')


INF = float('inf')

# -----------------------------------------

MOD = 998244353

def solve(N, K, P, Q):
    # R[i] = round 2 rank for round 1 rank-i player
    R = [0] * N
    for i, r in enumerate(P):
        R[r - 1] = Q[i] - 1
    
    # dp[i][j][k] = num ways to choose
    # * i = consider first i players
    # * j = min rank of a previous excluded player
    # * k = chosen k players already
    dp = [[[0] * (K+1) for _ in range(N+1)] for _ in range(N+1)]
    dp[0][N][0] = 1

    for i in range(1, N+1):
        r = R[i-1]
        for j in range(N+1):
            for k in range(K+1):
                # include i
                if r < j and k < K: dp[i][j][k+1] = (dp[i][j][k+1] + dp[i-1][j][k]) % MOD
                # exclude i
                dp[i][min(r, j)][k] = (dp[i][min(r, j)][k] + dp[i-1][j][k]) % MOD

    return sum(dp[N][j][K] for j in range(N)) % MOD


def main():
    N, K = list(map(int, input().split()))
    P = list(map(int, input().split()))
    Q = list(map(int, input().split()))
    out = solve(N, K, P, Q)
    print(out)


if __name__ == '__main__':
    main()

