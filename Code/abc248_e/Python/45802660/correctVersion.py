from math import gcd
import sys
M = 10**18
def main():
    input = sys.stdin.readline
    N, K = map(int, input().split())
    XY = [tuple(map(int, input().split())) for _ in range(N)]
    if K == 1:
        return 'Infinity'
    S = set()
    ans = 0
    for i in range(N):
        xi, yi = XY[i]
        for j in range(i):
            xj, yj = XY[j]
            dx, dy = xi - xj, yi - yj
            if dx < 0:
                dx, dy = -dx, -dy
            if dx == 0: dy = 1
            if dy == 0: dx = 1
            g = gcd(dx, dy)
            dx, dy = dx // g, dy // g
            b = M * yi - M * dy * xi // dx if dx != 0 else xi
            if (dx, dy, b) in S: continue
            S.add((dx, dy, b))
            cnt = 2
            for k in range(N):
                if k == i or k == j: continue
                xk, yk = XY[k]
                if dx * (yk - yi) == dy * (xk - xi):
                    cnt += 1
            if cnt >= K:
                ans += 1
    return ans

if __name__ == '__main__':
    print(main())
