## https://atcoder.jp/contests/abc250/tasks/abc250_g

MAX_INT = 10 ** 18

class LazySegmentTree:
    """
    非再帰版遅延セグメント木。
    更新は「加法」、取得は「最大値」のもの限定。
    取得のところの都合で取得演算子は可換になっている必要がある。
    """

    def __init__(self, init_array):
        n = 1
        while n < len(init_array):
            n *= 2
        
        self.size = n
        self.array = [(MAX_INT, MAX_INT) for _ in range(2 * self.size)]
        self.lazy_array = [0 for _ in range(2 * self.size)]
        for i, a in enumerate(init_array):
            self.array[self.size + i] = (a, i)
        
        end_index = self.size
        start_index = end_index // 2
        while start_index >= 1:
            for i in range(start_index, end_index):
                self.array[i] = min(self.array[2 * i], self.array[2 * i + 1])
            end_index = start_index
            start_index = end_index // 2
    
    def _propagates(self, *ids):
        for i in reversed(ids):
            self._propagate(i)

    def _propagate(self, i):
        v = self.lazy_array[i]
        if v == 0:
            return
        
        if i < self.size:
            self.lazy_array[2 * i] += v
            self.lazy_array[2 * i + 1] += v
            self.array[2 * i] = (self.array[2 * i][0] + v, self.array[2 * i][1])
            self.array[2 * i + 1] = (self.array[2 * i + 1][0] + v, self.array[2 * i + 1][1])
        self.lazy_array[i] = 0

    def _get_target_index(self, l, r):
        L = l + self.size; R = r + self.size
        lm = (L // (L & -L)) >> 1
        rm = (R // (R & -R)) >> 1
        while 0 < L and L < R:
            if R <= rm:
                yield R
            if L <= lm:
                yield L
            L >>= 1; R >>= 1
        while L > 0:
            yield L
            L >>= 1

    def add(self, l, r, x):
        # 2. 区間[l, r)のdata, lazyの値を更新
        L = self.size + l; R = self.size + r
        *ids, = self._get_target_index(l, r)
        self._propagates(*ids)
        while L < R:
            if R & 1:
                R -= 1
                self.lazy_array[R] += x
                self.array[R] = (self.array[R][0] + x, self.array[R][1])
            if L & 1:
                self.lazy_array[L] += x
                self.array[L] = (self.array[L][0] + x, self.array[R][1])
                L += 1
            L >>= 1; R >>= 1

        # 3. 伝搬させた区間について、ボトムアップにdataの値を伝搬する
        for i in ids:
            if i < self.size:
                self.array[i] = min(self.array[2 * i], self.array[2 * i +  1])

    def get_max(self, l, r):
        # 1. トップダウンにlazyの値を伝搬
        self._propagates(*self._get_target_index(l, r))
        L = self.size + l; R = self.size + r

        # 2. 区間[l, r)の最大値を求める
        s = (MAX_INT, MAX_INT)
        while L < R:
            if R & 1:
                R -= 1
                s = min(s, self.array[R])
            if L & 1:
                s = min(s, self.array[L])
                L += 1
            L >>= 1; R >>= 1
        return s



class SegmentTree:
    """
    非再帰版セグメント木。
    更新は「加法」、取得は「最大値」のもの限定。
    """

    def __init__(self, init_array):
        n = 1
        while n < len(init_array):
            n *= 2
        
        self.size = n
        self.array = [(MAX_INT, -1)] * (2 * self.size)
        for i, a in enumerate(init_array):
            self.array[self.size + i] = (a, i)
        
        end_index = self.size
        start_index = end_index // 2
        while start_index >= 1:
            for i in range(start_index, end_index):
                self.array[i] = min(self.array[2 * i], self.array[2 * i + 1])
            end_index = start_index
            start_index = end_index // 2

    def set(self, x, a):
        index = self.size + x
        self.array[index] = (a, x)
        while index > 1:
            index //= 2
            self.array[index] = min(self.array[2 * index], self.array[2 * index + 1])

    def get_max(self, l, r):
        L = self.size + l; R = self.size + r

        # 2. 区間[l, r)の最大値を求める
        s = (MAX_INT, -1)
        while L < R:
            if R & 1:
                R -= 1
                s = min(s, self.array[R])
            if L & 1:
                s = min(s, self.array[L])
                L += 1
            L >>= 1; R >>= 1
        return s


def main():
    N = int(input())
    P = list(map(int, input().split()))

    # 株の保持数を管理するセグメント木
    lazy_seg_tree = LazySegmentTree([0] * N)
    # 価格を管理するセグメント木
    seg_tree = SegmentTree(P)

    p_array = [(i, p) for i, p in enumerate(P)]
    p_array.sort(key=lambda x: x[1], reverse=True)
    answer = 0
    for i, p in p_array:
        v, max_range = lazy_seg_tree.get_max(i, N)
        if v < 0:
            max_range = N - 1
        
        s, j = seg_tree.get_max(0, max_range + 1)
        if s < p:
            seg_tree.set(j, MAX_INT)
            seg_tree.set(i, MAX_INT)
            answer += p - s
            if i < j:
                lazy_seg_tree.add(i, j, -1)
            else:
                lazy_seg_tree.add(j, i, +1)
    print(answer)    

        
        
        



if __name__ == "__main__":
    main()
