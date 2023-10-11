import sys, re
from math import ceil, floor, sqrt, pi, factorial, gcd, sin ,cos
from copy import deepcopy
from collections import Counter, deque, defaultdict
from heapq import heapify, heappop, heappush
from itertools import accumulate, product, combinations, combinations_with_replacement
from bisect import bisect, bisect_left, bisect_right
from functools import reduce, cmp_to_key
from decimal import Decimal, getcontext
# input = sys.stdin.readline 
def i_input(): return int(input())
def i_map(): return map(int, input().split())
def i_list(): return list(i_map())
def i_row(N): return [i_input() for _ in range(N)]
def i_row_list(N): return [i_list() for _ in range(N)]
def s_input(): return input()
def s_map(): return input().split()
def s_list(): return list(s_map())
def s_row(N): return [s_input for _ in range(N)]
def s_row_str(N): return [s_list() for _ in range(N)]
def s_row_list(N): return [list(s_input()) for _ in range(N)]
def tuple_row_list(N): return [tuple(map(int,input().split())) for _ in range(N)]
def lcm(a, b): return a * b // gcd(a, b)
#sys. setrecursionlimit( 10** 6)     # 再帰関数の時に必要。テスト実行ではREが発生する。
INF = float('inf')
MOD = pow(10,9) + 7
'''
# 誤差があるとき（精度が求められる時）の対策
      - 割り算ではなく掛け算ができるか
      - Decemal('数字の文字列')を使う。Pythonで提出する。
      - A/B ≒ A*(10**20)//B として考えてみる。
      - 小数点以下切り捨ては1で割った商でできる。
# 分数で誤差なしソート
      - sorted() 関数の key 引数に functools.cmp_to_key(cmp) を渡してやる(例: https://qiita.com/nishizumi_noob/items/7a1323c45cf6ce56a368 )
'''
a,b,c,d,e,f,x = i_map()

t_times = x//(a+c)
a_times = x//(d+f)

t_mod = x%(a+c)
a_mod = x%(d+f)

judge = t_times*a*b
if t_mod >= a:
    judge += a*b
else:
    judge += t_mod*b

judge -= a_times*d*e
if a_mod >= d:
    judge -= d*e
else:
    judge -= a_mod*e

if judge > 0:
    print('Takahashi')
elif judge == 0:
    print('Draw')
else:
    print("Aoki")