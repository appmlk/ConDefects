import time
from typing import Generic, Iterable, Iterator, TypeVar, Optional, List
from array import array
from bisect import bisect_left, bisect_right, insort
import collections
import copy
import heapq
import itertools
import decimal
from decimal import Decimal
import math
import string
import sys
sys.setrecursionlimit(10**6)
def I(): return int(sys.stdin.readline().rstrip())
def MI(): return map(int, sys.stdin.readline().rstrip().split())
def LI(): return list(map(int, sys.stdin.readline().rstrip().split()))
def LII(H): return [list(map(int, sys.stdin.readline().rstrip().split())) for _ in range(H)]
def ST(): return sys.stdin.readline().rstrip()
def SS(H): return [ST() for _ in range(H)]
def LS(): return list(sys.stdin.readline().rstrip().split())
def ARRAY(L): return array("i", L)


INF = (1 << 63)-1
DIFF = 10 ** -9
DX = [1, 0, -1, 0, 1, 1, -1, -1]
DY = [0, 1, 0, -1, 1, -1, 1, -1]
MOD = 998244353

# 累積和 ans=list(itertools.accumulate(L))
# 順列 ans=list(itertools.permutation(L))
# 直積 ans=list(itertools.product(L,M))
# 重複なし組み合わせ ans=list(itertools.combinations(L,2))
# 重複あり組み合わせ ans=list(itertools.combinations_with_replacement(L,2))
# nCr ans=math.comb(n,r)


# 日付を比較, date1 < date2ならTrue, O(1)
def compare_date(date1, date2):
    # date = "2019/04/30"
    formatted_date1 = time.strptime(date1, "%Y/%m/%d")
    formatted_date2 = time.strptime(date2, "%Y/%m/%d")
    return formatted_date1 < formatted_date2


# 四捨五入
def half_up(x):
    return Decimal(x).quantize(Decimal('1'), rounding=decimal.ROUND_HALF_UP)


# 切り上げ
def ceiling(x):
    return Decimal(x).quantize(Decimal('0.1'), rounding=decimal.ROUND_CEILING)


# 切り捨て
def floor(x):
    return Decimal(x).quantize(Decimal('0.1'), rounding=decimal.ROUND_FLOOR)


# Nまでの数を素数かどうか判定（エラトステネスのふるい）
def prime_judge(n: int):
    is_prime = [True] * (n + 1)
    is_prime[0], is_prime[1] = False, False
    for i in range(2, int(math.sqrt(n)) + 1):
        if is_prime[i]:
            for j in range(2 * i, n + 1, i):
                is_prime[j] = False
    return is_prime


# 素因数分解
def prime_factorization(N: int):
    arr = []
    sqrt = int(math.sqrt(N))
    for i in range(2, sqrt+1):
        if N % i == 0:
            cnt = 0
            while N % i == 0:
                cnt += 1
                N //= i
            arr.append([i, cnt])
    if N != 1:
        arr.append([N, 1])
    return arr


# 1文字ずつ文字列をずらしたときの最長共通接頭辞の長さ
def z_algorithm(S: string):
    n = len(S)
    rtn = [-1]*n
    rtn[0] = n
    i, j = 1, 0
    while i < len(S):
        while i+j < len(S) and S[j] == S[i+j]:
            j += 1
        rtn[i] = j
        if j == 0:
            i += 1
        k = 1
        while i+k < len(S) and k+rtn[k] < j:
            rtn[i+k] = rtn[k]
            k += 1
        i += k
        j -= k
    return rtn


# Nまでの積をxで何回割り切れるか
def divide_num(N: int, x: int):
    rtn = 0
    i = 1
    while pow(x, i) <= N:
        rtn += N//pow(x, i)
        i += 1
    return rtn


# 拡張ユークリッド互除法
def xgcd(a, b):
    x0, y0, x1, y1 = 1, 0, 0, 1
    while b != 0:
        q, a, b = a//b, b, a % b
        x0, x1 = x1, x0-q*x1
        y0, y1 = y1, y0-q*y1
    return a, x0, y0


# 逆元を求める
def modinv(a, mod=MOD):
    g, x, y = xgcd(a, mod)
    if g != 1:
        raise Exception("moduler inverse does not exist")
    else:
        return x % mod


# combinationの逆元を求める
def combination_modinv(n, r, mod=MOD):
    if n < 0 or r < 0 or n < r:
        return 0
    fact = [1]*(n+2)
    fact_inv = [1]*(n+2)
    for i in range(1, n+2):
        fact[i] = (fact[i-1]*i) % mod
    fact_inv[n+1] = pow(fact[n+1], mod-2, mod)
    for i in range(n, -1, -1):
        fact_inv[i] = (fact_inv[i+1]*(i+1)) % mod
    return (fact[n]*fact_inv[r] % mod)*fact_inv[n-r] % mod


class BIT:
    # 長さN+1の配列を初期化
    def __init__(self, N):
        self.size = N
        self.bit = [0]*(N+1)

    # i番目までの和を求める
    def sum(self, i):
        res = 0
        while i > 0:
            res += self.bit[i]  # フェニック木のi番目の値を加算
            i -= -i & i  # 最も右にある1の桁を0にする
        return res

    # i番目の値にxを足して更新する
    def add(self, i, x):
        while i <= self.size:
            self.bit[i] += x  # フェニック木のi番目にxを足して更新
            i += -i & i  # 最も右にある1の桁に1を足す


# N mod p = a, N mod q = b
# px + a = qy + b
# px - qy = b-a
# px = 1 mod q
def crp(a, p, b, q):
    x = pow(p, -1, q)
    x *= b-a
    x %= q
    return p*x + a


def Dijkstra(edges, num_node):
    """ 経路の表現
            [終点, 辺の値]
            A, B, C, D, ... → 0, 1, 2, ...とする """
    node = [float('inf')] * num_node  # スタート地点以外の値は∞で初期化
    node[0] = 0  # スタートは0で初期化

    node_name = [i for i in range(num_node)]  # ノードの名前を0~ノードの数で表す

    while len(node_name) > 0:
        r = node_name[0]

        # 最もコストが小さい頂点を探す
        for i in node_name:
            if node[i] < node[r]:
                r = i  # コストが小さい頂点が見つかると更新

        # 最もコストが小さい頂点を取り出す
        min_point = node_name.pop(node_name.index(r))

        # 経路の要素を各変数に格納することで，視覚的に見やすくする
        for factor in edges[min_point]:
            goal = factor[0]  # 終点
            cost = factor[1]  # コスト

            # 更新条件
            if node[min_point] + cost < node[goal]:
                node[goal] = node[min_point] + cost  # 更新

    return node


# 行きがけ、帰りがけの順番を取得
def EulerTour(n, graph, root):
    """
    (n: int, graph: List[List[int]], root: int) -> Tuple[List[int], List[int], List[int]]:

    :param n: the number of vertex points
    :param graph: 2D matrix of N vertices given by the edges
    :param root: start node index

    :return tour: order of visited vertex
    :return in_time: first visiting time of each vertex
    :return out_time: last visiting time of each vertex

    example:
    graph = [[] for _ in range(n)]
    for _ in range(n):
        a, b = map(int, input().split())
        graph[a].append(b)
        graph[b].append(a)

    tour, in_time, out_time = EulerTour(n, graph, 0)
    """

    parent = [-1] * n
    stack = [~root, root]  # postorder, preorder
    curr_time = -1
    tour = []
    in_time = [-1] * n
    out_time = [-1] * n
    while stack:
        curr_node = stack.pop()
        curr_time += 1
        if curr_node >= 0:  # preorder
            tour.append(curr_node)
            if in_time[curr_node] == -1:
                in_time[curr_node] = curr_time

            for next_node in graph[curr_node]:
                if next_node != parent[curr_node]:
                    parent[next_node] = curr_node
                    stack.append(~next_node)
                    stack.append(next_node)
        elif curr_node < 0:  # postorder
            out_time[~curr_node] = curr_time
            if parent[~curr_node] != -1:
                tour.append(parent[~curr_node])

    return tour, in_time, out_time


class UnionFind():
    def __init__(self, n):
        self.n = n
        self.parents = [-1] * n

    def find(self, x):
        if self.parents[x] < 0:
            return x
        else:
            self.parents[x] = self.find(self.parents[x])
            return self.parents[x]

    def union(self, x, y):
        x = self.find(x)
        y = self.find(y)

        if x == y:
            return

        if self.parents[x] > self.parents[y]:
            x, y = y, x

        self.parents[x] += self.parents[y]
        self.parents[y] = x

    def size(self, x):
        return -self.parents[self.find(x)]

    def same(self, x, y):
        return self.find(x) == self.find(y)

    def members(self, x):
        root = self.find(x)
        return [i for i in range(self.n) if self.find(i) == root]

    def roots(self):
        return [i for i, x in enumerate(self.parents) if x < 0]

    def group_count(self):
        return len(self.roots())

    def all_group_members(self):
        group_members = collections.defaultdict(list)
        for member in range(self.n):
            group_members[self.find(member)].append(member)
        return group_members

    def __str__(self):
        return '\n'.join(f'{r}: {m}' for r, m in self.all_group_members().items())


def make_kmp_table(t):
    i = 2
    j = 0
    m = len(t)
    tbl = [0] * (m + 1)
    tbl[0] = -1
    while i <= m:
        if t[i - 1] == t[j]:
            tbl[i] = j + 1
            i += 1
            j += 1
        elif j > 0:
            j = tbl[j]
        else:
            tbl[i] = 0
            i += 1
    return tbl


# 文字列Sの中に文字列Tが存在するかどうか
def kmp(s, t):
    matched_indices = []
    tbl = make_kmp_table(t)
    i = 0
    j = 0
    n = len(s)
    m = len(t)
    while i + j < n:
        if t[j] == s[i + j]:
            j += 1
            if j == m:
                matched_indices.append(i)
                i += j - tbl[j]
                j = tbl[j]
        else:
            i += j - tbl[j]
            if j > 0:
                j = tbl[j]
    return matched_indices


# 強連結成分分解
class SCC:
    def __init__(self, n):
        self.n = n
        self.graph = [[] for _ in range(n)]
        self.rev_graph = [[] for _ in range(n)]
        self.labels = [-1] * n
        self.lb_cnt = 0

    def add_edge(self, v, nxt_v):
        self.graph[v].append(nxt_v)
        self.rev_graph[nxt_v].append(v)

    def build(self):
        self.post_order = []
        self.used = [False] * self.n
        for v in range(self.n):
            if not self.used[v]:
                self._dfs(v)
        for v in reversed(self.post_order):
            if self.labels[v] == -1:
                self._rev_dfs(v)
                self.lb_cnt += 1

    def _dfs(self, v):
        stack = [v, 0]
        while stack:
            v, idx = stack[-2:]
            if not idx and self.used[v]:
                stack.pop()
                stack.pop()
            else:
                self.used[v] = True
                if idx < len(self.graph[v]):
                    stack[-1] += 1
                    stack.append(self.graph[v][idx])
                    stack.append(0)
                else:
                    stack.pop()
                    self.post_order.append(stack.pop())

    def _rev_dfs(self, v):
        stack = [v]
        self.labels[v] = self.lb_cnt
        while stack:
            v = stack.pop()
            for nxt_v in self.rev_graph[v]:
                if self.labels[nxt_v] != -1:
                    continue
                stack.append(nxt_v)
                self.labels[nxt_v] = self.lb_cnt

    def construct(self):
        self.dag = [[] for i in range(self.lb_cnt)]
        self.groups = [[] for i in range(self.lb_cnt)]
        for v, lb in enumerate(self.labels):
            for nxt_v in self.graph[v]:
                nxt_lb = self.labels[nxt_v]
                if lb == nxt_lb:
                    continue
                self.dag[lb].append(nxt_lb)
            self.groups[lb].append(v)
        return self.dag, self.groups


class SegTree:
    """ 点代入/区間総和
        操作 	segfunc 	単位元
        最小値 	min(x, y) 	float('inf')
        最大値 	max(x, y) 	-float('inf')
        区間和 	x + y 	0
        区間積 	x * y 	1
        最大公約数 	math.gcd(x, y) 	0
    segfunc : 和の計算がデフォ、最小値・最大値などのモノイドに置換
    add : k番目の値にxを加算
    update : k番目の値をxに更新
    query : 区間[l,r)のseg_funcモノイドの結果を出力
    """

    def __init__(self, init_val: list, ide_ele: int = 0):
        n = len(init_val)
        self.ide_ele = ide_ele
        self.num = 1 << (n-1).bit_length()
        self.tree = [ide_ele]*2*self.num
        for i in range(n):
            self.tree[self.num+i] = init_val[i]
        for i in range(self.num-1, 0, -1):
            self.tree[i] = self.segfunc(self.tree[2*i], self.tree[2*i+1])

    def segfunc(self, x, y):
        return x+y

    def add(self, k, x):
        k += self.num
        self.tree[k] += x
        while k > 1:
            self.tree[k >> 1] = self.segfunc(self.tree[k], self.tree[k ^ 1])
            k >>= 1

    def update(self, k, x):
        k += self.num
        self.tree[k] = x
        while k > 1:
            self.tree[k >> 1] = self.segfunc(self.tree[k], self.tree[k ^ 1])
            k >>= 1

    def query(self, l, r):
        res = self.ide_ele
        l += self.num
        r += self.num
        while l < r:
            if l & 1:
                res = self.segfunc(res, self.tree[l])
                l += 1
            if r & 1:
                res = self.segfunc(res, self.tree[r-1])
            l >>= 1
            r >>= 1
        return res


class LazySegTree_RAQ:
    """ 区間代入/区間総和
    seg_func : 和の計算がデフォ、最小値・最大値などのモノイドに置換
    add : 区間[l,r)の値にxを加算
    query : 区間[l,r)のseg_funcモノイドの結果を出力
    """

    def __init__(self, init_val, segfunc=None, ide_ele: int = 0):
        n = len(init_val)
        self.ide_ele = ide_ele
        if segfunc is not None:
            self.segfunc = segfunc
        self.num = 1 << (n-1).bit_length()
        self.tree = [ide_ele]*2*self.num
        self.lazy = [0]*2*self.num
        for i in range(n):
            self.tree[self.num+i] = init_val[i]
        for i in range(self.num-1, 0, -1):
            self.tree[i] = self.segfunc(self.tree[2*i], self.tree[2*i+1])

    def segfunc(x, y):
        return x+y

    def gindex(self, l, r):
        l += self.num
        r += self.num
        lm = l >> (l & -l).bit_length()
        rm = r >> (r & -r).bit_length()
        while r > l:
            if l <= lm:
                yield l
            if r <= rm:
                yield r
            r >>= 1
            l >>= 1
        while l:
            yield l
            l >>= 1

    def propagates(self, *ids):
        for i in reversed(ids):
            v = self.lazy[i]
            if v == 0:
                continue
            self.lazy[i] = 0
            self.lazy[2*i] += v
            self.lazy[2*i+1] += v
            self.tree[2*i] += v
            self.tree[2*i+1] += v

    def add(self, l, r, x):
        ids = self.gindex(l, r)
        l += self.num
        r += self.num
        while l < r:
            if l & 1:
                self.lazy[l] += x
                self.tree[l] += x
                l += 1
            if r & 1:
                self.lazy[r-1] += x
                self.tree[r-1] += x
            r >>= 1
            l >>= 1
        for i in ids:
            self.tree[i] = self.segfunc(self.tree[2*i], self.tree[2*i+1]) + self.lazy[i]

    def query(self, l, r):
        self.propagates(*self.gindex(l, r))
        res = self.ide_ele
        l += self.num
        r += self.num
        while l < r:
            if l & 1:
                res = self.segfunc(res, self.tree[l])
                l += 1
            if r & 1:
                res = self.segfunc(res, self.tree[r-1])
            l >>= 1
            r >>= 1
        return res


class LazySegTree_RUQ:
    """ 区間代入/区間総和
    seg_func : 和の計算がデフォ、最小値・最大値などのモノイドに置換
    update : 区間[l,r)の値をxに加算
    query : 区間[l,r)のseg_funcモノイドの結果を出力
    """

    def __init__(self, init_val: list, segfunc=None, ide_ele: int = 0):
        n = len(init_val)
        self.ide_ele = ide_ele
        self.num = 1 << (n-1).bit_length()
        if segfunc is not None:
            self.segfunc = segfunc
        self.tree = [ide_ele]*2*self.num
        self.lazy = [None]*2*self.num
        for i in range(n):
            self.tree[self.num+i] = init_val[i]
        for i in range(self.num-1, 0, -1):
            self.tree[i] = self.segfunc(self.tree[2*i], self.tree[2*i+1])

    def segfunc(x, y):
        return min(x, y)

    def gindex(self, l, r):
        l += self.num
        r += self.num
        lm = l >> (l & -l).bit_length()
        rm = r >> (r & -r).bit_length()
        while r > l:
            if l <= lm:
                yield l
            if r <= rm:
                yield r
            r >>= 1
            l >>= 1
        while l:
            yield l
            l >>= 1

    def propagates(self, *ids):
        for i in reversed(ids):
            v = self.lazy[i]
            if v is None:
                continue
            self.lazy[i] = None
            self.lazy[2*i] = v
            self.lazy[2*i+1] = v
            self.tree[2*i] = v
            self.tree[2*i+1] = v

    def update(self, l, r, x):
        ids = self.gindex(l, r)
        self.propagates(*self.gindex(l, r))
        l += self.num
        r += self.num
        while l < r:
            if l & 1:
                self.lazy[l] = x
                self.tree[l] = x
                l += 1
            if r & 1:
                self.lazy[r-1] = x
                self.tree[r-1] = x
            r >>= 1
            l >>= 1
        for i in ids:
            self.tree[i] = self.segfunc(self.tree[2*i], self.tree[2*i+1])

    def query(self, l, r):
        ids = self.gindex(l, r)
        self.propagates(*self.gindex(l, r))
        res = self.ide_ele
        l += self.num
        r += self.num
        while l < r:
            if l & 1:
                res = self.segfunc(res, self.tree[l])
                l += 1
            if r & 1:
                res = self.segfunc(res, self.tree[r-1])
            l >>= 1
            r >>= 1
        return res


T = TypeVar('T')


class SortedSet(Generic[T]):
    BUCKET_RATIO = 50
    REBUILD_RATIO = 170

    def _build(self, a=None) -> None:
        "Evenly divide `a` into buckets."
        if a is None:
            a = list(self)
        size = self.size = len(a)
        bucket_size = int(math.ceil(math.sqrt(size / self.BUCKET_RATIO)))
        self.a = [a[size * i // bucket_size: size * (i + 1) // bucket_size] for i in range(bucket_size)]

    def __init__(self, a: Iterable[T] = []) -> None:
        "Make a new SortedSet from iterable. / O(N) if sorted and unique / O(N log N)"
        a = list(a)
        if not all(a[i] < a[i + 1] for i in range(len(a) - 1)):
            a = sorted(set(a))
        self._build(a)

    def __iter__(self) -> Iterator[T]:
        for i in self.a:
            for j in i:
                yield j

    def __reversed__(self) -> Iterator[T]:
        for i in reversed(self.a):
            for j in reversed(i):
                yield j

    def __len__(self) -> int:
        return self.size

    def __repr__(self) -> str:
        return "SortedSet" + str(self.a)

    def __str__(self) -> str:
        s = str(list(self))
        return "{" + s[1: len(s) - 1] + "}"

    def _find_bucket(self, x: T) -> List[T]:
        "Find the bucket which should contain x. self must not be empty."
        for a in self.a:
            if x <= a[-1]:
                return a
        return a

    def __contains__(self, x: T) -> bool:
        if self.size == 0:
            return False
        a = self._find_bucket(x)
        i = bisect_left(a, x)
        return i != len(a) and a[i] == x

    def add(self, x: T) -> bool:
        "Add an element and return True if added. / O(√N)"
        if self.size == 0:
            self.a = [[x]]
            self.size = 1
            return True
        a = self._find_bucket(x)
        i = bisect_left(a, x)
        if i != len(a) and a[i] == x:
            return False
        a.insert(i, x)
        self.size += 1
        if len(a) > len(self.a) * self.REBUILD_RATIO:
            self._build()
        return True

    def discard(self, x: T) -> bool:
        "Remove an element and return True if removed. / O(√N)"
        if self.size == 0:
            return False
        a = self._find_bucket(x)
        i = bisect_left(a, x)
        if i == len(a) or a[i] != x:
            return False
        a.pop(i)
        self.size -= 1
        if len(a) == 0:
            self._build()
        return True

    def lt(self, x: T) -> Optional[T]:
        "Find the largest element < x, or None if it doesn't exist."
        for a in reversed(self.a):
            if a[0] < x:
                return a[bisect_left(a, x) - 1]

    def le(self, x: T) -> Optional[T]:
        "Find the largest element <= x, or None if it doesn't exist."
        for a in reversed(self.a):
            if a[0] <= x:
                return a[bisect_right(a, x) - 1]

    def gt(self, x: T) -> Optional[T]:
        "Find the smallest element > x, or None if it doesn't exist."
        for a in self.a:
            if a[-1] > x:
                return a[bisect_right(a, x)]

    def ge(self, x: T) -> Optional[T]:
        "Find the smallest element >= x, or None if it doesn't exist."
        for a in self.a:
            if a[-1] >= x:
                return a[bisect_left(a, x)]

    def __getitem__(self, x: int) -> T:
        "Return the x-th element, or IndexError if it doesn't exist."
        if x < 0:
            x += self.size
        if x < 0:
            raise IndexError
        for a in self.a:
            if x < len(a):
                return a[x]
            x -= len(a)
        raise IndexError

    def index(self, x: T) -> int:
        "Count the number of elements < x."
        ans = 0
        for a in self.a:
            if a[-1] >= x:
                return ans + bisect_left(a, x)
            ans += len(a)
        return ans

    def index_right(self, x: T) -> int:
        "Count the number of elements <= x."
        ans = 0
        for a in self.a:
            if a[-1] > x:
                return ans + bisect_right(a, x)
            ans += len(a)
        return ans


class SortedMultiset(Generic[T]):
    BUCKET_RATIO = 50
    REBUILD_RATIO = 170

    def _build(self, a=None) -> None:
        "Evenly divide `a` into buckets."
        if a is None:
            a = list(self)
        size = self.size = len(a)
        bucket_size = int(math.ceil(math.sqrt(size / self.BUCKET_RATIO)))
        self.a = [a[size * i // bucket_size: size * (i + 1) // bucket_size] for i in range(bucket_size)]

    def __init__(self, a: Iterable[T] = []) -> None:
        "Make a new SortedMultiset from iterable. / O(N) if sorted / O(N log N)"
        a = list(a)
        if not all(a[i] <= a[i + 1] for i in range(len(a) - 1)):
            a = sorted(a)
        self._build(a)

    def __iter__(self) -> Iterator[T]:
        for i in self.a:
            for j in i:
                yield j

    def __reversed__(self) -> Iterator[T]:
        for i in reversed(self.a):
            for j in reversed(i):
                yield j

    def __len__(self) -> int:
        return self.size

    def __repr__(self) -> str:
        return "SortedMultiset" + str(self.a)

    def __str__(self) -> str:
        s = str(list(self))
        return "{" + s[1: len(s) - 1] + "}"

    def _find_bucket(self, x: T) -> List[T]:
        "Find the bucket which should contain x. self must not be empty."
        for a in self.a:
            if x <= a[-1]:
                return a
        return a

    def __contains__(self, x: T) -> bool:
        if self.size == 0:
            return False
        a = self._find_bucket(x)
        i = bisect_left(a, x)
        return i != len(a) and a[i] == x

    def count(self, x: T) -> int:
        "Count the number of x."
        return self.index_right(x) - self.index(x)

    def add(self, x: T) -> None:
        "Add an element. / O(√N)"
        if self.size == 0:
            self.a = [[x]]
            self.size = 1
            return
        a = self._find_bucket(x)
        insort(a, x)
        self.size += 1
        if len(a) > len(self.a) * self.REBUILD_RATIO:
            self._build()

    def discard(self, x: T) -> bool:
        "Remove an element and return True if removed. / O(√N)"
        if self.size == 0:
            return False
        a = self._find_bucket(x)
        i = bisect_left(a, x)
        if i == len(a) or a[i] != x:
            return False
        a.pop(i)
        self.size -= 1
        if len(a) == 0:
            self._build()
        return True

    def lt(self, x: T) -> Optional[T]:
        "Find the largest element < x, or None if it doesn't exist."
        for a in reversed(self.a):
            if a[0] < x:
                return a[bisect_left(a, x) - 1]

    def le(self, x: T) -> Optional[T]:
        "Find the largest element <= x, or None if it doesn't exist."
        for a in reversed(self.a):
            if a[0] <= x:
                return a[bisect_right(a, x) - 1]

    def gt(self, x: T) -> Optional[T]:
        "Find the smallest element > x, or None if it doesn't exist."
        for a in self.a:
            if a[-1] > x:
                return a[bisect_right(a, x)]

    def ge(self, x: T) -> Optional[T]:
        "Find the smallest element >= x, or None if it doesn't exist."
        for a in self.a:
            if a[-1] >= x:
                return a[bisect_left(a, x)]

    def __getitem__(self, x: int) -> T:
        "Return the x-th element, or IndexError if it doesn't exist."
        if x < 0:
            x += self.size
        if x < 0:
            raise IndexError
        for a in self.a:
            if x < len(a):
                return a[x]
            x -= len(a)
        raise IndexError

    def index(self, x: T) -> int:
        "Count the number of elements < x."
        ans = 0
        for a in self.a:
            if a[-1] >= x:
                return ans + bisect_left(a, x)
            ans += len(a)
        return ans

    def index_right(self, x: T) -> int:
        "Count the number of elements <= x."
        ans = 0
        for a in self.a:
            if a[-1] > x:
                return ans + bisect_right(a, x)
            ans += len(a)
        return ans


# 配列の転倒数を求める（o(NlogN)）
def inversion_num(L: list):
    max_N = max(L)
    bit = BIT(max_N)
    cnt = 0
    for i, l in enumerate(L):
        cnt += i-bit.sum(l)
        bit.add(l, 1)
    return cnt


# 約数列挙
def make_divisors(n):
    lower_divisors, upper_divisors = [], []
    i = 1
    while i*i <= n:
        if n % i == 0:
            lower_divisors.append(i)
            if i != n // i:
                upper_divisors.append(n//i)
        i += 1
    return lower_divisors + upper_divisors[::-1]


# Nまでの素数列挙
# 計算量 O(logN)
def prime_list(N):
    H = [False] * (N + 1)
    primes = []
    for i in range(2, N + 1):
        if H[i]:
            continue
        primes.append(i)
        for j in range(i, N + 1, i):
            H[j] = True
    return primes


class ModInt:
    def __init__(self, x, mod=998244353):
        self.x = x % mod
        self.mod = mod

    def __str__(self):
        return str(self.x)

    __repr__ = __str__

    def __add__(self, other):
        return (
            ModInt(self.x + other.x) if isinstance(other, ModInt) else
            ModInt(self.x + other)
        )

    def __sub__(self, other):
        return (
            ModInt(self.x - other.x) if isinstance(other, ModInt) else
            ModInt(self.x - other)
        )

    def __mul__(self, other):
        return (
            ModInt(self.x*other.x) if isinstance(other, ModInt) else
            ModInt(self.x*other)
        )

    def __truediv__(self, other):
        return (
            ModInt(
                self.x*pow(other.x, self.mod-2, self.mod)
            ) if isinstance(other, ModInt) else
            ModInt(self.x*pow(other, self.mod-2, self.mod))
        )

    def __pow__(self, other):
        return (
            ModInt(pow(self.x, other.x, self.mod)) if isinstance(other, ModInt) else
            ModInt(pow(self.x, other, self.mod))
        )

    __radd__ = __add__

    def __rsub__(self, other):
        return (
            ModInt(other.x-self.x) if isinstance(other, ModInt) else
            ModInt(other-self.x)
        )

    __rmul__ = __mul__

    def __rtruediv__(self, other):
        return (
            ModInt(
                other.x * pow(self.x, self.mod-2, self.mod)
            ) if isinstance(other, ModInt) else
            ModInt(other * pow(self.x, self.mod-2, self.mod))
        )

    def __rpow__(self, other):
        return (
            ModInt(pow(other.x, self.x, self.mod)) if isinstance(other, ModInt) else
            ModInt(pow(other, self.x, self.mod))
        )


class FFT():
    def primitive_root_constexpr(self, m):
        if m == 2:
            return 1
        if m == 167772161:
            return 3
        if m == 469762049:
            return 3
        if m == 754974721:
            return 11
        if m == 998244353:
            return 3
        divs = [0]*20
        divs[0] = 2
        cnt = 1
        x = (m-1)//2
        while (x % 2 == 0):
            x //= 2
        i = 3
        while (i*i <= x):
            if (x % i == 0):
                divs[cnt] = i
                cnt += 1
                while (x % i == 0):
                    x //= i
            i += 2
        if x > 1:
            divs[cnt] = x
            cnt += 1
        g = 2
        while (1):
            ok = True
            for i in range(cnt):
                if pow(g, (m-1)//divs[i], m) == 1:
                    ok = False
                    break
            if ok:
                return g
            g += 1

    def bsf(self, x):
        res = 0
        while (x % 2 == 0):
            res += 1
            x //= 2
        return res
    rank2 = 0
    root = []
    iroot = []
    rate2 = []
    irate2 = []
    rate3 = []
    irate3 = []

    def __init__(self, MOD):
        self.mod = MOD
        self.g = self.primitive_root_constexpr(self.mod)
        self.rank2 = self.bsf(self.mod-1)
        self.root = [0 for i in range(self.rank2+1)]
        self.iroot = [0 for i in range(self.rank2+1)]
        self.rate2 = [0 for i in range(self.rank2)]
        self.irate2 = [0 for i in range(self.rank2)]
        self.rate3 = [0 for i in range(self.rank2-1)]
        self.irate3 = [0 for i in range(self.rank2-1)]
        self.root[self.rank2] = pow(self.g, (self.mod-1) >> self.rank2, self.mod)
        self.iroot[self.rank2] = pow(self.root[self.rank2], self.mod-2, self.mod)
        for i in range(self.rank2-1, -1, -1):
            self.root[i] = (self.root[i+1]**2) % self.mod
            self.iroot[i] = (self.iroot[i+1]**2) % self.mod
        prod = 1
        iprod = 1
        for i in range(self.rank2-1):
            self.rate2[i] = (self.root[i+2]*prod) % self.mod
            self.irate2[i] = (self.iroot[i+2]*iprod) % self.mod
            prod = (prod*self.iroot[i+2]) % self.mod
            iprod = (iprod*self.root[i+2]) % self.mod
        prod = 1
        iprod = 1
        for i in range(self.rank2-2):
            self.rate3[i] = (self.root[i+3]*prod) % self.mod
            self.irate3[i] = (self.iroot[i+3]*iprod) % self.mod
            prod = (prod*self.iroot[i+3]) % self.mod
            iprod = (iprod*self.root[i+3]) % self.mod

    def butterfly(self, a):
        n = len(a)
        h = (n-1).bit_length()

        LEN = 0
        while (LEN < h):
            if (h-LEN == 1):
                p = 1 << (h-LEN-1)
                rot = 1
                for s in range(1 << LEN):
                    offset = s << (h-LEN)
                    for i in range(p):
                        l = a[i+offset]
                        r = a[i+offset+p]*rot
                        a[i+offset] = (l+r) % self.mod
                        a[i+offset+p] = (l-r) % self.mod
                    rot *= self.rate2[(~s & -~s).bit_length()-1]
                    rot %= self.mod
                LEN += 1
            else:
                p = 1 << (h-LEN-2)
                rot = 1
                imag = self.root[2]
                for s in range(1 << LEN):
                    rot2 = (rot*rot) % self.mod
                    rot3 = (rot2*rot) % self.mod
                    offset = s << (h-LEN)
                    for i in range(p):
                        a0 = a[i+offset]
                        a1 = a[i+offset+p]*rot
                        a2 = a[i+offset+2*p]*rot2
                        a3 = a[i+offset+3*p]*rot3
                        a1na3imag = (a1-a3) % self.mod*imag
                        a[i+offset] = (a0+a2+a1+a3) % self.mod
                        a[i+offset+p] = (a0+a2-a1-a3) % self.mod
                        a[i+offset+2*p] = (a0-a2+a1na3imag) % self.mod
                        a[i+offset+3*p] = (a0-a2-a1na3imag) % self.mod
                    rot *= self.rate3[(~s & -~s).bit_length()-1]
                    rot %= self.mod
                LEN += 2

    def butterfly_inv(self, a):
        n = len(a)
        h = (n-1).bit_length()
        LEN = h
        while (LEN):
            if (LEN == 1):
                p = 1 << (h-LEN)
                irot = 1
                for s in range(1 << (LEN-1)):
                    offset = s << (h-LEN+1)
                    for i in range(p):
                        l = a[i+offset]
                        r = a[i+offset+p]
                        a[i+offset] = (l+r) % self.mod
                        a[i+offset+p] = (l-r)*irot % self.mod
                    irot *= self.irate2[(~s & -~s).bit_length()-1]
                    irot %= self.mod
                LEN -= 1
            else:
                p = 1 << (h-LEN)
                irot = 1
                iimag = self.iroot[2]
                for s in range(1 << (LEN-2)):
                    irot2 = (irot*irot) % self.mod
                    irot3 = (irot*irot2) % self.mod
                    offset = s << (h-LEN+2)
                    for i in range(p):
                        a0 = a[i+offset]
                        a1 = a[i+offset+p]
                        a2 = a[i+offset+2*p]
                        a3 = a[i+offset+3*p]
                        a2na3iimag = (a2-a3)*iimag % self.mod
                        a[i+offset] = (a0+a1+a2+a3) % self.mod
                        a[i+offset+p] = (a0-a1+a2na3iimag)*irot % self.mod
                        a[i+offset+2*p] = (a0+a1-a2-a3)*irot2 % self.mod
                        a[i+offset+3*p] = (a0-a1-a2na3iimag)*irot3 % self.mod
                    irot *= self.irate3[(~s & -~s).bit_length()-1]
                    irot %= self.mod
                LEN -= 2

    def convolution(self, a, b):
        n = len(a)
        m = len(b)
        if not (a) or not (b):
            return []
        if min(n, m) <= 40:
            res = [0]*(n+m-1)
            for i in range(n):
                for j in range(m):
                    res[i+j] += a[i]*b[j]
                    res[i+j] %= self.mod
            return res
        z = 1 << ((n+m-2).bit_length())
        a = a+[0]*(z-n)
        b = b+[0]*(z-m)
        self.butterfly(a)
        self.butterfly(b)
        c = [(a[i]*b[i]) % self.mod for i in range(z)]
        self.butterfly_inv(c)
        iz = pow(z, self.mod-2, self.mod)
        for i in range(n+m-1):
            c[i] = (c[i]*iz) % self.mod
        return c[:n+m-1]


class mf_graph:
    n = 1
    g = [[] for i in range(1)]
    pos = []

    def __init__(self, N):
        self.n = N
        self.g = [[] for i in range(N)]
        self.pos = []

    def add_edge(self, From, To, cap):
        assert 0 <= From and From < self.n
        assert 0 <= To and To < self.n
        assert 0 <= cap
        m = len(self.pos)
        from_id = len(self.g[From])
        self.pos.append([From, from_id])
        to_id = len(self.g[To])
        if From == To:
            to_id += 1
        self.g[From].append([To, to_id, cap])
        self.g[To].append([From, from_id, 0])
        return m

    def get_edge(self, i):
        m = len(self.pos)
        assert 0 <= i and i < m
        _e = self.g[self.pos[i][0]][self.pos[i][1]]
        _re = self.g[_e[0]][_e[1]]
        return [self.pos[i][0], _e[0], _e[2]+_re[2], _re[2]]

    def edges(self):
        m = len(self.pos)
        result = []
        for i in range(m):
            a, b, c, d = self.get_edge(i)
            result.append({"from": a, "to": b, "cap": c, "flow": d})
        return result

    def change_edge(self, i, new_cap, new_flow):
        m = len(self.pos)
        assert 0 <= i and i < m
        assert 0 <= new_flow and new_flow <= new_cap
        _e = self.g[self.pos[i][0]][self.pos[i][1]]
        _re = self.g[_e[0]][_e[1]]
        _e[2] = new_cap-new_flow
        _re[2] = new_flow

    def flow(self, s, t, flow_limit=(1 << 63)-1):
        assert 0 <= s and s < self.n
        assert 0 <= t and t < self.n
        assert s != t

        def bfs():
            level = [-1 for i in range(self.n)]
            level[s] = 0
            que = collections.deque([])
            que.append(s)
            while (que):
                v = que.popleft()
                for to, rev, cap in self.g[v]:
                    if cap == 0 or level[to] >= 0:
                        continue
                    level[to] = level[v]+1
                    if to == t:
                        return level
                    que.append(to)
            return level

        def dfs(v, up):
            if (v == s):
                return up
            res = 0
            level_v = level[v]
            for i in range(Iter[v], len(self.g[v])):
                Iter[v] = i
                to, rev, cap = self.g[v][i]
                if (level_v <= level[to] or self.g[to][rev][2] == 0):
                    continue
                d = dfs(to, min(up-res, self.g[to][rev][2]))
                if d <= 0:
                    continue
                self.g[v][i][2] += d
                self.g[to][rev][2] -= d
                res += d
                if res == up:
                    return res
            level[v] = self.n
            return res

        flow = 0
        while (flow < flow_limit):
            level = bfs()
            if level[t] == -1:
                break
            Iter = [0 for i in range(self.n)]
            f = dfs(t, flow_limit-flow)
            if not (f):
                break
            flow += f
        return flow

    def min_cut(self, s):
        visited = [False for i in range(self.n)]
        que = collections.deque([])
        que.append(s)
        while (len(que) > 0):
            p = que.popleft()
            visited[p] = True
            for to, rev, cap in self.g[p]:
                if cap and not (visited[to]):
                    visited[to] = True
                    que.append(to)
        return visited


# 最長増加部分列（LIS）
def lis(L: list):
    dp = [float('inf')]*len(L)
    for l in L:
        dp[bisect_left(dp, l)] = l
    return bisect_left(dp, float('inf'))


# Floor Sum
def floor_sum(n, m, a, b):
    ret = 0
    while n > 0 and m > 0:
        ret += (a // m) * n * (n-1) // 2 + (b // m) * n
        a, b = a % m, b % m
        last = a * n + b
        n, m, a, b = last // m, a, m, last % m
    return ret


def main():
    sx,sy,tx,ty,ux,uy=map(int,input().split())
    # 平行移動と反転により、目的地を原点、荷物を第一象限にする
    sx-=ux
    sy-=uy
    tx-=ux
    ty-=uy
    if tx<0:
        sx*=-1
        tx*=-1
    if ty<0:
        sy*=-1
        ty*=-1

    def push_down(sx,sy,tx,ty):
        # 高橋君が(sx,sy),荷物が(tx,ty)にあるとき、荷物を(tx,0)に移動するまでの最小行動回数と、移動後の高橋君の座標、荷物の座標を返す
        # ty==0なら何もしない
        if ty==0:
            return 0, sx,sy, tx,ty
        # (tx,ty+1)に行く
        step=abs(sx-tx)+abs(sy-(ty+1))
        # 荷物を迂回する必要があるなら+2
        if sx==tx and sy<ty:
            step+=2
        # 下に押す
        return step+ty, tx,1, tx,0

    ans=[]
    for _ in range(2):
        # 荷物を下→左の順に押す
        # 荷物を下に押す
        step1,a,b,c,d=push_down(sx,sy,tx,ty)
        # 軸を入れ替えることで左に押すことを下に押すことに帰着
        step2,*_=push_down(b,a,d,c)
        ans.append(step1+step2)
        # 軸を入れ替えることで左→下のケースを下→左に帰着
        sx,sy=sy,sx
        tx,ty=ty,tx
    print(min(ans))



if __name__ == "__main__":
    main()
