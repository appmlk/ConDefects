# 大通りだけを通って (sx, sy) -> (gx, gy) にいく最短距離
def path(sx, sy, gx, gy, b):
    if sx == gx or sy == gy:
        return abs(sx - gx) + abs(sy - gy)
    if (sx - sy) % b and (gx - gy) % b:
        if sy % b:
            sx, sy = sy, sx
            gx, gy = gy, gx
        rem = sx % b
        return min(
            path(sx - rem, sy, gx, gy, b) + rem,
            path(sx + b - rem, sy, gx, gy, b) + b - rem,
        )
    return abs(sx - gx) + abs(sy - gy)


t = int(input())
for _ in range(t):
    b, k, sx, sy, gx, gy = map(int, input().split())
    ans = k * (abs(sx - gx) + abs(sy - gy))
    rx = sx % b
    ry = sy % b
    ars = [
        [sx - rx, sy, rx * k],
        [sx + b - rx, sy, (b - rx) * k],
        [sx, sy - ry, ry * k],
        [sx, sy + b - ry, (b - ry) * k],
    ]
    rx = gx % b
    ry = gy % b
    arg = [
        [gx - rx, gy, rx * k],
        [gx + b - rx, gy, (b - rx) * k],
        [gx, gy - ry, ry * k],
        [gx, gy + b - ry, (b - ry) * k],
    ]
    for sx, sy, s1 in ars:
        for gx, gy, s2 in arg:
            ans = min(ans, path(sx, sy, gx, gy, b) + s1 + s2)

    print(ans)
