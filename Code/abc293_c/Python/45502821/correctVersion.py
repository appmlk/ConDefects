# import系 ---
from more_itertools import distinct_permutations

# 入力用 ---
INT = lambda: int(input())
MI = lambda: map(int, input().split())
MI_DEC = lambda: map(lambda x: int(x) - 1, input().split())
LI = lambda: list(map(int, input().split()))
LI_DEC = lambda: list(map(lambda x: int(x) - 1, input().split()))
LS = lambda: list(input())
LSS = lambda: input().split()

# コード ---
H, W = MI()

field = []

for _ in range(H):
    field.append(LI())

ans = 0

for perm in distinct_permutations([(0, 1)] * (H - 1) + [(1, 0)] * (W - 1)):
    is_ok = True
    
    visited = set([field[0][0]])
    now_x, now_y = 0, 0
    
    for x, y in perm:
        now_x += x; now_y += y
        if field[now_y][now_x] in visited:
            is_ok = False
            break
        visited.add(field[now_y][now_x])
    
    if is_ok:
        ans += 1

print(ans)
