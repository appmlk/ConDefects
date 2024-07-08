from heapq import heappop, heappush


class Set:
    def __init__(self):
        self.q = []
        self.d = []
        self.sm = 0

    def add(self, x):
        heappush(self.q, x)
        self.sm += x

    def delete(self, x):
        heappush(self.d, x)
        self.sm -= x

    def get_min(self):
        while self.d and self.q[0] == self.d[0]:
            heappop(self.q)
            heappop(self.d)
        return self.q[0]

    def pop_min(self):
        while self.d and self.q[0] == self.d[0]:
            heappop(self.q)
            heappop(self.d)
        res = heappop(self.q)
        self.sm -= res
        return res


class BottomK:
    def __init__(self, n, a, k):
        assert k <= n
        self.a = a
        self.n = n
        self.k = k
        self.low = Set()
        self.high = Set()
        a.sort()
        for i in range(k):
            self.low.add(a[i])
        for i in range(k, n):
            self.high.add(a[i])

    # 0-indexed
    def set(self, i, x):
        ai = self.a[i]
        self.a[i] = x

        if self.k == self.n:
            self.low.delete(-ai)
            self.low.add(-x)
            return

        if not self.k:
            self.high.delete(ai)
            self.high.add(x)
            return

        if ai < self.high.get_min():
            self.low.delete(-ai)
            hl = self.high.pop_min()
            if x > hl:
                self.low.add(-hl)
                self.high.add(x)
            else:
                self.high.add(hl)
                self.low.add(-x)
        else:
            self.high.delete(ai)
            lh = self.low.pop_min()
            lh *= -1
            if x > lh:
                self.high.add(x)
                self.low.add(-lh)
            else:
                self.high.add(lh)
                self.low.add(-x)

    def get(self):
        return -self.low.sm

    def inc_k(self):
        assert self.k < self.n
        self.k += 1
        hl = self.high.pop_min()
        self.low.add(-hl)

    def dec_k(self):
        assert self.k
        self.k -= 1
        lh = self.low.pop_min()
        self.high.add(-lh)


n, m, h = map(int, input().split())
s = BottomK(m, [0] * m, m)

ans = []
for i in range(n):
    a, b = map(int, input().split())
    b -= 1
    s.set(b, a + s.a[b])
    while s.get() >= h:
        ans.append(i)
        s.dec_k()

ans += [n] * (m + 1 - len(ans))
print(*ans)
