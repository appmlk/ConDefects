# coding: utf-8

from random import randint

from functools import partial
import sys
try:
    dummy = src
    rl = partial(src.pop, 0)
except NameError:
    rl = sys.stdin.readline
def ints():
    return list(map(int, rl().rstrip().split(' ')))
def int1():
    return int(rl().rstrip())

#@psecs
def main():
    n, m = ints()
    aa = ints()
#    print(locals())

    if False:
        n = 2 * 10 ** 5
        m = randint(1, n)
        aa = [randint(-2 * 10 ** 5, 2 * 10 ** 5) for _ in range(n)]
    
    bb = [0] * n
    s = 0
    for i in range(n):
        s += aa[i]
        bb[i] = s
    sm_max = 0
    for i in range(n - m + 1):
#        pr('i')
        if i == 0:
            sm = sum(a * b for a, b in zip(aa[i: i+m], range(1, m+1)))
        else:
            sm = sm - aa[i-1] + m * aa[i+m-1] - (bb[i+m-2] - bb[i-1])
#        pr('sm')
        if sm_max < sm:
            sm_max = sm
    print(sm_max)
    
if __name__ == '__main__':
    main()