from atcoder.lazysegtree import LazySegTree
from collections import deque

N = int(input())
LR = [list(map(int, input().split())) for _ in range(N)]

sousa = deque()
s = set()
s.add(10 ** 9 + 1)
s.add(0)
for l, r in LR:
  if r < 10 ** 9:
    sousa.append([l + 1, r + 1, 10 ** 9 + 1, 1])
    s.add(l + 1)
    s.add(r + 1)
    sousa.append([r, r + 1, 10 ** 9 + 1, -1])
    s.add(r)
  if l >= 1:
    sousa.append([0, l + 1, r, 1])
    s.add(l - 1)
    s.add(l + 1)
    s.add(r)
    sousa.append([l, l + 1, r, -1])
    s.add(l)
comp = dict()
comp_rev = dict()
now = 0
for i in sorted(s):
  comp[i] = now
  comp_rev[now] = i
  now += 1
for i in sousa:
  for j in range(3):
    i[j] = comp[i[j]]
    
sousa = deque(sorted(sousa))

INF = 1 << 63

def op(ele1, ele2):
    return max(ele1, ele2)

def mapping(func, ele):
    return func + ele

def composition(func_upper, func_lower):
    return func_upper + func_lower

e = -INF
id_ = 0

lst = [0] * now
seg = LazySegTree(op, e, mapping, composition, id_, lst)

ma = -1
ans = [0, 1]
while len(sousa) != 0:
  l, r1, r2, s = sousa.popleft()
  seg.apply(r1, r2, s)
  while len(sousa) != 0:
    l2, r1, r2, s = sousa.popleft()
    if l2 != l:
      sousa.appendleft([l2, r1, r2, s])
      break
    seg.apply(r1, r2, s)
  val = seg.all_prod()
  if val > ma:
    ma = val
    ans[0] = comp_rev[l]
    ans[1] = comp_rev[seg.max_right(0, lambda x:x < ma)]
print(ans[0], ans[1])