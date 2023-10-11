from collections import deque
h,w = map(int, input().split())
mp = [list(input()) for i in range(h)]
Q = deque()
root = [[-1]* w for i in range(h)]
dist = [(0, 1), (0, -1), (1, 0), (-1, 0)]
cnt = 0
Q.append((0,0))
root[0][0] = 0
while Q:
  y, x = Q.popleft()
  for dy, dx in dist:
    y2 = y + dy
    x2 = x + dx
    if not (0 <= y2 < h and 0 <= x2 < w):
      continue
    if mp[y2][x2] == "#":
      continue
    if root[y2][x2] == -1:
      root[y2][x2] = root[y][x] + 1
      Q.append([y2, x2])
ans = sum(root,[])
print(max(ans) + 1)