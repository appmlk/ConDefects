def main():
    # write code here.
    N = II()
    LR = LL(N)


    """
    座圧すべき値は l+1,r,r+1,10**9
    """
    CAP = 10**9+1
    inv = [CAP]
    for l,r in LR:
        inv+=[l+1,r,r+1]
    inv = sorted(set(inv))
    D = {e:i for i,e in enumerate(inv)}
    

    # x軸で処理内容をソート.
    event = []
    for l,r in LR:
        event.append((0,+1,l+1,r))
        event.append((l,-1,l+1,r))
        event.append((l+1,+1,r+1,CAP))
        event.append((r,-1,r+1,CAP))
    event.sort(key=lambda e:e[0])



    from atcoder.lazysegtree import LazySegTree
    INF = 1<<60
    # data:(最大値, -y(座圧後の値))
    op = max
    e = (0,-INF)
    def mapp(f, d):
        # 区間加算.
        return (d[0]+f, d[1])
    def comp(f, l):
        return f+l
    ID = 0
    # 初期値は、最大値0 argminは自身. 座圧後の空間を確保.
    seg = LazySegTree(op, e, mapp, comp, ID, [(0,-i)for i in range(len(D)+5)])


    LEN = len(event)

    ans = (0, 1)
    crr_max = 0



    for i in range(LEN):
        x,f,L,R = event[i]

        # 区間加算を行う.
        seg.apply(D[L],D[R],f)

        # 次のxも同じなら.
        if i+1<LEN and x==event[i+1][0]:
            continue

        res = seg.all_prod()

        # 最大値が更新されるなら. tieは無視で良い.
        if res[0] > crr_max:
            crr_max = res[0]
            
            if -res[1] >= len(inv):
                continue

            y = inv[-res[1]]
            ans = (x, y)

    print(*ans)










# user config
############
DEBUG_MODE=1
############


# import
import sys
import itertools
import bisect
import math
from collections import *
from pprint import pprint
from functools import cache
import heapq


# alias
DD = defaultdict
BSL = bisect.bisect_left
BSR = bisect.bisect_right


# config
input = sys.stdin.readline
sys.setrecursionlimit(10**7)


# input template
def II(): return int(input())
def IS(): return input()[:-1]
def MI(): return map(int,input().split())
def LM(): return list(MI())
def LL(n): return [LM() for _ in range(n)]
def INPUT_TABLE_LIST(n,remove_br=True): return [list(input())[:-1] if remove_br else list(input()) for _ in range(n)]
def INPUT_TABLE_STRING(n):  return [IS() for _ in range(n)]
def ALPHABET_TO_NUM(string, upper=False): return list(map(lambda elm:ord(elm)-ord("A") if upper else ord(elm)-ord("a"), string))


def MI_1(): return map(lambda x:int(x)-1,input().split())
def LM_1(): return list(MI_1())
def LL_1(n): return [LM_1() for _ in range(n)]


# functions
def DB(*args,**kwargs):
    global DEBUG_MODE
    if not DEBUG_MODE:
        return
    if args:
        print(*args)
        return
    for name, value in kwargs.items():
        print(f"{name} : {value}")


def bit_count(num):
    length = num.bit_length()
    res = 0
    for i in range(length):
        if num >> i & 1:
            res += 1
    return res


def popcount64(n):
    # 63桁まで高速に動く.64桁まで正常に動く.
    c=(n&0x5555555555555555)+((n>>1)&0x5555555555555555)
    c=(c&0x3333333333333333)+((c>>2)&0x3333333333333333)
    c=(c&0x0f0f0f0f0f0f0f0f)+((c>>4)&0x0f0f0f0f0f0f0f0f)
    c=(c&0x00ff00ff00ff00ff)+((c>>8)&0x00ff00ff00ff00ff)
    c=(c&0x0000ffff0000ffff)+((c>>16)&0x0000ffff0000ffff)
    c=(c&0x00000000ffffffff)+((c>>32)&0x00000000ffffffff)
    return c


def argmax(*args):
    if len(args) == 1 and hasattr(args[0], '__iter__'):
        lst = args[0]
    else:
        lst = args
    return lst.index(max(lst))


def argmin(*args):
    if len(args) == 1 and hasattr(args[0], '__iter__'):
        lst = args[0]
    else:
        lst = args
    return lst.index(min(lst))


def prefix_op(lst, op=lambda x,y:(x+y)%998244353, e=0):
    N = len(lst)
    res = [e]*(N+1)
    for i in range(N):
        res[i+1] = op(res[i], lst[i])
    return res


def suffix_op(lst, op=lambda x,y:(x+y)%998244353, e=0):
    N = len(lst)
    res = [e]*(N+1)
    for i in range(N):
        res[N-1-i] = op(lst[N-1-i], res[N-i])
    return res


def sigma_LinearFunc(coeff1, coeff0, left, right, MOD=None):
    """
    coeff1*x + coeff0
    の x = [left, right] の和を求める.
    MODで計算したい場合、区間の引数をMOD取った上で代入しても良い.
    そのとき、left > right となってもよい. 
    """
    if MOD:
        # MODが素数でない場合にも対応するように、和公式を適応後に剰余を計算.
        return ((coeff0%MOD*((right-left+1)%MOD)%MOD) + (coeff1%MOD*((left+right)*(right-left+1)//2%MOD)%MOD))%MOD
    return coeff0*(right-left+1) + coeff1*(left+right)*(right-left+1)//2


def find_divisors(n):
    divisors_small = []
    divisors_big = []
    i = 1
    while i * i <= n:
        if n % i == 0:
            divisors_small.append(i)
            # iと一致しない場合、n/iも約数
            if i != n // i:
                divisors_big.append(n // i)
        i += 1
    return divisors_small + divisors_big[::-1]


def prime_factorization(n):
    n_intact = n
    ret = []
    i = 2
    while i * i <= n_intact:
        if n % i == 0:
            cnt = 0
            while n % i == 0:
                n //= i
                cnt += 1
            ret.append((i,cnt))
        i += 1
    if n != 1: ret.append((n,1))
    return ret



""" 矩形の二次元配列を扱う諸関数 """
def copy_table(table):
    H,W = len(table), len(table[0])
    res = []
    for i in range(H):
        res.append([])
        for j in range(W):
            res[-1].append(table[i][j])
    return res

def sum_table(table, MOD=None):
    H,W = len(table), len(table[0])
    res = 0
    for i in range(H):
        for j in range(W):
            res += table[i][j]
        if MOD:
            res %= MOD
    return res


def expand_table(table, h_mag, w_mag):
    #引数の二次元配列などをタイルのように繰り替えしたものを返す.
    res = []
    for row in table:
        res.append(row*w_mag)
    return res*h_mag


def convert_table_to_bit(table, letter1="#", rev=False):
    H,W = len(table), len(table[0])
    res = []
    for h in range(H):
        rowBit = 0
        for w in range(W):
            if rev:
                if table[h][w] == letter1:
                    rowBit += 2**w
            else:
                if table[h][W-w-1] == letter1:
                    rowBit += 2**w
        res.append(rowBit)
    return res

def rotate_table(S):return list(zip(*S))[::-1]


def topological_sort(G, indegree=None):

    N = len(G)
    if indegree is None:
        indegree = [0]*N
        for v in range(N):
            for adj in G[v]:
                indegree[adj] += 1

    deq = deque()
    for v in range(N):
        if indegree[v] == 0:
            deq.append(v)

    res = []
    while deq:
        v = deq.popleft()
        res.append(v)
        for adj in G[v]:
            indegree[adj] -= 1
            if indegree[adj] == 0:
                deq.append(adj)

    return res


def mul_matrix(A, B, mod):
    N = len(A)
    K = len(A[0])
    M = len(B[0])

    res = [[0 for _ in range(M)] for _ in range(N)]

    for i in range(N) :
        for j in range(K) :
            for k in range(M) :
                res[i][k] += A[i][j] * B[j][k] 
                res[i][k] %= mod
    return res


def pow_matrix(mat, exp, mod):
    N = len(mat)
    res = [[1 if i == j else 0 for i in range(N)] for j in range(N)]
    while exp > 0 :
        if exp%2 == 1 :
            res = mul_matrix(res, mat, mod)
        mat = mul_matrix(mat, mat, mod)
        exp //= 2
    return res


def compress(lst):
    D = {e:i for i,e in enumerate(sorted(set(lst)))}
    return [D[e] for e in lst]

def highDimCompress(lst):
    #(x,y)の配列や,(x,y,z)の配列が与えられたとき,軸ごとに座圧する.
    return list(zip(*list(map(compress,list(zip(*lst))))))





#classes


# https://github.com/tatyam-prime/SortedSet/blob/main/SortedSet.py
from bisect import bisect_left, bisect_right
from typing import Generic, Iterable, Iterator, List, Tuple, TypeVar, Optional
T = TypeVar('T')
class SortedSet(Generic[T]):
    BUCKET_RATIO = 16
    SPLIT_RATIO = 24
    
    def __init__(self, a: Iterable[T] = []) -> None:
        "Make a new SortedSet from iterable. / O(N) if sorted and unique / O(N log N)"
        a = list(a)
        n = self.size = len(a)
        if any(a[i] > a[i + 1] for i in range(n - 1)):
            a.sort()
        if any(a[i] >= a[i + 1] for i in range(n - 1)):
            a, b = [], a
            for x in b:
                if not a or a[-1] != x:
                    a.append(x)
        bucket_size = int(math.ceil(math.sqrt(n / self.BUCKET_RATIO)))
        self.a = [a[n * i // bucket_size : n * (i + 1) // bucket_size] for i in range(bucket_size)]

    def __iter__(self) -> Iterator[T]:
        for i in self.a:
            for j in i: yield j

    def __reversed__(self) -> Iterator[T]:
        for i in reversed(self.a):
            for j in reversed(i): yield j
    
    def __eq__(self, other) -> bool:
        return list(self) == list(other)
    
    def __len__(self) -> int:
        return self.size
    
    def __repr__(self) -> str:
        return "SortedSet" + str(self.a)
    
    def __str__(self) -> str:
        s = str(list(self))
        return "{" + s[1 : len(s) - 1] + "}"

    def _position(self, x: T) -> Tuple[List[T], int, int]:
        "return the bucket, index of the bucket and position in which x should be. self must not be empty."
        for i, a in enumerate(self.a):
            if x <= a[-1]: break
        return (a, i, bisect_left(a, x))

    def __contains__(self, x: T) -> bool:
        if self.size == 0: return False
        a, _, i = self._position(x)
        return i != len(a) and a[i] == x

    def add(self, x: T) -> bool:
        "Add an element and return True if added. / O(√N)"
        if self.size == 0:
            self.a = [[x]]
            self.size = 1
            return True
        a, b, i = self._position(x)
        if i != len(a) and a[i] == x: return False
        a.insert(i, x)
        self.size += 1
        if len(a) > len(self.a) * self.SPLIT_RATIO:
            mid = len(a) >> 1
            self.a[b:b+1] = [a[:mid], a[mid:]]
        return True
    
    def _pop(self, a: List[T], b: int, i: int) -> T:
        ans = a.pop(i)
        self.size -= 1
        if not a: del self.a[b]
        return ans

    def discard(self, x: T) -> bool:
        "Remove an element and return True if removed. / O(√N)"
        if self.size == 0: return False
        a, b, i = self._position(x)
        if i == len(a) or a[i] != x: return False
        self._pop(a, b, i)
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
    
    def __getitem__(self, i: int) -> T:
        "Return the i-th element."
        if i < 0:
            for a in reversed(self.a):
                i += len(a)
                if i >= 0: return a[i]
        else:
            for a in self.a:
                if i < len(a): return a[i]
                i -= len(a)
        raise IndexError
    
    def pop(self, i: int = -1) -> T:
        "Pop and return the i-th element."
        if i < 0:
            for b, a in enumerate(reversed(self.a)):
                i += len(a)
                if i >= 0: return self._pop(a, ~b, i)
        else:
            for b, a in enumerate(self.a):
                if i < len(a): return self._pop(a, b, i)
                i -= len(a)
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


    """
    (num, cnt)を要素としたSSを管理してMultiset化したいとき用.
    """
    def exist(self, x):
        ret = self.gt((x,0))
        if ret is None:
            return False
        elif ret[0] == x:
            return True
        else:
            return False

    def increment(self, x):
        if not self.exist(x):
            self.add((x,1))
        else:
            num, cnt = self.gt((x,0))
            self.discard((x,cnt))
            self.add((x,cnt+1))


    def decrement(self, x):
        if not self.exist(x):
            return
        num, cnt = self.gt((x,0))
        if cnt == 1:
            self.discard((x,cnt))
        else:
            self.discard((x,cnt))
            self.add((x,cnt-1))

    def multi_add(self, x, y):
        if not self.exist(x):
            self.add((x,y))
        else:
            num, cnt = self.gt((x,0))
            self.discard((x,cnt))
            self.add((x,cnt+y))

    def multi_sub(self, x, y):
        if not self.exist(x):
            return
        num, cnt = self.gt((x,0))
        if cnt <= y:
            self.discard((x,cnt))
        else:
            self.discard((x,cnt))
            self.add((x,cnt-y))



# https://github.com/tatyam-prime/SortedSet/blob/main/SortedMultiset.py
T = TypeVar('T')
class SortedMultiset(Generic[T]):
    BUCKET_RATIO = 16
    SPLIT_RATIO = 24
    
    def __init__(self, a: Iterable[T] = []) -> None:
        "Make a new SortedMultiset from iterable. / O(N) if sorted / O(N log N)"
        a = list(a)
        n = self.size = len(a)
        if any(a[i] > a[i + 1] for i in range(n - 1)):
            a.sort()
        num_bucket = int(math.ceil(math.sqrt(n / self.BUCKET_RATIO)))
        self.a = [a[n * i // num_bucket : n * (i + 1) // num_bucket] for i in range(num_bucket)]

    def __iter__(self) -> Iterator[T]:
        for i in self.a:
            for j in i: yield j

    def __reversed__(self) -> Iterator[T]:
        for i in reversed(self.a):
            for j in reversed(i): yield j
    
    def __eq__(self, other) -> bool:
        return list(self) == list(other)
    
    def __len__(self) -> int:
        return self.size
    
    def __repr__(self) -> str:
        return "SortedMultiset" + str(self.a)
    
    def __str__(self) -> str:
        s = str(list(self))
        return "{" + s[1 : len(s) - 1] + "}"

    def _position(self, x: T) -> Tuple[List[T], int, int]:
        "return the bucket, index of the bucket and position in which x should be. self must not be empty."
        for i, a in enumerate(self.a):
            if x <= a[-1]: break
        return (a, i, bisect_left(a, x))

    def __contains__(self, x: T) -> bool:
        if self.size == 0: return False
        a, _, i = self._position(x)
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
        a, b, i = self._position(x)
        a.insert(i, x)
        self.size += 1
        if len(a) > len(self.a) * self.SPLIT_RATIO:
            mid = len(a) >> 1
            self.a[b:b+1] = [a[:mid], a[mid:]]
    
    def _pop(self, a: List[T], b: int, i: int) -> T:
        ans = a.pop(i)
        self.size -= 1
        if not a: del self.a[b]
        return ans

    def discard(self, x: T) -> bool:
        "Remove an element and return True if removed. / O(√N)"
        if self.size == 0: return False
        a, b, i = self._position(x)
        if i == len(a) or a[i] != x: return False
        self._pop(a, b, i)
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
    
    def __getitem__(self, i: int) -> T:
        "Return the i-th element."
        if i < 0:
            for a in reversed(self.a):
                i += len(a)
                if i >= 0: return a[i]
        else:
            for a in self.a:
                if i < len(a): return a[i]
                i -= len(a)
        raise IndexError
    
    def pop(self, i: int = -1) -> T:
        "Pop and return the i-th element."
        if i < 0:
            for b, a in enumerate(reversed(self.a)):
                i += len(a)
                if i >= 0: return self._pop(a, ~b, i)
        else:
            for b, a in enumerate(self.a):
                if i < len(a): return self._pop(a, b, i)
                i -= len(a)
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



class Comb:
    def __init__(self,table_len,MOD):
        """
        MODが素数の場合しか使用できない.
        table_len に指定した数まで法MODでのコンビネーションの計算が可能になる.
        """
        self.fac = [1,1]
        self.inv = [1,1]
        self.finv = [1,1]
        self.MOD = MOD
        for i in range(2,table_len+1):
            self.fac.append(self.fac[i-1]*i%MOD)
            self.inv.append(-self.inv[MOD%i]*(MOD//i)%MOD)
            self.finv.append(self.finv[i-1]*self.inv[i]%MOD)

    def comb(self,a,b):
        return self.fac[a]*self.finv[b]*self.finv[a-b]%MOD


class RollingHash:

    def __init__(self, string, base, mod):

        self.mod = mod

        l = len(string)
        self.hash = [0]*(l+1)

        for i in range(1,l+1):
            self.hash[i] = ( self.hash[i-1] * base + ord(string[i-1]) ) % mod

        self.pw = [1]*(l+1)
        for i in range(1,l+1):
            self.pw[i] = self.pw[i-1] * base % mod


    def get(self, l, r):
        return (self.hash[r] - self.hash[l] * self.pw[r-l]) % self.mod



class GridBFS:

    def __init__(self, table):
        #二次元配列や文字列の配列を受け取る.
        self.table = table
        self.H = len(table)
        self.W = len(table[0])
        self.wall = "#"

    def find(self, c):
        #table から引数の文字を探しインデックスを返す. 無い時、None.
        for h in range(self.H):
            for w in range(self.W):
                if self.table[h][w] == c:
                    return (h,w)
        return None

    def set_wall_string(self, string): 
        #壁として扱う文字を "#!^" の様に文字列リテラルで格納. 初期値は、"#"
        self.wall = string

    def island(self, transition = [[-1,0],[0,1],[1,0],[0,-1]]):
        H, W = self.H, self.W
        self.island_id = [[-1]*W for _ in range(H)]
        self.island_size = [[-1]*W for _ in range(W)]

        crr_id = 0
        id2size = dict()
        for sh in range(H):
            for sw in range(W):
                if self.table[sh][sw] in self.wall:
                    continue
                if self.island_id[sh][sw] != -1:
                    continue
                deq = deque()
                deq.append((sh,sw))
                crr_size = 1
                self.island_id[sh][sw] = crr_id
                while deq:
                    h,w = deq.popleft()
                    for dh, dw in transition:
                        nh, nw = h+dh, w+dw
                        if (not 0<=nh<H) or (not 0<=nw<W):
                            continue
                        if self.table[nh][nw] in self.wall:
                            continue
                        if self.island_id[nh][nw] == -1:
                            self.island_id[nh][nw] = crr_id
                            deq.append((nh, nw))
                            crr_size += 1

                id2size[crr_id] = crr_size
                crr_id += 1

        for h in range(H):
            for w in range(W):
                if self.table[h][w] in self.wall:
                    continue
                self.island_size[h][w] = id2size[self.island_id[h][w]]

        return self.island_id, self.island_size


    def distance(self, start, goal=None, transition = [[-1,0],[0,1],[1,0],[0,-1]]):
        #goal指定したら、goalまでの最短距離を、指定しなければdist配列を返す. 到達不可能は -1.
        #二次元配列上での遷移方法を transition で指定できる. 初期値は上下左右.
        H, W, tab, wall = self.H, self.W, self.table, self.wall

        INF = 1<<60

        deq = deque()
        deq.append(start)
        dist = [[INF]*W for _ in range(H)]
        dist[start[0]][start[1]] = 0

        while deq:
            h,w = deq.popleft()
            for dh, dw in transition:
                nh = h+dh
                nw = w+dw
                # gridの範囲外.
                if (not 0<=nh<H) or (not 0<=nw<W):
                    continue

                # wallに設定されている文字なら.
                if tab[nh][nw] in wall:
                    continue

                new_dist = dist[h][w] + 1

                #goalが引数で与えられていてgoalに達したら終了.
                if goal and (nh,nw)==goal:
                    return new_dist
                
                if dist[nh][nw] > new_dist:
                    dist[nh][nw] = new_dist
                    deq.append((nh,nw))

        # goalが設定されていていまだreturnされていないなら,
        # goalに到達できなかったということ.
        if goal:
            return -1

        return dist



class DisjointSparseTable:
    def __init__(self, op, v):
        """
        静的な半群列の区間積を<O(NlogN),O(1)>で.
        結合則満たして閉じていれば良い.
        """
        self._op = op
        self._n = len(v)
        self._log = (self._n - 1).bit_length()

        self._d = [[0]*self._n for _ in range(self._log)]
        for j in range(self._n):
            self._d[0][j] = v[j]

        for i in range(self._log):
            width = 1 << i+1
            half_width = 1 << i

            k = 0
            while k*width + half_width < self._n:
                piv = k*width + half_width
                self._d[i][piv-1] = v[piv-1]
                for j in range(1, half_width):
                    self._d[i][piv-1-j] = self._op(v[piv-1-j], self._d[i][piv-j])

                self._d[i][piv] = v[piv]
                for j in range(1, min(half_width, self._n-piv)):
                    self._d[i][piv+j] = self._op(v[piv+j], self._d[i][piv+j-1])

                k += 1


    def prod(self, left, right):
        """
        入力は開区間.
        left == right の時の挙動未定義(単位元が無いため)
        必要なら適宜追記.
        """
        if left == right:
            ...

        right -= 1
        assert 0 <= left <= right < self._n

        if left == right:
            return self._d[0][left]

        layer = (left ^ right).bit_length() - 1
        return self._op(self._d[layer][left], self._d[layer][right])



class RootedTree:

    @classmethod
    def autobuild(cls, N, input_index=1):
        """ 
        頂点数を受け取り、次のN-1行を読み込み、インスタンスを返す. 
        対応の型 : (u,v) , (u,v,c)
        """
        G = [[] for _ in range(N)]
        if N==1: return RootedTree(G)

        line1 = list(map(int, input().split()))
        assert 2<=len(line1)<=3

        # 重み無し.
        if len(line1)==2:
            u,v = line1
            u,v = u-input_index, v-input_index
            G[u].append(v)
            G[v].append(u)
            for _ in range(N-2):
                u,v = map(int, input().split())
                u,v = u-input_index, v-input_index
                G[u].append(v)
                G[v].append(u)
            return RootedTree(G)

        else:
            u,v,c = line1
            u,v = u-input_index, v-input_index
            G[u].append(v)
            G[v].append(u)
            edge = [(u,v,c)]
            for _ in range(N-2):
                u,v,c = map(int, input().split())
                u,v = u-input_index, v-input_index
                G[u].append(v)
                G[v].append(u)
                edge.append((u,v,c))

            obj = RootedTree(G)
            obj.set_weight(edge)
            return obj


    def __init__(self, G):
        self._N = len(G)
        self.G = G
        self._rooted = False
        self._has_weight = False
        self._key = 10**7

    def set_root(self, root):
        """ BFSついでにトポロジカルソート列も求める """
        assert self._rooted == False
        self.root = root
        n, G = self._N, self.G
        par, ch, ts = [-1]*n, [[] for _ in range(n)], []
        deq = deque([root])
        while deq:
            v = deq.popleft()
            ts.append(v)
            for adj in G[v]:
                if adj == par[v]: continue
                par[adj] = v
                ch[v].append(adj)
                deq.append(adj)
        self.parent, self.children, self.ts_order = par, ch, ts
        self._rooted = True

    def _encode(self, u, v):
        return u*self._key + v

    def _decode(self, uv):
        return divmod(uv, self._key)

    def is_root(self, v):
        return v == self.root

    def is_leaf(self, v):
        return len(self.children[v]) == 0


    """ weight """
    def set_weight(self, edge):
        assert self._has_weight == False
        d = {}
        for u,v,c in edge:
            d[self._encode(u,v)] = d[self._encode(v,u)] = c
        self.weight = d
        self._has_weight = True

    def get_weight(self, u, v):
        return self.weight[self._encode(u, v)]
    

    """ depth"""
    def get_depth(self, v):
        # obj.depth[v] と同じ.
        if not hasattr(self, "depth"):
            self.build_depth()    
        return self.depth[v]

    def build_depth(self):
        assert self._rooted
        N, ch, ts = self._N, self.children, self.ts_order
        depth = [0]*N
        for v in ts:
            for c in ch[v]:
                depth[c] = depth[v] + 1
        self.depth = depth


    """ subtree_size """
    def build_des_size(self):
        assert self._rooted
        if hasattr(self, "des_size"):
            return
        N, ts, par = self._N, self.ts_order, self.parent
        des = [1]*N
        for i in range(N-1,0,-1):
            v = ts[i]
            p = par[v]
            des[p] += des[v]
        self.des_size = des


    """ centroid """
    def centroid_decomposition(self):
        if hasattr(self, "centroid_id"):
            return

        # 根に依存しないアルゴリズムなので根0にしていい.
        if not self._rooted:
            self.set_root(0)

        if not hasattr(self, "des_size"):
            self.build_des_size()

        # sizeは書き換えるのでコピーを使用.
        N, G, size = self._N, self.G, self.des_size[:]
        c_id, c_depth, c_par, c_dfs_order = [-1]*N, [-1]*N, [-1]*N, []

        stack = [(self.root, -1, 0)]
        # 重心を見つけたら,「重心分解後のその頂点が重心となる部分木」の
        # DFS順の順番, 深さ, 重心木における親にあたる部分木の重心を記録
        for order in range(N):
            v, prev, d = stack.pop()
            while True:
                for adj in G[v]:
                    if c_id[adj] == -1 and size[adj]*2 > size[v]:
                        # adjを今見ている部分木の根にし,sizeを書き換える.
                        size[v], size[adj], v = size[v]-size[adj], size[v], adj
                        break
                else:
                    break

            c_id[v], c_depth[v], c_par[v] = order, d, prev
            c_dfs_order.append(v)

            if size[v] > 1:
                for adj in G[v]:
                    if c_id[adj] == -1:
                        stack.append((adj, v, d+1))

        self.centroid_id, self.centroid_depth, self.centroid_parent, self.centroid_dfs_order = c_id, c_depth, c_par, c_dfs_order

    def is_member_of_centroid_tree(self, v, c):
        # 頂点vが重心cの重心木に属するかを判定 O(logN)
        vs = self.get_higher_centroids_with_self(v)
        return c in vs

    def is_id_larger(self, u, v):
        # 重心cからBFSする時に、is_id_larger(adj, c)とすれば重心木内部を探索できる.
        return self.centroid_id[u] > self.centroid_id[v]

    def get_higher_centroids_with_self(self, c):
        # 頂点cが属する重心木の重心をサイズの昇順に列挙. O(logN)
        vs = []
        for d in range(self.centroid_depth[c], -1, -1):
            vs.append(c)
            c = self.centroid_parent[c]
        return vs

    def find_lowest_common_centroid(self, u, v):
        # 頂点u,vをどちらも含む最小の重心木を返す. O(logN)
        c_depth, c_par = self.centroid_depth, self.centroid_parent
        du, dv = c_depth[u], c_depth[v]
        if du > dv:
            u,v = v,u
            du,dv = dv,du
        for _ in range(dv - du):
            v = c_par[v]
        while u != v:
            u,v = c_par[u],c_par[v]
        return u


    """ tree dp """
    def dp_from_leaf(self, merge, e, add_root, push=lambda obj,data,dst,src:data):
        assert self._rooted

        N, ts, par = self._N, self.ts_order, self.parent
        sub = [e] * N
        for i in range(N-1,-1,-1):
            v = ts[i]
            sub[v] = add_root(self, sub[v], v)
            p = par[v]
            if p != -1:
                sub[p] = merge(sub[p], push(self, sub[v], p, v))
        return sub

    def rerooting_dp(self, merge, e, add_root, push=lambda obj,data,dst,src:data):        
        if self._rooted == False:
            self.set_root(0)

        sub = self.dp_from_leaf(merge, e, add_root, push)

        N = self._N
        ts, par, ch = self.ts_order, self.parent, self.children
        
        compl, dp = [e]*N, [e]*N

        for i in range(N):
            v = ts[i]
            p, size = par[v], len(ch[v])
            left, right = [e]*size, [e]*size
            for j in range(size):
                c = ch[v][j]
                left[j] = merge(left[j-1] if j>0 else e, push(self, sub[c], v, c))
            for j in range(size-1,-1,-1):
                c = ch[v][j]
                right[j] = merge(right[j+1] if j<size-1 else e, push(self, sub[c], v, c))

            for j in range(size):
                c = ch[v][j]
                compl[c] = merge(compl[c], left[j-1] if j>0 else e)
                compl[c] = merge(compl[c], right[j+1] if j<size-1 else e)
                if p != -1:
                    compl[c] = merge(compl[c], push(self, compl[v], v, p))
                compl[c] = add_root(self, compl[c], v)

            if p != -1:
                dp[v] = merge(dp[v], push(self, compl[v], v, p))
            dp[v] = merge(dp[v], left[-1] if size else e)
            dp[v] = add_root(self, dp[v], v)

        return dp




global DIRECTION_4, DIRECTION_8, DIRECTION_DIAGONAL, DIRECTION_URDL_TABLE, DIRECTION_URDL_COORD_PLANE, MOD, INF, LOWER_ALPS, UPPER_ALPS, ALL_ALPS
# well-used const
# clockwise from top.
DIRECTION_4 = [[-1,0],[0,1],[1,0],[0,-1]] 
DIRECTION_8 = [[-1,0],[-1,1],[0,1],[1,1],[1,0],[1,-1],[0,-1],[-1,-1]]
DIRECTION_DIAGONAL = [[-1,1],[1,1],[1,-1],[-1,-1]]
DIRECTION_URDL_TABLE = {'U':(-1,0), 'R':(0,1), 'D':(1,0), 'L':(0,-1)}
DIRECTION_URDL_COORD_PLANE = {'U':(0,1), 'R':(1,0), 'D':(0,-1), 'L':(-1,0)}
MOD = 998244353
INF = float("inf")
LOWER_ALPS = "abcdefghijklmnopqrstuvwxyz"
UPPER_ALPS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
ALL_ALPS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"




# random_checker.
def random_checker():
    """
    必要な準備 : 
    1. main関数の入力をコメントアウトして引数で受け取る.
    2. main関数の出力をreturnに変更.
    """
    import random

    T = 1000

    for testcase in range(T):
        #--------------------------------------#
        N = random.randrange(3,6)
        A = []
        B = []
        for i in range(N):
            A.append(random.randrange(1,10**2+1))
            B.append(random.randrange(1,10**2+1))
        args = (N,A,B)
        #--------------------------------------#

        res_main = main(*args)
        res_naive = naive(*args)
        if res_main != res_naive:
            print(f"{testcase}回目の実行\n")
            print("入力値:")
            print(*args, sep="\n")
            print("main():")
            print(res_main)
            print("naive():")
            print(res_naive)
            return

    print(f"{T}回の実行で有効なテストケースを発見できませんでした.")


main()