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
def LS(n,remove_br=False): return [list(input())[:-1] if remove_br else list(input()) for _ in range(n)]

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


def DB(*x):
    global DEBUG_MODE
    if DEBUG_MODE: print(*x)


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
import math
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
    個数付きなSortedSetがほしいとき.
    (num, cnt)を要素としたSSを管理すると良い場合がある.
    その時、numを一個追加、一個消去、存在判定が大変なので,
    メソッド化した.
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


        


# well-used const
# clockwise from top.
DIRECTION_4 = [[-1,0],[0,1],[1,0],[0,-1]] 
DIRECTION_8 = [[-1,0],[-1,1],[0,1],[1,1],[1,0],[1,-1],[0,-1],[-1,-1]] 
DIRECTION_URDL_TABLE = {'U':(-1,0), 'R':(0,1), 'D':(1,0), 'L':(0,-1)}
DIRECTION_URDL_COORD_PLANE = {'U':(0,1), 'R':(1,0), 'D':(0,-1), 'L':(-1,0)}
MOD = 998244353
INF = float("inf")
LOWER_ALPS = "abcdefghijklmnopqrstuvwxyz"
UPPER_ALPS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
ALL_ALPS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"




# user config
############
DEBUG_MODE=0
############




import copy

N, T, M = MI()
AB = LL_1(M)

fbdn_set = set()
for a,b in AB:
    fbdn_set.add((a,b))

avail = [set(range(N)) for _ in range(N)]
for i in range(N):
    avail[i].discard(i)

for a,b in AB:
    avail[a].discard(b)
    avail[b].discard(a)

DB(avail)

def rec(v, rest, cnt):

    global T, ans

    DB(v, rest, cnt)
    if not rest and cnt < T:
        return

    if cnt == T:
        if not rest:
            ans += 1
        return

    if v == N:
        return

    if v not in rest:
        rec(v+1, rest, cnt)
        return

    rest.discard(v)

    S = rest & avail[v]
    S_arr = list(S)
    L = len(S)

    for bit in range(2**L):
        flg = False
        for i in range(L):
            for j in range(i+1,L):
                if bit >> i & 1 and bit >> j & 1 and (int(S_arr[i]), int(S_arr[j])) in fbdn_set:
                    flg = True
                    break
            else:
                continue
            break

        if flg:
            continue

        rest_copy = copy.copy(rest)
        for i in range(L):
            if bit >> i & 1:
                rest_copy.discard(S_arr[i])

        rec(v+1, rest_copy, cnt+1)


ans = 0
rec(0, set(range(N)), 0)
print(ans)