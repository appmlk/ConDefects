def calc1(a, m, t):
  if t == -1:
    return len(a) // 2
  if len(a) < 2:
    return 0
  bit = 1 << t
  b = []
  c = []
  for i in a:
    if i & bit: c.append(i)
    else: b.append(i)
  if m & bit: return calc2(b, c, m, t - 1)
  else:
    if len(b) > len(c): b, c = c, b
    res = len(b) + min((len(c) - len(b)) // 2, calc1(c, m, t - 1))
    return res
def calc2(b, c, m, t):
  if t == -1: return min(len(b), len(c))
  if len(b) == 0 or len(c) == 0: return 0
  bit = 1 << t
  d = []
  e = []
  f = []
  g = []
  for i in b:
    if i & bit: e.append(i)
    else: d.append(i)
  for i in c:
    if i & bit: g.append(i)
    else: f.append(i)
  if m & bit:
    return calc2(e, f, m, t - 1) + calc2(d, g, m, t - 1)
  else:
    res = min(len(e), len(f)) + min(len(d), len(g))
    if len(e) > len(f) and len(g) > len(d):
      res += min(len(e) - len(f), len(g) - len(d), calc2(e, g, m, t - 1))
    elif len(e) < len(f) and len(g) < len(d):
      res += min(len(f) - len(e), len(d) - len(g), calc2(d, f, m, t - 1))
    return res
n = int(input())
a = list(map(int, input().split()))
l = 0
r = 10 ** 10
while l + 1 < r:
  m = (l + r) // 2
  if calc1(a, m, 29) >= (n + 1) // 2: l = m
  else: r = m
print(l)