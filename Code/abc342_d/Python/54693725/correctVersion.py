#!/usr/bin/env python3
from collections import Counter
from math import comb
from sys import stdin

_tokens = (y for x in stdin for y in x.split())
def read(): return next(_tokens)
def iread(): return int(next(_tokens))


def prime_factorize(n):
    if n == 0:
        return [0]
    a = []
    while n % 2 == 0:
        if a and a[-1] == 2:
            a.pop()
        else:
            a.append(2)
        n //= 2
    f = 3
    while f * f <= n:
        if n % f == 0:
            if a and a[-1] == f:
                a.pop()
            else:
                a.append(f)
            n //= f
        else:
            f += 2
    if n != 1:
        if a and a[-1] == n:
            a.pop()
        else:
            a.append(n)
    return a


def f(p):
    ret = 1
    for x in p:
        ret *= x
    return ret


def main():
    n = iread()
    a = [iread() for _ in range(n)]
    p = [prime_factorize(x) for x in a]
    p = [f(x) for x in p]
    c = Counter(p)
    ans = 0
    for k, v in c.items():
        if k == 0:
            u = n - v
            ans += u * v + comb(v, 2)
        else:
            ans += comb(v, 2)
    print(ans)


if __name__ == '__main__':
    main()
