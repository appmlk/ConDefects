import sys
from math import ceil, floor
from fractions import Fraction
from itertools import permutations
input = sys.stdin.readline
inf = 10**18


def read(dtype=int):
    return list(map(dtype, input().split()))


r, = read(float)
x, = read()
# ceil

ans = (Fraction(r) - Fraction("1e-100")
       ).limit_denominator(x).as_integer_ratio()

print(*ans)
