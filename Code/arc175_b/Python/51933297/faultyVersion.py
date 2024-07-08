DEBUG = False
testcase = r"""
1
2 3
test
"""[1:]


from typing import *
from collections import Counter, deque, defaultdict
import bisect
import heapq
import copy
import itertools
from pprint import pprint
import math
import sys


_input = input
input = lambda: sys.stdin.readline().rstrip()
mi = lambda: map(int, input().split())
li = lambda: list(mi())

dbp = lambda *args, **kwargs: print("[dbp]", *args, **kwargs)
dbp = lambda *args, **kwargs: None
def asf(): assert False


def main() -> None:
    N, A, B = mi()
    S = list(input())

    # imosで足りてるかを把握する
    # 最終的な値が正: (がおおい
    # 　　　　　　負: )が多い
    #             0: ちょうど
    # 途中の値が0以上: 問題なし
    # 　　　　　　   : 問題あり．)が多い
    imos = [1 if c == '(' else -1 for c in S]
    for i in range(1, 2*N): imos[i] += imos[i-1]
    dbp(imos)

    A_cost = min(A, 2*B)  # B二回やる方が安いならそうする
    if imos[-1] < 0:  # ')'が多すぎる場合
        # 左から多すぎる')'を'('に変換する
        assert imos[-1] % 2 == 0, "一回の操作で最終スコアを2動かすことができるはず．そのため，偶数でなければ0にならない"
        shortage = -imos[-1] // 2  # 回数分. ) -> (の操作でスコアは2改善される
        worst_point = -min(imos) - shortage  # -worst_pointが，) -> ( をした後の一番悪い部分のスコア
        ans = shortage * B
        improve = (worst_point+1) // 2  # 一番悪い部分より左側の)をそれより右側の(で置き換えるといい
        ans += improve * A_cost
        print(ans)
    else:
        # 右から多すぎる'('を')'に変換する
        assert imos[-1] % 2 == 0
        shortage = imos[-1] // 2
        worst_point = -min(imos)
        ans = shortage * B
        improve = (worst_point+1) // 2
        ans += improve*A_cost
        print(ans)


# infinity
INF = int(1e20)

# recursion setting
sys.setrecursionlimit(10**8)

T = TypeVar('T')
# rotate 2dim list
def rot_2dim_list(l: list[list[T]]) -> list[list[T]]:
    return [list(e) for e in zip(*l)][::-1]

# find the minimum subgrid which includes all interests in the grid
# return the subgrid parameter [ymin, xmin, ymax, xmax] as half-open interval [ymin, ymax), [xmin, xmax)
def extract_roi(grid: list[list[T]], interests: set[str]) -> tuple[int, int, int, int]:
    ymin = xmin = INF
    ymax = xmax = -1
    for i in range(len(grid)):
        for j in range(len(grid[i])):
            if grid[i][j] in interests:
                ymin = min(ymin, i)
                xmin = min(xmin, j)
                ymax = max(ymax, i+1)
                xmax = max(xmax, j+1)
    return ymin, xmin, ymax, xmax

# enumerate all 26 alphabets
def get_all_alphabets(lower=False) -> list[str]:
    offset = 32 if lower else 0
    return [chr(ord("A")+i+offset) for i in range(26)]

# express fraction using modulo
def modulo_expr(numer: int, denom: int, mod=998244353) -> int:
    """returns (numer / denom) using modulo"""
    modulo_inv = pow(denom, -1, mod)
    return numer * modulo_inv % mod

# make primes
def make_primes(n: int) -> list[int]:
    """ make primes lesser equal than n """
    is_prime = [True] * (n + 1)
    is_prime[0], is_prime[1] = False, False
    for i in range(2, int(math.sqrt(n)) + 1):
        if is_prime[i]:
            for j in range(2 * i, n + 1, i):
                is_prime[j] = False

    prime_list = [i for i in range(n + 1) if is_prime[i]]

    return prime_list

# calc number of digits for given integer
def get_digit(n: int) -> int:
    assert n >= 1, "n must be greater than or equal to 1"
    return int(math.log10(n)) + 1

# binary search
# This is Memorandum-like function. So it supports ascending order only. Write by yourself.
def ref_binary_search(ok: int, ng: int, is_ok: Callable) -> int:
    while ok + 1 != ng:
        mid = (ok + ng) // 2
        if is_ok(mid):
            ok = mid
        else:
            ng = mid
    return mid

# convert given number into list of bits
# when num_bit is 3, number: 1 -> [0, 0, 1], number: 2 -> [0, 1, 0], number: 3 -> [0, 1, 1]
def num_to_bits(number: int, num_bit: int) -> list[int]:
    return list(map(int, format(number, f"0{num_bit}b")))  # num_bit is shortest length of bits. the length of bits may be longer than num_bit.

# convert given bits to number
# [0, 1] -> 1, [1, 0, 1] -> 5, [1, 0, 1, 0] -> 6
def bits_to_num(bits: list[int]) -> int:
    num = bits[0]
    for i in range(1, len(bits)):
        num *= 2
        num += bits[i]
    return num

# for bit exhaustive search
# if "num_bit" == 2, returns [[0, 0], [0, 1], [1, 0], [1, 1]]
def bit_exhaustive_enumerate(num_bit: int) -> Iterable[list[int]]:
    for i in range(2 ** num_bit):
        yield list(map(int, format(i, f"0{num_bit}b")))

# for exhaustive permutation
# if "num_elem" == 3, returns [(0, 1, 2), (0, 2, 1), (1, 0, 2), (1, 2, 0), (2, 0, 1), (2, 1, 0)]
def exhaustive_permutation(num_elem: int) -> Iterable[tuple[int, ...]]:
    numbers = list(range(num_elem))
    for p in itertools.permutations(numbers):
        yield p


#######################################################
# debug configuration
#######################################################
def debug_warn():
    if DEBUG:
        print("DEBUGGING!!! Please rewite variable DEBUG before submission!!!")

# by this operation, input() returns testcase
if DEBUG:
    import sys, io
    sys.stdin = io.StringIO(testcase)
    debug_warn()
#######################################################


if __name__ == "__main__":
    main()
    debug_warn()
