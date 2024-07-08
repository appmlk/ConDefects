H, W = map(int, input().split())
S = [list(input()) for _ in range(H)]
for h in range(H):
  for w in range(W):
    if S[h][w] == "S":
      sh, sw = h, w
    elif S[h][w] == "G":
      gh, gw = h, w

for h in range(H):
  w = 0
  flg = False
  while w < W:
    if S[h][w] not in ("*", "."):
      flg = False
    
    if S[h][w] == ">":
      flg = True
    elif flg:
      S[h][w] = "*"
    w += 1
  w = W - 1
  flg = False
  while w >= 0:
    if S[h][w] not in ("*", "."):
      flg = False
    
    if S[h][w] == "<":
      flg = True
    elif flg:
      S[h][w] = "*"
    w -= 1


for w in range(W):
  h = 0
  flg = False
  while h < H:
    if S[h][w] not in ("*", "."):
      flg = False
    
    if S[h][w] == "v":
      flg = True
    elif flg:
      S[h][w] = "*"
    h += 1
  h = 0
  flg = False
  while h >= 0:
    if S[h][w] not in ("*", "."):
      flg = False
    
    if S[h][w] == "^":
      flg = True
    elif flg:
      S[h][w] = "*"
    h -= 1

def can_move(h, w):
  return 0 <= h < H and 0 <= w < W and S[h][w] in ("S", "G", ".")

dh = (0, 1, 0, -1)
dw = (1, 0, -1, 0)

from collections import deque
que = deque()
que.append((sh, sw))
dist = [[-1] * W for _ in range(H)]
dist[sh][sw] = 0
while que:
  fh, fw = que.popleft()
  for i in range(4):
    th = fh + dh[i]
    tw = fw + dw[i]
    if can_move(th, tw) and dist[th][tw] == -1:
      dist[th][tw] = dist[fh][fw] + 1
      que.append((th, tw))
print(dist[gh][gw])