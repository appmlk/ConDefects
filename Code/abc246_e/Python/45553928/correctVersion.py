from collections import deque
N = int(input())
sy, sx = map(lambda x: int(x) - 1, input().split())
gy, gx = map(lambda x: int(x) - 1, input().split())
S = [ input() for _ in range(N)]
queue = deque([(sy, sx)])
dis = [ [1e32] * N for _ in range(N)]
dis[sy][sx] = 0
dir = [ [1, 1], [1, -1], [-1, 1], [-1, -1]]

def is_ok(y : int, x : int, td : int):
    if not (0 <= y <= N - 1 and 0 <= x <= N - 1):
        return False
    if S[y][x] == "#":
        return False
    if dis[y][x] <= td:
        return False
    return True

while queue:
    y, x = queue.popleft()
    td = dis[y][x] + 1
    for dy, dx in dir:
        ty, tx = y, x
        while True:
            ty, tx = ty + dy, tx + dx
            if not (0 <= ty <= N - 1 and 0 <= tx <= N - 1):
                break
            elif S[ty][tx] == "#":
                break
            elif dis[ty][tx] < td:
                break
            else:
                dis[ty][tx] = td
                queue.append((ty, tx))
print(dis[gy][gx] if dis[gy][gx] < 1e32 else -1)