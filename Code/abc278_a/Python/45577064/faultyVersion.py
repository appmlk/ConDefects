# coding: utf-8

from functools import partial
try:
    dummy = src
    rl = partial(src.pop, 0)
except NameError:
    rl = input
def ints():
    return list(map(int, rl().strip().split()))
def int1():
    return int(rl().strip())

#@psecs
def main():
    n, k = ints()
    aa = ints()
    print(locals())
    
    if n > k:
        bb = aa[k:] + [0] * k
    else:
        bb = [0] * n
    print(*bb)
    
if __name__ == '__main__':
    main()