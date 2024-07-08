def main():
    # write code here.
    N,H = MI()
    time2coord = LM()
    coord2idx = DD(int)
    idx2coord = [-1]*N
    for idx,coord in enumerate(sorted(time2coord)):
        coord2idx[coord] = idx
        idx2coord[idx] = coord
    time2idx = [coord2idx[time2coord[idx]] for idx in range(N)]

    
    idx2island_ID = [0]*N
    crr_id = 0
    prev = idx2coord[0]
    for idx in range(1,N):
        crr_coord = idx2coord[idx]
        if crr_coord - prev > H:
            crr_id += 1
        idx2island_ID[idx] = crr_id
        prev = crr_coord




    island_size = idx2island_ID[-1]+1

    from atcoder.segtree import SegTree
    def prob_mul(x,y):
        return (x[0]*y[0]%MOD, x[1]+y[1])
    def prob_add(x,y):
        if x[1]<y[1]:
            x,y=y,x
        assert x[1]>=y[1]
        numerator = x[0]+y[0]*pow(2,x[1]-y[1],MOD)
        numerator %= MOD
        return (numerator, x[1])


    e0 = (1,0)
    island_fin_prob = SegTree(prob_mul, e0, [(0,0)]*island_size)


    ans = [0]*N
    right_req = [0]*N
    left_req = [0]*N
    ss_of_idx = SortedSet()
    for time in range(N):
        now = 0
        idx = time2idx[time]
        island_id = idx2island_ID[idx]
        ss_of_idx.add(idx)

        adj_right = ss_of_idx.gt(idx)
        if adj_right is not None and island_id == idx2island_ID[adj_right]:
            right_req[idx] = right_req[adj_right] + 1

        adj_left = ss_of_idx.lt(idx)
        if adj_left is not None and island_id == idx2island_ID[adj_left]:
            left_req[idx] = left_req[adj_left] + 1

        DB(time=time)
        DB(idx, adj_right, adj_left)
        DB(right_req[idx], left_req[idx])
        DB(island_id)

        other_fin_prob = prob_mul(island_fin_prob.prod(0,island_id), island_fin_prob.prod(island_id+1,island_size))


        crr_prob = (0,0)
        #右が全部倒れていて左に倒れるパターン.
        if idx==N-1 or island_id != idx2island_ID[idx+1] or adj_right == idx+1:
            crr_prob = prob_add(crr_prob, (1,1+left_req[idx]+right_req[idx]))
            prob = prob_mul((1,1+left_req[idx]+right_req[idx]), other_fin_prob)
            now += prob[0] * pow(2, N-prob[1], MOD)
            now %= MOD

        #左が全部倒れていて右に倒れるパターン.
        if idx==0 or island_id != idx2island_ID[idx-1] or adj_left == idx-1:
            crr_prob = prob_add(crr_prob, (1,1+left_req[idx]+right_req[idx]))
            prob = prob_mul((1,1+left_req[idx]+right_req[idx]), other_fin_prob)
            now += prob[0] * pow(2, N-prob[1], MOD)
            now %= MOD

        ans[time] = now

        prev_prob = island_fin_prob.get(island_id)
        island_fin_prob.set(island_id, prob_add(prev_prob, crr_prob))


    print(*ans)
            





# user config
############
DEBUG_MODE=0
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
def bit_count(num):
    length = num.bit_length()
    res = 0
    for i in range(length):
        if num >> i & 1:
            res += 1
    return res


def DB(*args,**kwargs):
    global DEBUG_MODE
    if not DEBUG_MODE:
        return
    if args:
        print(*args)
        return
    for name, value in kwargs.items():
        print(f"{name} : {value}")


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


def expand_table(table, h_mag, w_mag):
    #引数の二次元配列などをタイルのように繰り替えしたものを返す.
    res = []
    for row in table:
        res.append(row*w_mag)
    return res*h_mag


def safe_sqrt(N):
    #[平方根]の誤差が怖いとき用.
    rough = int(N**0.5)
    left = rough - 10
    right = rough + 10
    while left != right:
        mid = (left+right+1)//2
        if mid**2 <= N:
            left = mid
        else:
            right = mid - 1
    return left


def sigma_LinearFunc(coeff1, coeff0, bound_included1, bound_included2, MOD=None):
    """
    coeff1*x + coeff0
    の x = [left, right] の和を求める.
    """
    left = min(bound_included1, bound_included2)
    right = max(bound_included1, bound_included2)
    if MOD:
        # MODが素数でない場合にも対応するように、和公式を適応後に剰余を計算している.
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



def makeTableBit(table, letter1="#", rev=False):
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


def rot(S):return list(zip(*S))[::-1]


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


def popcount64(n):
    # 63桁まで高速に動く.64桁まで正常に動く.
    c=(n&0x5555555555555555)+((n>>1)&0x5555555555555555)
    c=(c&0x3333333333333333)+((c>>2)&0x3333333333333333)
    c=(c&0x0f0f0f0f0f0f0f0f)+((c>>4)&0x0f0f0f0f0f0f0f0f)
    c=(c&0x00ff00ff00ff00ff)+((c>>8)&0x00ff00ff00ff00ff)
    c=(c&0x0000ffff0000ffff)+((c>>16)&0x0000ffff0000ffff)
    c=(c&0x00000000ffffffff)+((c>>32)&0x00000000ffffffff)
    return c





#classes


"""
・使い方
s=SortedSet() : 引数にイテラブル渡せる.
s.a: SortedSetの中身を返す。
len(s), x in s, x not in s: リストと同じ要領で使える。
s.add(x): xを追加してTrueを返す。ただしxがすでにs内にある場合、xは追加せずにFalseを返す。
s.discard(x): xを削除してTrueを返す。ただしxがs内にない場合、何もせずにFalseを返す。
s.lt(x): xより小さい最大の要素を返す。もし存在しないなら、Noneを返す。
s.le(x): x　以下の　最大の要素を返す。もし存在しないなら、Noneを返す。
s.gt(x): xより大きい最小の要素を返す。もし存在しないなら、Noneを返す。
s.ge(x): x　以上の　最小の要素を返す。もし存在しないなら、Noneを返す。
s.index(x): xより小さい要素の数を返す。
s.index_right(x): x以下の要素の数を返す。
"""

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


# wotsushiさん作成 : https://qiita.com/wotsushi/items/c936838df992b706084c
# global MOD を定義.
# ModIntとintの結果はModInt.
# a**b , a/b といった演算も可能.
# 配列などの添え字には利用できない. intに変換はできない.
class ModInt:
    def __init__(self, x):
        self.x = x % MOD

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
            ModInt(self.x * other.x) if isinstance(other, ModInt) else
            ModInt(self.x * other)
        )

    def __truediv__(self, other):
        return (
            ModInt(
                self.x * pow(other.x, MOD - 2, MOD)
            ) if isinstance(other, ModInt) else
            ModInt(self.x * pow(other, MOD - 2, MOD))
        )

    def __pow__(self, other):
        return (
            ModInt(pow(self.x, other.x, MOD)) if isinstance(other, ModInt) else
            ModInt(pow(self.x, other, MOD))
        )

    __radd__ = __add__

    def __rsub__(self, other):
        return (
            ModInt(other.x - self.x) if isinstance(other, ModInt) else
            ModInt(other - self.x)
        )

    __rmul__ = __mul__

    def __rtruediv__(self, other):
        return (
            ModInt(
                other.x * pow(self.x, MOD - 2, MOD)
            ) if isinstance(other, ModInt) else
            ModInt(other * pow(self.x, MOD - 2, MOD))
        )

    def __rpow__(self, other):
        return (
            ModInt(pow(other.x, self.x, MOD)) if isinstance(other, ModInt) else
            ModInt(pow(other, self.x, MOD))
        )




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
        H, W = self.H, self.W
        deq = deque()
        deq.append(start)
        dist = [[-1]*W for _ in range(H)]
        dist[start[0]][start[1]] = 0

        while deq:
            h,w = deq.popleft()
            for dh, dw in transition:
                nh = h+dh
                nw = w+dw
                if (not 0<=nh<H) or (not 0<=nw<W):
                    continue

                if goal and (nh,nw)==goal:
                    return dist[h][w] + 1
                
                if self.table[nh][nw] in self.wall:
                    continue
                
                if dist[nh][nw] == -1:
                    dist[nh][nw] = dist[h][w] + 1
                    deq.append((nh,nw))

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


main()