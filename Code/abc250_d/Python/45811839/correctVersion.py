import sys
from collections import *
from functools import lru_cache, partial
from itertools import *
from pprint import pprint


def debug(*args, end='\n'): print(*args, end=end, file=sys.stderr)
dpprint = partial(pprint, stream=sys.stderr)
sys.setrecursionlimit(10 ** 6)
MOD=998244353

N = int(input())

# p x q^3 <= q^4 <= N
# q <= N^(1/4)
M = 1000000

# M までの素数を列挙する
is_prime = [True] * (M + 1)
is_prime[0] = False
is_prime[1] = False
for i in range(2, M + 1):
    if not is_prime[i]:
        continue
    for j in range(i * 2, M + 1, i):
        is_prime[j] = False

primes = [i for i, p in enumerate(is_prime, start=0) if p]
# debug(f"{is_prime[:20]=}")
# debug(f"{primes[:20]=}")
# debug(f"{len(primes)=}")

O = len(primes)
ans = 0
for i in range(O):
    p = primes[i]
    for j in range(i + 1, O):
        q = primes[j]
        if p * q * q * q > N:
            break
        ans += 1

print(ans)
