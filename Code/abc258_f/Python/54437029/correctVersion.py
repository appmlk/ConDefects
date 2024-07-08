import sys
input = sys.stdin.readline

def dist(x1, y1, x2, y2):
    return abs(x1 - x2) + abs(y1 - y2)

def push(x, y, B, K):
    if x % B == 0 or y % B == 0:
        return [(x, y, 0)]
    dx, ux = x//B*B, (x+B-1)//B*B
    L = []
    L.append((dx, y, K * abs(x - dx)))
    L.append((ux, y, K * abs(x - ux)))
    dy, uy = y//B*B, (y+B-1)//B*B
    L.append((x, dy, K * abs(y - dy)))
    L.append((x, uy, K * abs(y - uy)))
    return L

def push2(x, y, B):
    L = []
    if y % B != 0:
        dy, uy = y//B*B, (y+B-1)//B*B
        L.append((x, dy, abs(y - dy)))
        L.append((x, uy, abs(y - uy)))
    else:
        dx, ux = x//B*B, (x+B-1)//B*B
        L.append((dx, y, abs(x - dx)))
        L.append((ux, y, abs(x - ux)))
    return L
        

T = int(input())
for _ in range(T):
    B, K, sx, sy, gx, gy = map(int, input().split())
    start = push(sx, sy, B, K)
    goal = push(gx, gy, B, K)
    ans = dist(sx, sy, gx, gy) * K
    for sx, sy, sc in start:
        for gx, gy, gc in goal:
            if (sx == gx and sx % B == 0) or (sy == gy and sy % B == 0):
                ans = min(ans, sc + gc + dist(sx, sy, gx, gy))
                continue
            start2 = push2(sx, sy, B)
            goal2 = push2(gx, gy, B)
            for sx2, sy2, sc2 in start2:
                for gx2, gy2, gc2 in goal2:
                    ans = min(ans, sc + gc + sc2 + gc2 + dist(sx2, sy2, gx2, gy2))
    
    print(ans)