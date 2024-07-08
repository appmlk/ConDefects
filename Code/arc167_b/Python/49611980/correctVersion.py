from atcoder.modint import ModContext, Modint

MOD = 998244353
ModContext.context.append(MOD)
Modint.__repr__ = lambda x: repr(x._v)

A, B = map(int, input().split())


def factorization(N):
   from collections import defaultdict
   res = defaultdict(int)
   x = N
   y = 2
   while y*y <= x:
      while x%y == 0:
         res[y] += 1
         x //= y
      y += 1
   if x > 1:
      res[x] += 1
   return res


fact = factorization(A)

res = Modint(1)
for p, e in fact.items():
   res *= (B*e + 1)

res *= B
if B%2 == 1 and all(e%2 == 0 for e in fact.values()):
   res -= 1
res *= pow(2, -1, MOD)
print(res)
