class SegmentTree:
    def __init__(
        self,
        n,            # 列の長さ
        identity_e,   # 単位元
        combine_f,    # 2 つのデータから値を合成するための関数
    ):
        self._n = n
        self._size = 1
        while self._size < self._n:
            self._size <<= 1
        self._identity_e = identity_e
        self._combine_f = combine_f
        self._node = [self._identity_e] * (2 * self._size)

    # 配列の各要素を登録する
    def build(self, array):
        assert len(array) == self._n
        for index, value in enumerate(array, start=self._size):
            self._node[index] = value
        for index in range(self._size - 1, 0, -1):
            self._node[index] = self._combine_f(
                self._node[index << 1 | 0],  # 左の子
                self._node[index << 1 | 1],  # 右の子
            )

    # [一点更新] 位置 index (0-indexed) を値 value で更新
    def update(self, index, value):
        i = self._size + index
        self._node[i] = value
        while i > 1:
            i >>= 1
            self._node[i] = self._combine_f(
                self._node[i << 1 | 0],  # 左の子
                self._node[i << 1 | 1],  # 右の子
            )

    # [区間取得] 区間 [l, r) (0-indexed) 内の要素について、
    # l 番目から順に combine_f を適用した結果を返す (交換法則が前提になくてもよい)
    def fold(self, L, R):
        L += self._size
        R += self._size
        value_L = self._identity_e
        value_R = self._identity_e
        while L < R:
            if L & 1:
                value_L = self._combine_f(value_L, self._node[L])
                L += 1
            if R & 1:
                R -= 1
                value_R = self._combine_f(self._node[R], value_R)
            L >>= 1
            R >>= 1
        return self._combine_f(value_L, value_R)

N, D = map(int, input().split())
A = list(map(int, input().split()))
seg = SegmentTree(max(A)+1, 0, max)
seg.build([0]*(max(A)+1))
m = max(A)
dp = [0 for i in range(N + 1)]
for i in range(N):
    t = seg.fold(max(0, A[i]-D), min(m, A[i]+D+1))
    seg.update(A[i], t+1)


print(seg.fold(0, max(A)+1))


