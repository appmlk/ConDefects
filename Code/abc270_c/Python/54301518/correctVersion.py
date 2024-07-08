N, X, Y = map(int, input().split())
G = [[] for _ in range(N+1)]
for _ in range(N-1):
  u, v = map(int, input().split())
  G[u].append(v)
  G[v].append(u)

# 深さ優先探索のときはこれをつけるように！
# また、PyPy は再帰が遅いので CPython を使うように！
import sys
sys.setrecursionlimit(10**6)

def dfs(v, p, s):
  s[v] = True
  for neibor in G[v]:
    if s[neibor]:
      continue
    
    p[neibor] = v
    dfs(neibor, p, s)

prev = [-1 for _ in range(N+1)]
seen = [False for _ in range(N+1)]
dfs(X, prev, seen)
#print(prev)

# ゴールから dist の数値を頼りに逆にたどり、最後に配列を反転させて経路を取得する。
root = list()
now = Y
while (now != -1):
  root.append(now)
  now = prev[now]
root.reverse()
print(*root)
