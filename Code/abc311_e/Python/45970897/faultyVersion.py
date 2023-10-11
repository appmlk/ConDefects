def solve():
    H, W, N = map(int, input().split())

    Grid = [[1] * (W + 1) for _ in range(H + 1)]
    for i in range(N):
        a, b = map(int, input().split())
        Grid[a][b] = 0

    ans = 0

    ODP = [0] * (W + 1)
    DP = [0] * (W + 1)
    for i in range(1, H + 1):
        ODP, DP = DP, ODP
        Grid_i = Grid[i]
        for j in range(1, W + 1):
            if Grid_i[j]:
                DP[j] = min(ODP[j - 1], ODP[j], ODP[j - 1]) + 1
            else:
                DP[j] = 0

            ans += DP[j]

    return ans

#==================================================
import sys
input=sys.stdin.readline
write=sys.stdout.write

print(solve())
