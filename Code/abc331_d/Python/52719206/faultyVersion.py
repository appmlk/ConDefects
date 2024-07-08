import sys
from collections import deque, defaultdict
from itertools import (
    accumulate,
    product,
    permutations,
    combinations,
    combinations_with_replacement,
)
import math
from bisect import bisect_left, insort_left, bisect_right, insort_right

# product : bit全探索 product(range(2),repeat=n)
# permutations : 順列全探索
# combinations : 組み合わせ（重複無し）
# combinations_with_replacement : 組み合わせ（重複可）
# from sortedcontainers import SortedSet, SortedList, SortedDict
sys.setrecursionlimit(10**7)
around4 = ((0, -1), (0, 1), (-1, 0), (1, 0))
around8 = ((-1, -1), (-1, 0), (-1, 1), (0, -1), (0, 1), (1, -1), (1, 0), (1, 1))
inf = float("inf")
deq = deque()
dd = defaultdict()

II = lambda: int(input())
MII = lambda: map(int, input().split())
LMII = lambda: list(map(int, input().split()))
Ary2 = lambda w, h, element: [[element] * w for _ in range(h)]
is_not_Index_Er = lambda x, y, h, w: 0 <= x < h and 0 <= y < w  # 範囲外参照


n, q = MII()
P = [input() for _ in range(n)]
Q = [LMII() for _ in range(q)]
acc = [[0] * n for i in range(n)]
temp = 0

for i in range(n):
    yoko = 0
    for j in range(n):
        if P[i][j] == "B":
            yoko += 1
        acc[i][j] = yoko

for j in range(n):
    for i in range(1, n):
        acc[i][j] += acc[i - 1][j]


def f(a, b):
    if a < 0 or b < 0:
        return 0

    h, h_amari = (a + 1) // n, a % n
    w, w_amari = (b + 1) // n, b % n
    if a < n and b < n:
        return acc[a][b]
    result = 0
    if h > 0 and w > 0:
        result += h * w * acc[n - 1][n - 1]
    if w_amari != n - 1 and h > 0:
        result += f(n - 1, w_amari) * h
    if h_amari != n - 1 and w > 0:
        result += f(h_amari, n - 1) * w

    if h_amari != n - 1 or w_amari != n - 1:
        result += f(h_amari, w_amari)
    # print(a, b, result)
    return result


for a, b, c, d in Q:
    print(f(c, d) + f(a - 1, b - 1) - f(c, b - 1) - f(a - 1, d))


# def f(a, b, c, d):
#     if a < 0 or b < 0 or c < 0 or d < 0 or a > c or b > d:
#         return 0
#     if c < n and d < n:
#         if a == b == 0:
#             return acc[c][d]
#         else:
#             result = (
#                 acc[c][d]
#                 - f(0, 0, c, b - 1)
#                 - f(0, 0, a - 1, d)
#                 + f(0, 0, a - 1, b - 1)
#             )
#             return result

#     else:
#         h, w = ((c + 1) // n + (-a // n), (d + 1) // n + (-b // n))
#         result = 0
#         if h > 0 and w > 0:
#             result = h * w * acc[n - 1][n - 1]

#         if b % n != 0 and h > 0 and b % n < d % n:
#             result += f(0, b % n, n - 1, n - 1) * h

#         if d % n != n - 1 and h > 0 and b % n < d % n:
#             result += f(0, 0, n - 1, d % n) * h

#         if a % n != 0 and w > 0 and a % n < c % n:
#             result += f(a % n, 0, n - 1, n - 1) * w

#         if c % n != n - 1 and w > 0 and a % n < c % n:
#             result += f(0, 0, c % n, n - 1) * w

#         if a // n < c // n:
#             if b // n < d // n:
#                 if a % n != 0 and b % n != 0:
#                     result += f(a % n, b % n, n - 1, n - 1)

#                 if c % n != n - 1 and d % n != n - 1:
#                     result += f(0, 0, c % n, d % n)

#                 if a % n != 0 and d % n != n - 1:
#                     result += f(a % n, 0, n - 1, d % n)

#                 if b % n != 0 and c % n != n - 1:
#                     result += f(0, b % n, c % n, n - 1)

#             else:
#                 if a % n != 0 and b % n != 0 and a % n != 0 and d % n != n - 1:
#                     result += f(a % n, b % n, n - 1, d % n)

#                 if c % n != n - 1 and d % n != n - 1 and b % n != 0 and c % n != n - 1:
#                     result += f(0, b % n, c % n, d % n)

#         else:
#             if b // n < d // n:
#                 if a % n != 0 and b % n != 0 and b % n != 0 and c % n != n - 1:
#                     result += f(a % n, b % n, c % n, n - 1)

#                 if c % n != n - 1 and d % n != n - 1 and a % n != 0 and d % n != n - 1:
#                     result += f(a % n, 0, c % n, d % n)

#             else:
#                 result += f(a % n, b % n, c % n, d % n)

#         return result


# for a, b, c, d in Q:
#     print(f(a, b, c, d))
