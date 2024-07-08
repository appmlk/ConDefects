class FenwickTreeInjectable:
    def __init__(self, n, identity_factory, func):
        self.size = n
        self.tree = [identity_factory() for _ in range(n + 1)]
        self.func = func
        self.idf = identity_factory
        self.depth = n.bit_length()

    def add(self, i, x):
        i += 1
        tree = self.tree
        func = self.func
        while i <= self.size:
            tree[i] = func(tree[i], x)
            i += i & -i

    def sum(self, i):
        i += 1
        s = self.idf()
        tree = self.tree
        func = self.func
        while i > 0:
            s = func(s, tree[i])
            i -= i & -i
        return s

    def lower_bound(self, x, lt):
        """
        累積和がx以上になる最小のindexと、その直前までの累積和

        :param lt: lt(a, b) で a < b ならTrueを返す関数
        """
        total = self.idf()
        pos = 0
        tree = self.tree
        func = self.func
        for i in range(self.depth, -1, -1):
            k = pos + (1 << i)
            if k > self.size:
                continue
            new_total = func(total, tree[k])
            if lt(new_total, x):
                total = new_total
                pos += 1 << i
        return pos + 1, total

    def debug_print(self):
        prev = 0
        arr = []
        for i in range(self.size):
            curr = self.sum(i)
            arr.append(curr - prev)
            prev = curr
        print(arr)


n, k, c = list(map(int, input().split()))
MOD = 998244353

# DP[i] = A[i] 以降まで決まった数列で、最後の色の連続の左端indexが i であるものの個数
fwt = FenwickTreeInjectable(n + 1, int, lambda x, y: (x + y) % MOD)
# 0→i の遷移: iに限らず c*(c-1)    （特殊なのでDF配列では管理せず外側で持つ）
# (j=1～i-k+1)→i の遷移: DP[j]*(c-1)
# (j=i-k+2～i-1)→i の遷移: DP[j]*1
for i in range(1, n):
    s = fwt.sum(i)
    t = fwt.sum(i - k + 1) if i - k + 1 > 0 else 0
    s -= t
    tmp = s + t * (c - 1) + c * (c - 1)
    fwt.add(i, tmp % MOD)

ans = fwt.sum(n) + c
print(ans)
