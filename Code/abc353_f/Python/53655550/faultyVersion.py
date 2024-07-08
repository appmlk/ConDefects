from sys import stdin, setrecursionlimit
from collections import deque, defaultdict, Counter

setrecursionlimit(10 ** 9 + 7)
input = stdin.readline
INF = 1 << 61


# DX = (0, 1, 0, -1)
# DY = (-1, 0, 1, 0)
# DX = (0, 1, 1, 1, 0, -1, -1, -1)
# DY = (-1, -1, 0, 1, 1, 1, 0, -1)

class P:
    def __init__(self, x, y):
        self.x = x
        self.y = y


def to_big(p, k):
    bp = P(p.x // k, p.y // k)
    sp = P(p.x % k, p.y % k)
    ret = []
    if (bp.x + bp.y) % 2 == 0:
        ret.append([P(bp.x, bp.y + 1), k - sp.y])
        ret.append([P(bp.x, bp.y - 1), sp.y + 1])
        ret.append([P(bp.x + 1, bp.y), k - sp.x])
        ret.append([P(bp.x - 1, bp.y), sp.y + 1])
    else:
        ret.append([bp, 0])
    return ret


def main():
    k = int(input())
    s = P(*map(int, input().split()))
    t = P(*map(int, input().split()))

    sl = to_big(s, k)
    tl = to_big(t, k)

    ans = abs(s.x - t.x) + abs(s.y - t.y)
    if k == 1:
        print(ans)
        exit()
    for si, ic in sl:
        for tj, jc in tl:
            d = P(abs(si.x - tj.x), abs(si.y - tj.y))
            if d.x < d.y: d.x, d.y = d.y, d.x
            dist = ic + jc
            if k == 2:
                dist += d.y * 2
                dist += (d.x - d.y) * 3 // 2
            else:
                dist += d.x * 2
            if ans > dist:
                ans = dist
    print(ans)


if __name__ == '__main__':
    main()
