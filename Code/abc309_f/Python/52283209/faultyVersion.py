class SegTree:

   def __init__(self, op, e, seq):
      if type(seq) is int:
         seq = [e]*seq
      self.N = len(seq)
      self.e = e
      self.op = op
      self.X = [e]*(self.N * 2)
      self._build(seq)

   def _build(self, seq):
      X = self.X
      op = self.op

      for i, x in enumerate(seq, self.N):
         X[i] = x
      for i in range(self.N - 1, 0, -1):
         X[i] = op(X[i<<1], X[i<<1|1])

   def get(self, i):
      return self.X[self.N + i]

   def set(self, i, x):
      X = self.X
      op = self.op

      i += self.N
      X[i] = x
      while i > 1:
         i >>= 1
         X[i] = op(X[i<<1], X[i<<1|1])

   def fold(self, L, R):
      assert 0 <= L <= R <= self.N

      X = self.X
      op = self.op

      L += self.N
      R += self.N
      vL = self.e
      vR = self.e
      while L < R:
         if L&1:
            vL = op(vL, X[L])
            L += 1
         if R&1:
            R -= 1
            vR = op(X[R], vR)
         L >>= 1
         R >>= 1
      return op(vL, vR)

   def __getitem__(self, idx):
      if type(idx) is int:
         return self.get(idx)
      return self.fold(*self.unpack_slice(idx))

   def __setitem__(self, i, x):
      self.set(i, x)

   def __repr__(self):
      return repr([self.get(i) for i in range(self.N)])

   def __iter__(self):
      return iter(self.X[self.N:])

   def unpack_slice(self, slice):

      assert slice.step is None
      l = slice.start or 0
      r = slice.stop if slice.stop is not None else self.N

      l = max(0, l)
      r = min(self.N, r)
      assert l <= r
      return l, r


from collections import defaultdict

INF = float("INF")
N = int(input())
B = defaultdict(list)
W = []
D = []
for _ in range(N):
   h, w, d = map(int, input().split())
   h, w, d = sorted([h, w, d])
   B[h].append((w, d))
   W.append(w)
   D.append(d)

H = sorted(B.keys())


def compress(A):
   S = sorted(set(A))
   d = {v: i for i, v in enumerate(S)}
   return d


encode_W = compress(W)
encode_D = compress(D)

for h in H:
   WD = B[h]
   del B[h]
   for w, d in WD:
      B[h].append((encode_W[w], encode_D[d]))

seg = SegTree(min, INF, len(set(W)) + 1)


class Break(Exception):
   pass


try:
   for h in H:
      WD = B[h]
      for w, d in WD:
         if d > seg[:w]:
            raise Break
      for w, d in WD:
         seg[w] = d
except Break:
   ans = True
else:
   ans = False

if ans is True:
   print("Yes")
else:
   print("No")
