N, K = map(int, input().split())
A = list(map(int, input().split()))
K -= 1
inda = [-1]*(N+1)
for i, a in enumerate(A):
    inda[a] = i


class Bit:
    def __init__(self, n):
        self.size = n
        self.tree = [0] * (n + 1)

    def sum(self, i):
        i += 1
        s = 0
        while i > 0:
            s += self.tree[i]
            i -= i & -i
        return s

    def sumrange(self, i, j):
        si = self.sum(i - 1)
        sj = self.sum(j)
        return sj - si

    def add(self, i, x):
        i += 1
        while i <= self.size:
            self.tree[i] += x
            i += i & -i

    def lower_bound(self, w):
        if w <= 0:
            return 0
        x = 0
        r = 1 << (self.size).bit_length()
        while r > 0:
            if x+r < self.size and self.tree[x+r] < w:
                w -= self.tree[x+r]
                x += r
            r = r >> 1
        return x


ctl, ctr = 0, N*(N+1)//2
flag = 0
state = Bit(N+2)
ans = A[:]

for i in range(1, N+1):
    state.add(i, 1)

for i, a in enumerate(A):
    state.add(a, -1)
    vl = state.sumrange(0, a-1)
    vr = state.sumrange(a+1, N)
    if ctl+vl > K:
        x = state.lower_bound(K-ctl+1)
        ind = inda[x]
        for j in range(i, ind+1):
            ans[j] = A[ind-j+i]
        break
    if ctr-vr <= K:
        x = state.lower_bound(K-ctr+vr+vl+1)
        ind = inda[x]
        for j in range(i, ind+1):
            ans[j] = A[ind-j+i]
        break
    ctl += vl
    ctr -= vr

print(*ans)
