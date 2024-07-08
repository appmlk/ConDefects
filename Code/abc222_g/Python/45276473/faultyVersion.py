from math import sqrt


def gcd2(a, b):
    x, y, lx, ly = 0, 1, 1, 0
    while b != 0:
        q = a // b
        a, b = b, a % b
        x, y, lx, ly = lx - q * x, ly - q * y, x, y
    return (lx, ly)


def inv(n, p):
    return gcd2(n, p)[0] % p


def solve(k):
    if k <= 2:
        return 1

    if not (k & 1):
        k //= 2

    if not (k % 2 and k % 5):
        return -1

    sq = int(sqrt(k))
    s = 1
    tbl = {}
    for i in range(sq + 2):
        if s not in tbl:
            tbl[s] = i
        s = s * 10 % k

    psinv = inv(pow(10, sq, k), k)
    m = inv(10, k)
    for i in range(sq + 2):
        if m in tbl:
            return sq * i + tbl[m] + 1
        m = m * psinv % k

    return -1


t = int(input())
for i in range(t):
    k = int(input())
    print(solve(k))
