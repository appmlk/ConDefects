class SegmentTree:
    def __init__(self, n, identity_e, combine_f):
        """
        配列を 2 * n 個のノードで初期化する(0-indexed, 頂点は1から)
        n: 列の長さ
        identity_e: 単位元
        combine_f: 2つのデータから値を合成するための関数
        node: 各頂点の中身
        """
        self._n = n
        self._size = 1
        while self._size < self._n:
            self._size <<= 1
        self._identity_e = identity_e
        self._combine_f = combine_f
        self._node = [self._identity_e] * (2 * self._size)

    def build(self, array):
        """ 
        配列 array の各要素を登録する 
        """
        # assert: True なら何も起こらない, False なら AssertionError を返す
        assert len(array) == self._n
        for idx, value in enumerate(array, start = self._size):
            self._node[idx] = value
        for idx in range(self._size - 1, 0, -1):
            self._node[idx] = self._combine_f(
                self._node[idx << 1 | 0], # 左の子
                self._node[idx << 1 | 1], # 右の子
            )

    def update(self, idx, value):
        """
        一点更新: 位置 idx(0-indexed) を値 value で更新
        """
        i = self._size + idx
        self._node[i] = value
        while i > 1:
            i >>= 1
            self._node[i] = self._combine_f(
                self._node[i << 1 | 0], # 左の子
                self._node[i << 1 | 1], # 右の子
            )

    def fold(self, L, R):
        """
        区間取得: 区間 [l, r) (0-indexed) 内の要素について、l 番目から順に
                 combine_f を適用した結果を返す(交換法則が前提になくても良い)
        """
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
    
    def __str__(self):
        return ', '.join([str(x) for x in self._node])


def comp(A):
    # 座標圧縮
    d = {a: i for i, a in enumerate(sorted(set(A)))}
    res = [d[a] for a in A]
    return res


from functools import cmp_to_key
from operator import add

def cmp(a, b):
    if a[0] != b[0]:
        return a[0] - b[0]
    elif a[1] != b[1]:
        return b[1] - a[1]
    else:
        return 0

N = int(input())
HWD = [list(map(int, input().split())) for _ in range(N)]
ss = set()
for i in range(N):
    HWD[i].sort(reverse=True)
    for j in range(3):
        ss.add(HWD[i][j])
HWD.sort(key=cmp_to_key(cmp))
d = {a: i for i, a in enumerate(sorted(ss))}
# print(d)
# print(HWD)
INF = 10**18
seg_size = len(d)
seg = SegmentTree(seg_size, INF, min)
for i in range(N):
    _, h, w = HWD[i]
    h = d[h]
    w = d[w]
    cv = seg.fold(0, h)
    seg.update(h, min(seg.fold(h, h + 1), w))
    if cv < w:
        print('Yes')
        exit()
    # print(cv, max(seg.fold(h, h + 1), w), h, w)
print('No')