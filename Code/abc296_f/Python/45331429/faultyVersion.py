import sys
def main():
    input = sys.stdin.readline
    N = int(input())
    *A, = map(int, input().split())
    *B, = map(int, input().split())
    if sorted(A) != sorted(B):
        return False
    G = [[] for _ in range(N + 1)]
    for i, a in enumerate(A):
        G[a].append(i)
    for g in G:
        s = set(B[i] for i in g)
        if len(s) > 1:
            return True
    I = sorted(range(N), key=lambda i: A[i])
    C = [B[i] for i in I]
    bit = BinaryIndexedTree(N + 1)
    t = 0
    for i, c in enumerate(C):
        t += i - bit.query(c)
        bit.add(c, 1)
    return t & 1 == 2

class BinaryIndexedTree:
    __slots__ = ('__n', '__d', '__f', '__id')

    def __init__(self, n=None, f=lambda x, y: x + y, identity=0, initial_values=None):
        assert n or initial_values
        self.__f, self.__id, = f, identity
        self.__n = len(initial_values) if initial_values else n
        self.__d = [identity] * (self.__n + 1)
        if initial_values:
            for i, v in enumerate(initial_values): self.add(i, v)

    def add(self, i, v):
        n, f, d = self.__n, self.__f, self.__d
        i += 1
        while i <= n:
            d[i] = f(d[i], v)
            i += -i & i

    def query(self, r):
        res, f, d = self.__id, self.__f, self.__d
        r += 1
        while r:
            res = f(res, d[r])
            r -= -r & r
        return res

    def bisect(self, func):
        '''func()がFalseになるもっとも左のindexを探す
        '''
        n, f, d, v = self.__n, self.__f, self.__d, self.__id
        x, i = 0, 1 << (n.bit_length() - 1)
        while i > 0:
            if x + i <= n and func(f(v, d[x + i])): v, x = f(v, d[x + i]), x + i
            i >>= 1
        return x

if __name__ == '__main__':
    print('Yes' if main() else 'No')
