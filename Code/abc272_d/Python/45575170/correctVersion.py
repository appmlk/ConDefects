# import系 ---
from collections import deque

# 入力用 ---
INT = lambda: int(input())
MI = lambda: map(int, input().split())
MI_DEC = lambda: map(lambda x: int(x) - 1, input().split())
LI = lambda: list(map(int, input().split()))
LI_DEC = lambda: list(map(lambda x: int(x) - 1, input().split()))
LS = lambda: list(input())
LSS = lambda: input().split()

# コード ---
N, M = MI()

can_go = set()

for i in range(-int(M**(1/2)) - 1, int(M**(1/2)) + 2):
    for j in range(-int(M**(1/2)) - 1, int(M**(1/2)) + 2):
        if i**2 + j**2 == M:
            can_go.add((i, j))

visited = set([(0, 0)])
que = deque([(0, 0, 0)])
ans = [[-1]*N for _ in range(N)]

while que:
    now_i, now_j, d = que.popleft()
    ans[now_i][now_j] = d
    
    for i, j in can_go:
        next_i, next_j = now_i + i, now_j + j
        if not (0 <= next_i < N and 0 <= next_j < N):
            continue
        if (next_i, next_j) in visited:
            continue
        visited.add((next_i, next_j))
        que.append((next_i, next_j, d+1))

for a in ans:
    print(*a)

