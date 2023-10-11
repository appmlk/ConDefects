from collections import Counter
from functools import reduce

N = int(input())

def prime_factorize(n):
    a = []
    while n % 2 == 0:
        a.append(2)
        n //= 2
    f = 3
    while f * f <= n:
        if n % f == 0:
            a.append(f)
            n //= f
        else:
            f += 2
    if n != 1:
        a.append(n)
    return a
    

cnt = 0
for AB in range(1, N // 2 + 1):
  CD = N - AB
  if AB == 1:
    cntAB = 1
  else:
    cntAB = reduce(lambda x, y: x * (y+1), Counter(prime_factorize(AB)).values(), 1)
  if CD == 1:
    cntCD = 1
  else:
    cntCD = reduce(lambda x, y: x * (y+1), Counter(prime_factorize(CD)).values(), 1)
  cnt += (cntAB * cntCD * 2) if AB != CD else (cntAB * cntCD)


print(cnt)
