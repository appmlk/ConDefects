from sys import setrecursionlimit
H, W = map(int, input().split())
S = [input() for _ in [0]*H]
dir = [(-1, 0), (1, 0), (0, -1), (0, 1)]

setrecursionlimit(300000)
seen = [[False]*W for _ in [0]*H]
def func(y, x, p):
    if y == H-1 and x == W-1:
        print("Yes")
        exit()

    for ay, ax in dir:
        ny, nx = y + ay, x + ax
        if 0 <= ny < H and 0 <= nx < W:
            if seen[ny][nx]:    continue
            seen[ny][nx] = True
            if S[ny][nx] == "snuke"[p]:
                func(ny, nx, (p+1)%5)
    return

if S[0][0] == "s":
    seen[0][0] = True
    func(0, 0, 1)
print("No")