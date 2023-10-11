# coding: utf-8

from fractions import Fraction

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

def above_below(p, p1, p2):
    '''
    点(x, y)が２点(x1, y1),(x2, y2)を通る直線の上にあるか下にあるか
    　0なら直線上、正なら+y領域、負なら-y領域
    '''
    (x, y), (x1, y1), (x2, y2) = p, p1, p2
    
    # 鉛直線の場合
    if x1 == x2:
        return x - x1

    # 直線 a x + y + b = 0 を求める
    a = Fraction(-(y1 - y2) / (x1 - x2))
    b = - (a * x1 + y1)
    # x, yを代入して返す
    return a * x + y + b

#@psecs
def main():
    pts = [tuple(ints()) for _ in range(4)]
#    print(locals())
    
    # 対角線が作る直線に対して残りの２点が反対側にあれば凸
    if above_below(pts[0], pts[1], pts[3]) * above_below(pts[2], pts[1], pts[3]) < 0 and \
        above_below(pts[1], pts[0], pts[2]) * above_below(pts[3], pts[0], pts[2]) < 0:
            print('Yes')
    else:
        print('No')
    
if __name__ == '__main__':
    main()