import io
import sys
import itertools
import functools
import collections
import bisect
import operator
import threading
import math
import pprint
import heapq
import time
import decimal
import string
import random

sys.setrecursionlimit(3 * 10 ** 5)
INF = 10 ** 18 + 7

decimal.getcontext().prec = 18

# def testcase():
#   with open("in.txt",mode="w") as input_file:
#     testS = ''.join(random.choice(string.ascii_uppercase) for _ in range(random.randint(1, 10)))
#     testT = ''.join(random.choice(string.ascii_uppercase) for _ in range(random.randint(1, 10)))
#     input_file.write(f"{testS}\n{testT}")
#   return


# _INPUT = """\
# 13 3 5

# """
# sys.stdin = io.StringIO(_INPUT)


# ------------------------------------------------
# ------------------------------------------------

N, H, X = map(int, input().split())
P = list(map(int, input().split()))
print(bisect.bisect_right(P, X - H) + 1)