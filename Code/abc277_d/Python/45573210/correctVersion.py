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
    n, m = ints()
    aa = sorted(ints())
#    print(locals())
    
    ss = []
    s = 0
    for i in range(len(aa)):
        a = aa[i]
        if i > 0 and a > aa[i-1] + 1:
            ss.append(s)
            s = a
        else:
            s += a
    ss.append(s)
        
    if len(ss) > 1 and (aa[-1] + 1) % m == aa[0]:
        ss.append(ss[0] + ss[-1])

    total = sum(aa)
    print(total - max(ss))
    
if __name__ == '__main__':
    main()