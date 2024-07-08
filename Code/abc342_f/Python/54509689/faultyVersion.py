class Dual_Fenwick_Tree:
    def __init__(self, n):
        self._n = n + 1
        self.data = [0] * (n + 1)

    def _add(self, p, x):
        assert 0 <= p < self._n
        p += 1
        while p <= self._n:
            self.data[p - 1] += x
            p += p & -p

    def sum(self, l, r):
        assert 0 <= l <= r <= self._n
        return self._sum(r) - self._sum(l)

    def _sum(self, r):
        s = 0
        while r > 0:
            s += self.data[r - 1]
            r -= r & -r
        return s

    # A[l:r]にvを足す
    def add(self, l, r, v):
        self._add(l, v)
        self._add(r, -v)

    # A[x]を返す
    def get(self, x):
        return self.sum(0, x + 1)

    def __str__(self):
        temp = []
        for i in range(self._n):
            temp.append(str(self.sum(0, i + 1)))
        return ' '.join(temp)


N, L, D = map(int, input().split())
M = 2 * N + 5
T = Dual_Fenwick_Tree(M)
T.add(0, 1, 1)
for i in range(L):
    v = T.get(i)/D
    T.add(i + 1, i + D + 1, v)
    
p = [0] * M
q = [0] * M
r = [0] * M
for i in range(M):
    if i >= L:
        p[i] = T.get(i)
        
Ac = [0] * (M + 1)
for i in range(M):
    Ac[i + 1] = Ac[i] + p[i]
    
for i in range(M):
    q[i] = Ac[i] + (Ac[-1] - Ac[N + 1])

from collections import *        
Q = deque()
v = 0
for i in range(N, -1, -1):
    r[i] = max(q[i], v)
    Q.append(r[i]/D)
    v += r[i]/D
    if len(Q) > L:
        v -= Q.popleft()
        
print(r[0])