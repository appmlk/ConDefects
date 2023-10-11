from collections import deque

n, d = map(int, input().split())
wall = [list(map(int, input().split())) for _ in range(n)]

wall.sort(key=lambda x : x[1])
q = deque(wall)
ans = 0
now = 0
while q:
    nex = q.popleft()
    if nex[0] <= now:
        continue
    now = nex[1] + d - 1
    ans += 1

print(ans)