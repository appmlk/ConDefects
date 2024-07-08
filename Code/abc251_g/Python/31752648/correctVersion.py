INF = 10 ** 18
N = int(input())
P = [tuple(map(int, input().split())) for _ in range(N)]
M = int(input())
move = [tuple(map(int, input().split())) for _ in range(M)]
lines = []

def intercept(dx, dy, x, y):
    if dx == 0: return x
    return dx * y - x * dy

for i in range(N):
    x1, y1 = P[i]
    x2, y2 = P[(i + 1) % N]
    dx = x2 - x1
    dy = y2 - y1
    if intercept(dx, dy, x1, y1) < intercept(dx, dy, *P[(i - 1) % N]): d = 1
    else: d = -1
    inter = d * -INF
    for x, y in move:
        tmpinter = intercept(dx, dy, x1 + x, y1 + y)
        if inter * d < tmpinter * d: inter = tmpinter
    lines.append((dx, dy, d, inter))

Q = int(input())
for _ in range(Q):
    x, y = map(int, input().split())
    for i in range(N):
        dx, dy, d, inter = lines[i]
        if inter * d > intercept(dx, dy, x, y) * d:
            print('No')
            break
    else: print('Yes')