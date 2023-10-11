DEBUG = False
testcase = r"""
15 158260522
877914575 2436426
24979445 61648772
623690081 33933447
476190629 62703497
211047202 71407775
628894325 31963982
822804784 50968417
430302156 82631932
161735902 80895728
923078537 7723857
189330739 10286918
802329211 4539679
303238506 17063340
492686568 73361868
125660016 50287940
"""[1:]


from collections import Counter, deque
import bisect
import heapq
import copy
import itertools
from distutils.util import strtobool
from pprint import pprint
from typing import *
import math
import sys


def main():
    N, K = map(int, input().split())
    Q = []
    for _ in range(N):
        a, b = map(int, input().split())
        Q.append((a, b))

    sum_b = sum(b for a, b in Q)
    if sum_b <= K:
        print(1)
        return

    Q.sort(key=lambda x: x[0])
    day = Q[-1][0] + 1
    num = 0

    for a, b in reversed(Q):
        if num + b < K:
            num += b
        else:
            day = a + 1
            break

    print(day)

# infinity
INF = int(1e20)

# recursion setting
sys.setrecursionlimit(2_000_000_000)

# make primes
def make_primes(n: int) -> List[int]:
    """ make primes lesser equal than n """
    is_prime = [True] * (n + 1)
    is_prime[0], is_prime[1] = False, False
    for i in range(2, int(math.sqrt(n)) + 1):
        if is_prime[i]:
            for j in range(2 * i, n + 1, i):
                is_prime[j] = False

    prime_list = [i for i in range(n + 1) if is_prime[i]]

    return prime_list

# binary search
# This is Memorandum-like function. So it supports ascending order only.
def binary_search(ok: int, ng: int, is_ok: Callable) -> int:
    while ok + 1 != ng:
        mid = (ok + ng) // 2
        if is_ok(mid):
            ok = mid
        else:
            ng = mid
    return mid

# bit shift
def bit_shift(number: int, shift: int, shift_right=False) -> int:
    if not shift_right:
        return int(number * (2 ** shift))
    else:
        return int(number / (2 ** shift))

# convert given number into list of bits
# when num_bit is 3, number: 1 -> [0, 0, 1], number: 2 -> [0, 1, 0], number: 3 -> [0, 1, 1]
def num_to_bits(number: int, num_bit: int) -> List[int]:
    return list(map(strtobool, format(number, f"0{num_bit}b")))

# convert given bits to number
# [0, 1] -> 1, [1, 0, 1] -> 5, [1, 0, 1, 0] -> 6
def bits_to_num(bits: List[int]) -> int:
    num = bits[0]
    for i in range(1, len(bits)):
        num *= 2
        num += bits[i]
    return num

# for bit exhaustive search
# if "num_bit" == 2, returns [[0, 0], [0, 1], [1, 0], [1, 1]]
def bit_exhaustive_enumerate(num_bit: int) -> Iterable[List[int]]:
    for i in range(2 ** num_bit):
        yield list(map(strtobool, format(i, f"0{num_bit}b")))

# for exhaustive permutation
# if "num_elem" == 3, returns [(0, 1, 2), (0, 2, 1), (1, 0, 2), (1, 2, 0), (2, 0, 1), (2, 1, 0)]
def exhaustive_permutation(num_elem: int) -> Iterable[Tuple[int]]:
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
