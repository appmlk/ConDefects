import io
import sys
import math
import collections
import itertools
from operator import mul
from functools import reduce, wraps
from collections import defaultdict, deque
import bisect
import time
import heapq
from copy import deepcopy
import sys
sys.setrecursionlimit(1000000000)


# input
# --------------------------------------------------------------------
def N(): return int(input())
def NM(): return map(int, input().split())  # multi
def NMFAST(): return map(int, sys.stdin.readline().split())
def NL(): return list(map(int, input().split()))  # list
def NR(N): return [list(map(int, sys.stdin.readline().split()))  # row
                   for _ in range(N)]
def NR1(N): return [int(input())  # row
                    for _ in range(N)]


def S(): return input()
def SM(): return input().split()  # multi
def SL(): return list(map(str, input().split()))  # list
def SR(N): return [list(map(str, sys.stdin.readline().split()))  # row
                   for _ in range(N)]
def SR1(N): return [input() for i in range(N)]
def SPR(N): return [list(input()) for i in range(N)]  # 1文字ずつ分割


def F(): return float(input())
def FM(): return map(float, input().split())  # multi
def FL(): return list(map(float, input().split()))  # list
def FR(N): return [list(map(float, sys.stdin.readline().split()))  # row
                   for _ in range(N)]
def FR1(N): return [float(input())  # row
                    for _ in range(N)]
# --------------------------------------------------------------------

# output
# --------------------------------------------------------------------
def P(arg): print(arg)
def Yes(): print("Yes")
def No(): print("No")
def E(): exit()
def PE(arg):
    print(arg)
    exit()
def YE():
    print("Yes")
    exit()
def NE():
    print("No")
    exit()
# --------------------------------------------------------------------

def stop_watch(func):
    @wraps(func)
    def wrapper(*args, **kargs):
        start = time.time()
        result = func(*args, **kargs)
        process_time = time.time() - start
        print(f"{func.__name__}は{process_time}秒かかりました")
        return result
    return wrapper


INF = float('inf')
MOD = 10**9 + 7
MOD2 = 998244353


''' ------------------------debug--------------------------------- '''
# --------------------------------------------------------------------
_INPUT = """\

"""
# sys.stdin = io.StringIO(_INPUT)

''' ------------------------終 debug--------------------------------- '''


'''-----------------------------main-------------------------------'''
# --------------------------------------------------------------------

def main():
    n = N()
    left, right = 1, n
    for i in range(20):
        p = (left + right) // 2
        print('?', p)

        s = input()

        if s == 0:
            left = p
        else:
            right = p

    print('!', left)


if __name__ == '__main__':
    main()
