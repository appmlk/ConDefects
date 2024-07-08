from collections import defaultdict


class UnionFind:
    def __init__(self, n):
        self.table = [-1] * n

    def root(self, x):
        stack = []
        tbl = self.table
        while tbl[x] >= 0:
            stack.append(x)
            x = tbl[x]
        for y in stack:
            tbl[y] = x
        return x

    def find(self, x, y):
        return self.root(x) == self.root(y)

    def unite(self, x, y):
        r1 = self.root(x)
        r2 = self.root(y)
        if r1 == r2:
            return False
        d1 = self.table[r1]
        d2 = self.table[r2]
        if d1 <= d2:
            self.table[r2] = r1
            self.table[r1] += d2
        else:
            self.table[r1] = r2
            self.table[r2] += d1
        return True

    def get_size(self, x):
        return -self.table[self.root(x)]


def solve(n, aaa):
    uft = UnionFind(n)

    i, j = 0, 0
    while i < n:
        while j < aaa[i] + 1:
            uft.unite(i - j, i + j)
            j += 1
        k = 1
        while i - k >= 0 and i + k < n and k + aaa[i - k] + 1 < j:
            if k + aaa[i - k] < j - 1 and aaa[i - k] != aaa[i + k]:
                return None
            k += 1
        i += k
        j -= k

    banned = defaultdict(set)
    for i, a in enumerate(aaa):
        if i - a == 0 or i + a == n - 1:
            continue
        bl = i - a - 1
        br = i + a + 1
        rl = uft.root(bl)
        rr = uft.root(br)
        if rl == rr:
            return None
        banned[rl].add(rr)
        banned[rr].add(rl)

    ans = [0] * n
    color = [-1] * n
    for i in range(n):
        rt = uft.root(i)
        if color[rt] > 0:
            ans[i] = color[rt]
            continue
        ban = {color[b] for b in banned[rt]}
        for c in range(1, 10000):
            if c not in ban:
                break
        color[rt] = c
        ans[i] = c

    return ans


n = int(input())
aaa = list(map(int, input().split()))
ans = solve(n, aaa)

if ans is None:
    print('No')
else:
    print('Yes')
    print(*ans)
