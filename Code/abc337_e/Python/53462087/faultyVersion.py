from collections import defaultdict, deque, Counter
from sortedcontainers import SortedSet, SortedList, SortedDict
from heapq import heappush, heappop
from atcoder.dsu import DSU
from atcoder.segtree import SegTree
# st = SegTree(lambda dt1,dt2: データ参照, 単位元, 元データ)
from atcoder.lazysegtree import LazySegTree
# lst = LazySegTree(lambda dt1,dt2: データ参照, 恒等写像, lambda lz,dt: データ更新, lambda lz1,lz2: 遅延伝播, 単位元, 元データ)
from bisect import bisect_left, bisect_right
from itertools import product, groupby, permutations, combinations
import math
from copy import copy, deepcopy
import sys
sys.setrecursionlimit(10000000)
# PyPy再帰高速化
import pypyjit
pypyjit.set_param('max_unroll_recursion=-1')



# 切り上げ除算
# 絶対値の切り下げ/切り上げを反転する
def div_ceil(num, div):
    return -(-num//div)

# ユークリッドの互除法 O(logN)
# 最大公約数(gcd)を求める
"""
gcdはaとbの約数の積集合
"""
def gcd(a, b):
    a, b = max(a, b), min(a, b)
    while a%b != 0:
        a, b = b, a%b
    return b

# ユークリッドの互除法と約数集合 O(logN)
# 最小公倍数(lcm)を求める
"""
lcmはaとbの約数の和集合
ただし, lcm(3, 9) = 9のようになるので,
約数集合において1つ目の3と2つ目の3は別として扱うように,
同じ値でも何個目かによって区別する。
割り算は約数の引き算
1.aとbの約数集合を重ねる
2.ダブルカウントされた共通部分GCDを除く
補足: GCDで必ず割り切れるので先に割る
"""
def lcm(a, b):
    GCD = gcd(a, b)
    return a//GCD*b

# エラトステネスの篩 O(NloglogN)
# N未満の素数列挙
"""
素数の倍数の削除について, N/2+N/3+N/5... = N(1/2+1/3+1/5...)
N未満の素数の逆数和はloglogNになるらしいので, O(NloglogN)
O(NloglogN)がTLEするほど大きな結果をコードに埋め込むことはできない。
TLEしない10^6以下の素数列挙でもテキストのサイズが600kBほどになってしまうため。
"""
def eratosthenes_sieve(lim):
    primes = set(range(3, lim, 2))
    primes.add(2)
    # iが素数か判定(奇数だけ見ていく)
    for i in range(3, lim, 2):
        if i in primes:
            # 素数iの倍数を削除
            for j in range(i*2, lim, i):
                primes.discard(j)
    return primes

# 試し割り法 O(√N)
# Nを素因数分解
"""
N = 10^16でO(10^8)でも424msなので, 実際はみかけより早い
N = 10^18でO(10^9)だと3009ms
N = 10^17だと1048msなので, この辺が限界
"""
def prime_factors(src):
    # pf = {素因数p : 指数e}
    pf = defaultdict(int)
    now = src
    i = 2
    # src = i*j のときjがiより大きいなら既出なので√srcまで確認
    while i*i <= src:
        # 割り切れるなら割れるだけ割る
        while now%i == 0:
            now //= i
            pf[i] += 1
        i += 1
    # それ以上分解できない残ったものを素因数に加える
    if now > 1:
        pf[now] += 1
    return pf

# 累積和 O(N)
# imos法で使う場合は[l, r)のlに+v, rに-vして累積和で復元
# 区間和の取得をO(1)で行える
def pre_sum(src):
    n = len(src)+1
    pre_sum = [0]*n
    for i in range(1, n):
        pre_sum[i] = pre_sum[i-1]+src[i-1]
    return pre_sum

# しゃくとり法 O(N)
"""
# l], (r
r = N-1
for l in range(N):
    # 右の追い越し防止
    while r > l and "ここにrを進める条件":
        r -= 1
    # 左の追い越し修正
    if l > r:
        r = l
    # ここにメインの処理
"""

# 座標圧縮 O(N)
# 10 10 12 24 -> 0 0 1 2 (rankだと0 0 2 3)
def dense_rank(src):
    # srcの重複を除いてソートして値と順位の対応を得る
    d = {v:i for i, v in enumerate(sorted(set(src)))}
    ret = [d[si] for si in src]
    return ret

# ランレングス圧縮 O(N)
def rle(src):
    return [(k, len(list(v))) for k, v in groupby(src)]

# 二分探索 O(logN)
"""
sより小さい最大値の位置を返す(挿入位置ではない)
sは必ず探索範囲内に存在するものとする
同じ値が存在する場合は最も右の値の位置を返す
"""
def binary_search(s):
    # 半開区間[l, r)
    l, r = 0, N
    m = (l+r)//2
    while r-l > 1:
        if A[m] < s:
            l = m
        else:
            r = m
        m = (l+r)//2
        print(m, l, r)
    return m

# bitDP O(4^N) (種類をNとすると(2^N)^2 = 2^(N*2) = 4^N)
"""
選ぶ/選ばないの組合せを表すビットを状態にとる動的計画法
全ての選択肢に対してありうる全ての状態からの遷移を求める
下記のNは組合せのビット数なので注意
"""
def bit_dp(src):
    # 元データの数を状態の数に圧縮する
    src = Counter(src)
    N = 2**len(src)
    dp = [0]*N
    # どれも選ばない場合が1通り
    dp[0] = 1
    for src_bits, cnt in src:
        # 逆順に更新すれば1次元配列でも二重に更新されない
        for frm_bits in range(N-1, -1, -1):
            # srcから1つ以上選ぶ遷移, 爆発しないようにMODをとる
            dp[frm_bits|src_bits] += (dp[frm_bits]*(2**cnt-1))%MOD
            dp[frm_bits|src_bits] %= MOD
    return dp

# 4隣接の取得
# "#"判定は呼び出し側
def get_4adjacent(i, j):
    ret = []
    # 時計回り
    if 0 <= i-1 < H:
        ret.append((i-1, j))
    if 0 <= j+1 < W:
        ret.append((i, j+1))
    if 0 <= i+1 < H:
        ret.append((i+1, j))
    if 0 <= j-1 < W:
        ret.append((i, j-1))
    return ret

# 9隣接の取得
# "#"判定は呼び出し側
def get_9adjacent(frm):
    ret = []
    i, j = frm[0], frm[1]
    d = (-1, 0, 1)
    for di in d:
        ii = i+di
        for dj in d:
            jj = j+dj
            if ii == i and jj == j:
                continue
            if 0 <= ii < H and 0 <= jj < W:
                ret.append((ii, jj))
    return ret

# 3*3のグリッド(要素数9の配列)の縦横斜めがそろっているか判定(oxゲーム)
def judge_line(ox):
    ret = "_"
    for i in range(3):
        # 横一列
        if ox[i*3] == "o" and ox[i*3+1] == "o" and ox[i*3+2] == "o":
            ret = "o"
            break
        if ox[i*3] == "x" and ox[i*3+1] == "x" and ox[i*3+2] == "x":
            ret = "x"
            break
        # 縦一列
        if ox[i] == "o" and ox[i+3] == "o" and ox[i+6] == "o":
            ret = "o"
            break
        if ox[i] == "x" and ox[i+3] == "x" and ox[i+6] == "x":
            ret = "x"
            break
    # 斜め(\, /)
    if ox[0] == "o" and ox[4] == "o" and ox[8] == "o":
        ret = "o"
    if ox[0] == "x" and ox[4] == "x" and ox[8] == "x":
        ret = "x"
    if ox[2] == "o" and ox[4] == "o" and ox[6] == "o":
        ret = "o"
    if ox[2] == "x" and ox[4] == "x" and ox[6] == "x":
        ret = "x"
    return ret

"""
# 深さ優先探索(再帰) O(V+E)
# 根からの距離を求める
# 呼び出し側でdist = [INF]*N, dist[root] = 0
def dfs(frm):
    for to in G[frm]:
        if dist[to] < INF:
            continue
        dist[to] = dist[frm]+1
        dfs(to)
"""

# 深さ優先探索(行きがけのみ/スタック) O(V+E)
# 根からの距離を求める
# 呼び出し側でdist = [INF]*N, dist[root] = 0
def dfs(start):
    st = [start]
    while st:
        frm = st.pop()
        for to in G[frm]:
            if dist[to] < INF:
                continue
            # 行きがけ処理
            dist[to] = dist[frm]+1
            st.append(to)

# 深さ優先探索(行きがけ帰りがけ両対応/スタック) O(V+E)
# 根からの距離を求める
# 呼び出し側でdist = [INF]*N, dist[root] = 0
def dfs(start):
    # 帰りがけ用頂点と行きがけ用頂点を用意する
    st = [~start, start]
    while st:
        frm = st.pop()
        if frm >= 0:
            # 行きがけ処理
            for to in G[frm]:
                if dist[to] < INF:
                    continue
                dist[to] = dist[frm]+1
                st.append(~to)
                st.append(to)
        else:
            # 帰りがけ処理
            frm = ~frm
            # ここに具体的な帰りがけ処理(再帰の戻り値でやるようなやつ)

# 自身を含む自身以下の部分木のコスト総和を求める
def subtree_cost_sum(start):
    st = [~start, start]
    while st:
        frm = st.pop()
        if frm >= 0:
            for to in G[frm]:
                if visited[to]:
                    continue
                visited[to] = True
                st.append(~to)
                st.append(to)
        else:
            frm = ~frm
            # 帰りがけ順に処理されるので自身と直下の子のみ足せばよい
            C_sum[frm] = C[frm]+sum([C_sum[to] for to in G[frm]])

# 木の重心を求める
"""
直線とウニとその間の木で考えても重心での距離総和が最小で,
重心で木を分解したときの部分木の頂点数の最大値の最小値はN//2以下。
複数N//2より大きいtoが存在すると考えると頂点数がNを超えて矛盾するためtoはfrmに対して1つ。
頂点数でなくコストでも同様のことが成り立つ。
"""
def centroid(start):
    st = [start]
    while st:
        frm = st.pop()
        for to in G[frm]:
            if visited[to]:
                continue
            visited[to] = True
            if C_sum[to] > C_sum[0]//2:
                st.append(to)
    return frm

# 幅優先探索 O(V+E)
# BFSごとにリセットする場合: in_visited = defaultdict(bool)
def bfs(start):
    q = deque([start])
    while q:
        frm = q.popleft()
        visited[frm] = True
        for to in G[frm]:
            if visited[to]:
                continue
            visited[to] = True
            q.append(to)

# ダイクストラ法 O((V+E)logV)
"""
単一始点最短経路(1つの頂点からすべての頂点までの最短経路)を求める。
ダイクストラ法のベースは幅優先探索。
最短距離が未確定の頂点のうち,
暫定最短距離が最短の頂点はその時点で距離が確定する。
その頂点は回り道したら必ず距離が暫定最短距離より大きくなるため。
負のコストが存在すると回り道した方がコストが小さくなる可能性があるため,
ダイクストラ法が使えない。
"""
def dijkstra(start):
    # 各頂点の始点からの距離
    dist = [INF]*N
    dist[0] = 0
    # 経路復元用の各頂点の最短経路における前の要素
    prev = [-1]*N
    # visited[i]: 最短距離確定済みか
    visited = [False]*N
    # (距離, 頂点)の優先度付きキュー
    pq = [start]
    
    while pq:
        # 暫定距離最短の頂点の距離を確定してたどる
        frm = heappop(pq)[1]
        if visited[frm]:
           continue
        visited[frm] = True
        for frm_to_dist, to in G[frm]:
            # 暫定最短距離と前の頂点の更新
            if dist[frm]+frm_to_dist < dist[to]:
                dist[to] = dist[frm]+frm_to_dist
                prev[to] = frm
                heappush(pq, (dist[to], to))
    
    # 最短経路を終点から遡って復元
    # デフォルトの終点: N-1
    path = [N-1]
    frm = prev[path[0]]
    while frm > -1:
        path.append(frm)
        frm = prev[frm]
    # frm->to方向に直す
    path = path[::-1]
    
    # 始点から終点までの最短経路, 始点からの最短距離
    # print(*path)
    return dist

# ベルマンフォード法 O(VE)
"""
負の閉路がなければ再訪問は起きないので,
最短経路の経由頂点数の最大値はV-1個となる。
最短経路の部分経路が最短経路でなければ置き換えて短縮可能になってしまうので,
最短経路の部分経路は経由頂点数が自身未満の最短経路である。
それを利用して経由頂点数が0..V-1までの最短経路で動的計画法をするイメージのようだ。
負の閉路から到達可能かの判定については,
最初の1回で-INFとなる始点を求めて, 残りV-1回で影響を拾う。
"""
def bellman_ford(start):
    dist = [INF]*N
    dist[start] = 0
    # 最短経路を求める
    for _ in range(N-1):
        for dist_frm_to, frm, to in E:
            if dist[frm] < INF:
                dist[to] = min(dist[to], dist[frm]+dist_frm_to)
    # 負の閉路(の始点となる-INF)発見
    for dist_frm_to, frm, to in E:
        if dist[frm] < INF and dist[frm]+dist_frm_to < dist[to]:
            dist[to] = -INF
    # 負の閉路から到達可能か
    for _ in range(N-1):
        for dist_frm_to, frm, to in E:
            if dist[frm] == -INF:
                dist[to] = -INF
    return dist

# ワーシャル-フロイド法 O(V^3)
"""
全点対最短経路(すべての頂点からすべての頂点までの最短経路)を求める。
基本的に隣接行列でやるものとしている。
経由する頂点の候補を増やしていくDP。
これは回り道がどうとか考えないので, 負のコストが存在しても動作する。
"""
def warshall_floyd():
    for via in range(N):
        for frm in range(N):
            for to in range(N):
                G[frm][to] = min(G[frm][to], G[frm][via]+G[via][to])

# クラスカル法 O(ElogE)
"""
MST(最小全域木)のコスト総和を求める。
辺をコストの昇順にソートして貪欲にとる。
MSTは最小コストで全頂点を連結にすればよいので, すでに連結ならとらない。
"""
def kruskal():
    UF = DSU(N)
    ret = 0
    for c, u, v in sorted(E):
        if not UF.same(u, v):
            UF.merge(u, v)
            ret += c
    # MSTが作れない場合
    if UF.size(0) < N:
        ret = INF
    return ret

# プリム法 O(ElogE)
"""
MST(最小全域木)のコスト総和を求める。
ダイクストラ法のように訪問済みの頂点に隣接する辺のうち,
コストが最小の辺からとって, その先の頂点を訪問済みにしていく。
どの頂点からスタートしてもok.
"""
def prim():
    ret = 0
    visited = {0}
    pq = []
    for c, to in G[0]:
        heappush(pq, (c, to))
    while len(visited) < N and len(pq) > 0:
        c, frm = heappop(pq)
        if frm in visited:
            continue
        ret += c
        visited.add(frm)
        for c, to in G[frm]:
            if to in visited:
                continue
            heappush(pq, (c, to))
    # MSTが作れない場合
    if len(visited) < N:
        ret = INF
    return ret



INF = 1<<60
ABC = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
abc = "abcdefghijklmnopqrstuvwxyz"
MOD = 998244353



# 固有部分
N = int(input())

# 呼ぶ友人の数(ビット数)
for M in range(1, 9):
    if 1<<M >= N:
        break
print(M)

# ジュースの与え方
A = [[] for _ in range(M)]
for i in range(N):
    for j in range(M):
        m = 1<<j
        if i&m > 0:
            A[j].append(i+1)
for i in range(M):
    K = len(A[i])
    print(K, *A[i])

# 結果ビットの解釈
S = input()
X = int(S, 2)+1
print(X)