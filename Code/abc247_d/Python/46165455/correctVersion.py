import sys
sys.setrecursionlimit(500*500)

# if 'pypyjit' in sys.builtin_module_names:
#     import pypyjit
#     pypyjit.set_param('max_unroll_recursion=-1')
input = sys.stdin.readline
from math import gcd
from functools import reduce
# product('ABCD', repeat=2) => AA AB AC AD BA BB BC BD CA CB CC CD DA DB DC DD
from itertools import product
# permutations('ABCD', 2) => AB AC AD BA BC BD CA CB CD DA DB DC
from itertools import permutations
# combinations('ABCD', 2) => AB AC AD BC BD CD
from itertools import combinations
from itertools import accumulate # 累積和作るやつ
from collections import deque
from collections import defaultdict
from heapq import heappop, heappush
from bisect import bisect_left
# 0埋めされた二進数表現
f'{9:05b}'

alpha2num = lambda c: ord(c) - ord('a')
num2alpha = lambda c: chr(c+97)
popcnt = lambda x: bin(x).count("1")

# 数値判定
"1".isdigit()


dh = [-1, 0, 1, 0]
dw = [0, -1, 0, 1]
dh8 = [-1, -1, -1,  0,  0,  1,  1,  1]
dw8 = [-1,  0,  1, -1,  1, -1,  0,  1]

def resolve():
  Q = int(input()[:-1])
  que = deque()
  for _ in range(Q):
    query = [int(x) for x in input()[:-1].split(" ")]
    q = query[0]
    if q == 1:
      _, x, c = query
      que.append((x, c))
    else:
      _, c = query
      ans = 0
      C = c
      while C > 0:
        x, c_ = que.popleft()
        d = min(C, c_)
        ans += d*x
        C -= d
        c_ -= d
        if c_ > 0:
          que.appendleft((x, c_))
      print(ans)
resolve()
