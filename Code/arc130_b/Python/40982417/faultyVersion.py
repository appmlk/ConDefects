h, w, c, q = map(int, input().split())
dv = set()
db = set()
ans = [0] * c
queries = [list(map(lambda x: int(x)-1, input().split())) for _ in range(q)]
for t, n, c in reversed(queries):
  if t == 2:
    if n not in db:
      ans[c] += w - len(dv)
      db.add(n)
  else:
    if n not in dv:
      ans[c] += h - len(db)
      dv.add(n)
print(*ans)