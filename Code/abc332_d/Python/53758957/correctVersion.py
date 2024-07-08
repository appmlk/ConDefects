import sys
sys.setrecursionlimit(10**8)





H, W = map(int, input().split())
A = [list(map(int, input().split())) for _ in range(H)]
B = [list(map(int, input().split())) for _ in range(H)]

def perm(n):
  rt = []
  def iter(s, acc):
    if not s:
      rt.append(acc[:])
    for i in range(len(s)):
      v = s[i]
      s.remove(v)
      acc.append(v)
      iter(s, acc)
      acc.pop()
      s.insert(i, v)
  iter(list(range(n)), [])
  return rt

def tp(tbl):
  H = len(tbl)
  W = len(tbl[0])
  t = [['' for _ in range(H)] for _ in range(W)]
  for i in range(H):
    for j in range(W):
      t[j][i] = tbl[i][j]
  return t

def f(s):
  n = 0
  for i in range(len(s)):
    for j in range(i):
      if s[j] > s[i]:
        n += 1
  return n

ans = float('inf')
for p in perm(W):
  tbl = []
  for i in range(H):
    t = []
    for j in p:
      t.append(A[i][j])
    tbl.append(t)
  if sorted(tbl) == sorted(B):
    At = tp(tbl)
    Bt = tp(B)
    for q in perm(H):
      tbl = []
      for i in range(W):
        t = []
        for j in q:
          t.append(At[i][j])
        tbl.append(t)
      if tbl == Bt:
        ans = min(ans, f(p) + f(q))

if ans == float('inf'):
  ans = -1

print(ans)
