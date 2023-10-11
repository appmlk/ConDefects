import copy
import gc
import itertools
from array import array
from fractions import Fraction
import heapq
import math
import operator
import os, sys
import profile
import cProfile
import random
import re
import string
from bisect import bisect_left, bisect_right
from collections import defaultdict, deque, Counter
from functools import reduce
from io import IOBase, BytesIO
from itertools import count, groupby, accumulate, permutations, combinations_with_replacement, product
from math import gcd
from operator import xor, add
from typing import List


input = lambda: sys.stdin.readline().rstrip("\r\n")


# print = lambda d: sys.stdout.write(str(d)+"\n")
def read_int_list(): return list(map(int, input().split()))


def read_int_tuple(): return tuple(map(int, input().split()))


def read_int(): return int(input())

# endregion

### CODE HERE

# f = open('inputs', 'r')
# def input(): return f.readline().rstrip("\r\n")


# sys.setrecursionlimit(212345)


P = 23

def solve(n, m):
    for i in range(n):
        print(*[(i // P * j // P + i + j) % P + 1 for j in range(m)])

def main():
    n, m = read_int_tuple()
    solve(n, m)

if __name__ == "__main__":
    main()
    # cProfile.run("main()")