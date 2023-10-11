from collections import Counter
import sys
input = lambda: sys.stdin.readline().rstrip()
ii = lambda: int(input())
mi = lambda: map(int, input().split())
li = lambda: list(mi())
inf = 2 ** 63 - 1
mod = 998244353

n, m = mi()

N = 1
while N <= n or N <= m:
    N *= 2

a = li()

class andconvolution():
    def __init__(self):
        self.mod = 2

    def zetatransform(self, a):
        N = len(a)
        n = 0
        for i in range(0, 21):
            if 2 ** i >= N:
                a += [0] * (N - 2 ** i)
                n = i
                break
        g = []
        for i in range(2 ** n):
            g.append(a[i])
        for j in range(n):
            bit = 1 << j
            for i in range(1 << n):
                if i & bit == 0:
                    g[i] ^= g[i | bit]
   
        return g

    def mebioustransform(self, a):
        N = len(a)
        n = 0
        for i in range(0, 21):
            if 2 ** i >= N:
                a += [0] * (N - 2 ** i)
                n = i
                break
        g = []
        for i in range(2 ** n):
            g.append(a[i])
        for j in range(n):
            bit = 1 << j
            for i in range(1 << n):
                if i & bit == 0:
                    g[i] -= g[i | bit]
                    g[i] %= self.mod
   
        return g

    def ANDconv(self, a, b):
        a = self.zetatransform(a)
        b = self.zetatransform(b)
        N = max(len(a), len(b))
        a += [0] * (N - len(a))
        b += [0] * (N - len(b))
        c = [0] * N

        for i in range(N):
            c[i] = (a[i] * b[i]) % self.mod
        c = self.mebioustransform(c)

        for i in range(N):
            c[i] %= self.mod
        return c
        
C = andconvolution()
A = [0 for i in range(N)]
for i in range(n):
    A[N - 1 - (n - i - 1)] = a[i]
A = C.zetatransform(A)

print(*A[:m])