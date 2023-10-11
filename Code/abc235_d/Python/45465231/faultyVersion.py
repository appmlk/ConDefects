import sys,random,bisect
from collections import deque,defaultdict
from heapq import heapify,heappop,heappush
from itertools import permutations
from math import gcd,log,sqrt
from atcoder.modint import ModContext, Modint

ModContext(1).context.append(998244353)
sys.setrecursionlimit(1000000)

input = lambda :sys.stdin.readline().rstrip()
mi = lambda :map(int,input().split())
li = lambda :list(mi())

a, N = mi()
ans = 1 << 60
di = defaultdict(int)
def change(x, cnt):
    # print(x, cnt)
    if di[x] != 0 and di[x] <= cnt:
        return
    di[x] = cnt
    global ans
    if x == 1:
        ans = min(ans, cnt)
        return
    if x % a == 0:
        change(x // a, cnt + 1)
    d = sum(c.isdigit() for c in str(x))
    if d == 1:
        return
    nx = (x % (10 ** (d - 1))) * 10 + x // (10 ** (d-1))
    change(nx, cnt + 1)
    return
change(N, 0)
if ans == 1 << 60:
    print(-1)
else:
    print(ans)