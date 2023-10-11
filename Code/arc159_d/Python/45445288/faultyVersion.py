class SegmentTree:
    # n->要素数, l->リスト, e->単位元, comp->二項関数
    def __init__(self, n, l, e, comp):
        self.e = e
        self.comp = comp
        self.length = 1 << (n - 1).bit_length()
        self.tree = [self.e] * self.length + l + [self.e] * (self.length - len(l))
        for i in range(self.length - 1, 0, -1):
            self.tree[i] = comp(self.tree[2 * i], self.tree[2 * i + 1])

    # 0-indexedでidx番目の要素をxに更新
    def _set(self, idx, x):
        idx += self.length
        self.tree[idx] = x
        idx >>= 1
        while idx > 0:
            self.tree[idx] = self.comp(self.tree[2 * idx], self.tree[2 * idx + 1])
            idx >>= 1

    # 0-indexedで[l, r)の値を求める
    def _get(self, left, right):
        left += self.length
        right += self.length
        res = self.e
        while right > left:
            if left & 1:
                res = self.comp(res, self.tree[left])
                left += 1
            if right & 1:
                res = self.comp(res, self.tree[right - 1])
                right -= 1
            left >>= 1
            right >>= 1
        return res

    def __repr__(self) -> str:
        return str(self.tree[self.length :])


class SegmentTreeR:
    # n->要素数, l->リスト, e_upd->単位元(更新用), fun_upd->二項関数(更新用), e_acq->単位元(取得用), fun_acq->二項関数(取得用)
    def __init__(self, n, l, e_upd, fun_upd, e_acq, fun_acq):
        self.e_upd = e_upd
        self.fun_upd = fun_upd
        self.e_acq = e_acq
        self.fun_acq = fun_acq
        self.length = 1 << (n - 1).bit_length()
        self.tree = (
            [self.e_upd] * self.length + l + [self.e_upd] * (self.length - len(l))
        )

    # 0-indexedで[l, r)の要素をxに更新
    def _set(self, l, r, x):
        l += self.length
        r += self.length
        while r > l:
            if l & 1:
                self.tree[l] = self.fun_upd(x, self.tree[l])
                l += 1
            if r & 1:
                self.tree[r - 1] = self.fun_upd(x, self.tree[r - 1])
                r -= 1
            l >>= 1
            r >>= 1

    # 0-indexedで idx の値を求める
    def _get(self, idx):
        idx += self.length
        res = self.e_acq
        while idx > 0:
            res = self.fun_acq(res, self.tree[idx])
            idx >>= 1
        return res

    def __repr__(self) -> str:
        return str(self.tree[self.length :])

n = int(input())
lr = []
s = set()
for i in range(n):
    l, r = map(int, input().split())
    lr.append((l, r))
    for j in range(-1, 2):
        s.add(l + j)
        s.add(r + j)

s = sorted(list(s))
d = {}
for idx, i in enumerate(s):
    d[i] = idx + 1

l = len(s) + 3
seg = SegmentTree(l, [], 0, max)
segr = SegmentTreeR(l, [0] + [i for i in s], 1 << 30, min, 1 << 30, min)

for l, r in lr:
    mx = seg._get(0, d[l])
    mx = max(mx, l - 1 - segr._get(d[l - 1]))
    segr._set(d[l], d[r] + 1, l - mx)
    seg._set(d[r], mx + r - l + 1)

print(seg._get(0, len(s) + 2))
