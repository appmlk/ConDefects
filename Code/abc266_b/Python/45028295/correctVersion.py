# coding: utf-8

from functools import partial
try:
    dummy = src
    minp = partial(src.pop, 0)
except NameError:
    minp = input
def ints():
    return list(map(int, minp().rstrip().split(' ')))
def int1():
    return int(minp().rstrip())

#@psecs
def main():
    n = int1()
    d = 998244353
    print(n % d)

if __name__ == '__main__':
    main()
