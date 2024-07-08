from sys import stdin, setrecursionlimit
from collections import deque, defaultdict, Counter

setrecursionlimit(10 ** 9 + 7)
input = stdin.readline
INF = 1 << 61


# DX = (0, 1, 0, -1)
# DY = (-1, 0, 1, 0)
# DX = (0, 1, 1, 1, 0, -1, -1, -1)
# DY = (-1, -1, 0, 1, 1, 1, 0, -1)


class SegTree:
    def __init__(self, n, op=max, e=0, array=None):
        self.size = 1 << (n - 1).bit_length()
        self.op = op
        self.e = e
        self.dat = [self.e] * (self.size << 1)
        if array is not None:
            for i in range(n):
                self.dat[self.size + i] = array[i]
            for i in range(self.size - 1, 0, -1):
                self.dat[i] = self.op(self.dat[i * 2], self.dat[i * 2 + 1])

    def update(self, i, x):
        k = self.size + i
        self.dat[k] = x
        while k > 0:
            self.dat[k >> 1] = self.op(self.dat[k], self.dat[k ^ 1])
            k >>= 1

    def get(self, l, r=None):
        if r is None:
            r = l + 1
        ret = self.e
        l += self.size
        r += self.size
        while l < r:
            if l & 1:
                ret = self.op(ret, self.dat[l])
                l += 1
            if r & 1:
                ret = self.op(ret, self.dat[r - 1])
            l >>= 1
            r >>= 1
        return ret


def main():
    n, c = map(int, input().split())
    m = int(input())
    cl = SegTree(n, e=-INF)
    cr = SegTree(n, e=-INF)
    cl.update(0, 0)
    ans = 0
    for _ in range(m):
        t, p = map(int, input().split())
        t -= 1
        ma = max(cl.get(0, t + 1) - c * t, cr.get(t, n) + c * t) + p
        ans = max(ans, ma)
        cl.update(t, ma + c * t)
        cr.update(t, ma - c * t)
    print(ans)


if __name__ == '__main__':
    main()
