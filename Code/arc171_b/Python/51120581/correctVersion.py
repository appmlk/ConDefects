def main():
    # write code here.
    N = II()
    A = LM()

    #最終的に i になる数字たち group[i]
    group = [[] for _ in range(N+1)]
    for i in range(N):
        if A[i] < i+1 or group[i+1] and A[i] != i+1:
            print(0)
            exit()
        group[A[i]].append(i+1)

    bigs = []
    smalls = []
    for i in range(1,N+1):
        if group[i]:
            bigs.append(i)
            smalls.append(min(group[i]))

    smalls.sort()
    bigs.sort()
    L = len(bigs)

    assert len(smalls)==len(bigs)==len(set(smalls))==len(set(bigs))

    ans = 1
    for i in range(L):
        assert BSR(smalls, bigs[i]) - i > 0
        ans *= (BSR(smalls, bigs[i]) - i) % MOD
        ans %= MOD
    print(ans%MOD)




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


def sigma_LinearFunc(bound_included1, bound_included2, coeff1, coeff0):
    """
    coeff1*x + coeff0
    の x = [left, right] の和を求める.
    """
    left = min(bound_included1, bound_included2)
    right = max(bound_included1, bound_included2)
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
        num, cnt = self.gt((x,0))
        if cnt == 1:
            self.discard((x,cnt))
        else:
            self.discard((x,cnt))
            self.add((x,cnt-1))



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




class UnionFind:
    """
    二次元の時は、初期化時に (H,W) のように二次元配列の高さと幅を入力.
    引数一個の root とか size は (r,c) みたいに.
    引数二個の unite とか same は ((ra,ca),(rb,cb)) みたいに引数入れる.
    """

    def __init__(self,*N):
        if not isinstance(N[0],int):
            N = N[0]
        if len(N)==1:
            N=N[0]
        elif len(N)==2:
            self.H, self.W = N[0], N[1]
            N = self.H * self.W

        self.par = [ i for i in range(N) ]
        self.tree_size = [ 1 for i in range(N) ]

    def root(self,*x):
        x = self._dimCheck1(x)
        if self.par[x] == x:
            return x
        self.par[x] = self.root(self.par[x])
        return self.par[x]

    def unite(self,*xy):
        x,y = self._dimCheck2(xy)
        rx = self.root(x)
        ry = self.root(y)

        if rx == ry:
            return

        self.par[rx] = ry 
        self.tree_size[ry] += self.tree_size[rx] 

    def same(self,*xy):
        x,y = self._dimCheck2(xy)
        rx = self.root(x)
        ry = self.root(y)
        return rx == ry 

    def size(self,*x):
        x = self._dimCheck1(x)
        rx = self.root(x)
        return self.tree_size[rx]

    def _dimCheck1(self,x):
        if len(x)==1: return x[0]
        if len(x)==2: return x[0]*self.W + x[1]

    def _dimCheck2(self,xy):
        if isinstance(xy[0],int): return xy[0],xy[1]
        return xy[0][0]*self.W + xy[0][1], xy[1][0]*self.W + xy[1][1]



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