import math
from decimal import Decimal

a, b = map(int, input().split())

def f(x):
    return Decimal((a / math.sqrt(1 + x)) + (b * x))

# 3分探索する
l = 0
r = 10**40

while r - l > 1:
    m1 = (2 * l + r) // 3
    m2 = (l + 2 * r) // 3
    # print(m1, m2, l, r, f(m1), f(m2))
    if f(m1) < f(m2):
        r = m2
    else:
        if l == m1:
            l += 1
        else:
            l = m1
print(min(f(l), f(r)))