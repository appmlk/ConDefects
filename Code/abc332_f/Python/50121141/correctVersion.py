class LazySegmentTree:
  def __init__(self, op, e, mapping, composition, id_, n):
    self._n = len(n) if isinstance(n, list) else n
    self.op = op
    self.e = e
    self.mapping = mapping
    self.composition = composition
    self.id = id_
    self.log = (self._n - 1).bit_length()
    self.size = 1 << self.log
    self.d = [e for _ in range(2 * self.size)]
    self.lz = [id_ for _ in range(self.size)]
    if isinstance(n, list): self.d[self.size: self.size + self._n] = n
    for i in reversed(range(1, self.size)): self._update(i)

  def __repr__(self):
    l, r = 1, 2
    res = []
    np_T = lambda x: [list(x) for x in zip(*x)]
    while r <= self.size:
      res.append(f'{np_T([self.d[l: r], self.lz[l: r]])}')
      l, r = r, r << 1
    res.append(f'{self.d[l: r]}')
    return '\n'.join(res)

  def __setitem__(self, p, x):
    self.set(p, x)

  def __getitem__(self, p):
    return self.get(p)

  def set(self, p, x):  # O(log n)
    p += self.size
    for i in reversed(range(1, self.log + 1)): self._push(p >> i)
    self.d[p] = x
    for i in range(1, self.log + 1): self._update(p >> i)

  def get(self, p):     # O(log n)
    p += self.size
    for i in reversed(range(1, self.log + 1)): self._push(p >> i)
    return self.d[p]

  def prod(self, l, r):  # [l, r)   O(log n)
    if l == r: return self.e
    l += self.size
    r += self.size
    for i in reversed(range(1, self.log + 1)):
      if ((l >> i) << i) != l: self._push(l >> i)
      if ((r >> i) << i) != r: self._push((r - 1) >> i)
    sml, smr = self.e, self.e
    while (l < r):
      if l & 1:
        sml = self.op(sml, self.d[l])
        l += 1
      if r & 1:
        r -= 1
        smr = self.op(self.d[r], smr)
      l >>= 1
      r >>= 1
    return self.op(sml, smr)

  def all_prod(self):             # O(1)
    return self.d[1]

  def apply(self, p, f):          # O(log n)
    p += self.size
    for i in reversed(range(1, self.log + 1)): self._push(p >> i)
    self.d[p] = self.mapping(f, self.d[p])
    for i in range(1, self.log + 1): self._update(p >> i)

  def apply_seg(self, l, r, f):   # O(log n)
    if l == r: return
    l += self.size
    r += self.size
    for i in reversed(range(1, self.log + 1)):
      if ((l >> i) << i) != l: self._push(l >> i)
      if ((r >> i) << i) != r: self._push((r - 1) >> i)
    l2, r2 = l, r
    while l < r:
      if l & 1:
        self._all_apply(l, f)
        l += 1
      if r & 1:
        r -= 1
        self._all_apply(r, f)
      l >>= 1
      r >>= 1
    l, r = l2, r2
    for i in range(1, self.log + 1):
      if ((l >> i) << i) != l: self._update(l >> i)
      if ((r >> i) << i) != r: self._update((r - 1) >> i)

  def max_right(self, l, f):   # O(log n)
    if l >= self._n: return self._n
    l = max(l, 0) + self.size
    for i in reversed(range(1, self.log + 1)):
      self._push(l >> i)
    sm = self.e
    while True:
      while l % 2 == 0: l >>= 1
      if not f(self.op(sm, self.d[l])):
        while l < self.size:
          self._push(l)
          l <<= 1
          if f(self.op(sm, self.d[l])):
            sm = self.op(sm, self.d[l])
            l += 1
        return l - self.size
      sm = self.op(sm, self.d[l])
      l += 1
      if l & -l == l: break
    return self._n

  def min_left(self, r, f):   # O(log n)
    if r <= 0: return 0
    r = min(r, self._n) + self.size
    for i in reversed(range(1, self.log + 1)):
      self._push((r - 1) >> i)
    sm = self.e
    while True:
      r -= 1
      while r > 1 and r % 2: r >>= 1
      if not f(self.op(self.d[r], sm)):
        while r < self.size:
          self._push(r)
          r = 2 * r + 1
          if f(self.op(self.d[r], sm)):
            sm = self.op(self.d[r], sm)
            r -= 1
        return r + 1 - self.size
      sm = self.op(self.d[r], sm)
      if r & -r == r: break
    return 0

  def _update(self, k):
    self.d[k] = self.op(self.d[2 * k], self.d[2 * k + 1])

  def _all_apply(self, k, f):
    self.d[k] = self.mapping(f, self.d[k])
    if k < self.size: self.lz[k] = self.composition(f, self.lz[k])

  def _push(self, k):
    if self.lz[k] == self.id: return
    self._all_apply(2 * k, self.lz[k])
    self._all_apply(2 * k + 1, self.lz[k])
    self.lz[k] = self.id

INF = 10 ** 16
mod = 998244353
# from operator import add
# f = lambda x, y: y if x == INF else x
def mapping(x, y):
  a, b = x
  return (a*y+b) % mod

def composition(y, x):
  if x == y == INF:
    return INF
  elif x == INF:
    return y
  elif y == INF:
    return x

  a1, b1 = x
  a2, b2 = y
  return (a1*a2 % mod, (a2*b1+b2) % mod)

segadd = lambda x, y: (x[0] + y[0], x[1] + y[1])
# seg = LazySegmentTree(min, INF, f, f, INF, W)  # 区間更新・区間最小値取得
# seg = LazySegmentTree(min, INF, add, add, 0, W)  # 区間加算・区間最小値取得
# seg = LazySegmentTree(max, -INF, add, add, 0, W)  # 区間加算・区間最大値取得
# seg = LazySegmentTree(segadd, (0, 0), lambda f, x: (x[0] + f * x[1], x[1]), add, 0, [(0, 1) for _ in range(W)])  # 区間加算・区間和取得
# seg = LazySegmentTree(segadd, (0, 0), lambda f, x: (f * x[1], x[1]) if f < INF else x, f, INF, [(0, 1) for _ in range(W)])  # 区間更新・区間和取得


n, m = map(int, input().split())
a = list(map(int, input().split()))
seg = LazySegmentTree(max, -INF, mapping, composition, INF, a)  # 区間更新・区間最大値取得

for _ in range(m):
  l, r, x = map(int, input().split())
  l -= 1
  LEN = r-l
  seg.apply_seg(l, r, ((LEN-1)*pow(LEN, -1, mod), x*pow(LEN, -1, mod)))

ans = []
for i in range(n):
  ans.append(seg.get(i)%mod)

print(*ans)
