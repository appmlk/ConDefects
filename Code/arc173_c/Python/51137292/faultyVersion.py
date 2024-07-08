from operator import add, eq


class SegmentTreeInjectable:
    """
    単位元生成関数 identity_factory と二項演算関数 func を外部注入するセグメント木

    [生成]
    SegmentTreeInjectable(n, identity_factory, func)
    SegmentTreeInjectable.from_array(array, identity_factory, func)  # 既存の配列より作成

    [関数]
    add(i, x)               Aiにxを加算
    update(i, x)            Aiをxに書き換え
    get_range(a, b)         [a, b) の集約値を得る
    get_all()               全ての集約値を得る
    get_point(i)            Aiを得る
    leftmost(a, b, x, ev)   [a, b) の範囲で、ev(x, Ai)=True となる最も左の i を得る（前提条件あり）
    rightmost(a, b, x, ev)  [a, b) の範囲で、ev(x, Ai)=True となる最も右の i を得る（前提条件あり）
    debug_print()           深さ毎に整形して出力する
    """

    def __init__(self, n, identity_factory, func):
        n2 = 1 << (n - 1).bit_length()
        self.offset = n2
        self.tree = [identity_factory() for _ in range(n2 << 1)]
        self.func = func
        self.idf = identity_factory

    @classmethod
    def from_array(cls, arr, identity_factory, func):
        """ 既存の配列から生成 """
        ins = cls(len(arr), identity_factory, func)
        ins.tree[ins.offset:ins.offset + len(arr)] = arr
        for i in range(ins.offset - 1, 0, -1):
            l = i << 1
            r = l + 1
            ins.tree[i] = func(ins.tree[l], ins.tree[r])
        return ins

    def add(self, i, x):
        """
        Aiにxを加算
        :param i: index (0-indexed)
        :param x: add value
        """
        i += self.offset
        self.tree[i] = self.func(self.tree[i], x)
        self.__upstream(i)

    def update(self, i, x):
        """
        Aiの値をxに更新
        :param i: index(0-indexed)
        :param x: update value
        """
        i += self.offset
        self.tree[i] = x
        self.__upstream(i)

    def __upstream(self, i):
        tree = self.tree
        func = self.func
        while i > 1:
            i >>= 1
            lch = i << 1
            rch = lch | 1
            tree[i] = func(tree[lch], tree[rch])

    def get_range(self, a, b):
        """
        [a, b)の値を得る
        :param a: index(0-indexed)
        :param b: index(0-indexed)
        """
        tree = self.tree
        func = self.func
        result_l = self.idf()
        result_r = self.idf()

        l = a + self.offset
        r = b + self.offset
        while l < r:
            if r & 1:
                result_r = func(tree[r - 1], result_r)
            if l & 1:
                result_l = func(result_l, tree[l])
                l += 1
            l >>= 1
            r >>= 1

        return func(result_l, result_r)

    def get_all(self):
        return self.tree[1]

    def get_point(self, i):
        return self.tree[i + self.offset]

    def leftmost(self, a, b, x, ev):
        """
        [a, b) の範囲で、ev(x, 値) = True となる最初の index を得る。存在しない場合は-1。

        使用できる条件:
        [l, r) の集約値を y としたとき、ev(x, y)=True となることが、
        l <= i < r 内に ev(x, Ai)=True となる要素があることと等しい。（(func, ev) = (min,ge), (max,le) など）
        """
        tree = self.tree
        l = a + self.offset
        r = b + self.offset
        r_found = -1
        while l < r:
            if l & 1:
                if ev(x, tree[l]):
                    return self._leftmost_sub(l, x, ev)
                l += 1
            if r & 1:
                if ev(x, tree[r - 1]):
                    r_found = r - 1
            l >>= 1
            r >>= 1

        if r_found == -1:
            return -1

        return self._leftmost_sub(r_found, x, ev)

    def _leftmost_sub(self, i, x, ev):
        """
        tree-index i が示す範囲で、ev(x, Aj)=True となる最も左のarray-index j を得る
        （tree[i] が示す範囲には条件を満たすものが必ず存在する前提とする）
        """
        tree = self.tree
        while i < self.offset:
            l = i << 1
            if ev(x, tree[l]):
                i = l
            else:
                i = l + 1
        return i - self.offset

    def rightmost(self, a, b, x, ev):
        """
        [a, b) の範囲で、ev(x, 値) = True となる最後の index を得る。存在しない場合は-1。

        使用できる条件:
        [l, r) の集約値を y としたとき、ev(x, y)=True となることが、
        l <= i < r 内に ev(x, Ai)=True となる要素があることと等しい。（(func, ev) = (min,ge), (max,le) など）
        """
        tree = self.tree
        l = a + self.offset
        r = b + self.offset
        l_found = -1
        while l < r:
            if r & 1:
                if ev(x, tree[r - 1]):
                    return self._rightmost_sub(r - 1, x, ev)
            if l & 1:
                if ev(x, tree[l]):
                    l_found = l
                l += 1
            l >>= 1
            r >>= 1

        if l_found == -1:
            return -1

        return self._rightmost_sub(l_found, x, ev)

    def _rightmost_sub(self, i, x, ev):
        """
        tree-index i が示す範囲で、ev(x, Aj)=True となる最も右のarray-index j を得る
        （tree[i] が示す範囲には条件を満たすものが必ず存在する前提とする）
        """
        tree = self.tree
        while i < self.offset:
            l = i << 1
            if ev(x, tree[l + 1]):
                i = l + 1
            else:
                i = l
        return i - self.offset

    def debug_print(self):
        i = 1
        while i <= self.offset:
            print(self.tree[i:i * 2])
            i <<= 1


n = int(input())
aaa = list(map(int, input().split()))
aaa = [a - 1 for a in aaa]

# import random
#
# n = 100
# aaa = list(range(n))
# random.shuffle(aaa)
# print(aaa)

pos = [0] * n
for i, a in enumerate(aaa, start=1):
    pos[a] = i


def sgt_update(i):
    b = aaa[i - 1]
    if i > 1:
        a = aaa[i - 2]
        if a < b:
            sgt.update(i - 1, 1)
        else:
            sgt.update(i - 1, 0)
    if i < n:
        c = aaa[i]
        if b > c:
            sgt.update(i, 1)
        else:
            sgt.update(i, 0)


def pattern_left():
    j = 1
    over = 0
    under = 0
    try:
        while True:
            if b < aaa[j]:
                over += 1
            else:
                under += 1
            j += 1
            if b < aaa[j]:
                over += 1
            else:
                under += 1
            j += 1
            if over != under:
                return j
    except:
        return -1


def pattern_right():
    j = n - 2
    over = 0
    under = 0
    try:
        while j >= 2:
            if b < aaa[j]:
                over += 1
            else:
                under += 1
            j -= 1
            if b < aaa[j]:
                over += 1
            else:
                under += 1
            j -= 1
            if over != under:
                return n - j - 1
        else:
            return -1
    except:
        return -1


sgt = SegmentTreeInjectable.from_array([1] * (n + 2), lambda: 1, max)
ans = [0] * n
INF = 1 << 60
for b in range(n):
    i = pos[b]

    if i == 1:
        ans[i - 1] = pattern_left()
        sgt_update(i)
        continue

    if i == n:
        ans[i - 1] = pattern_right()
        sgt_update(i)
        continue

    a = aaa[i - 2]
    c = aaa[i]
    if (a < b) == (b > c):
        ans[i - 1] = 3
        sgt_update(i)

        # print(f'{b=} {i=}')
        # sgt.debug_print()

        continue

    l = sgt.rightmost(0, i - 1, 1, eq)
    r = sgt.leftmost(i + 1, n + 2, 1, eq)
    # print(b, l, i, r, aaa[l - 1:r + 1])

    tmp = INF
    if l > 0:
        tmp = min(tmp, (i - l + 1) // 2 * 2 + 1)
    if r < n:
        tmp = min(tmp, (r - i + 2) // 2 * 2 + 1)
    if tmp == INF:
        tmp = -1
    ans[i - 1] = tmp

    sgt_update(i)

    # print(f'{b=} {i=} {tmp=}')
    # sgt.debug_print()

print(*ans)
