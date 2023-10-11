import sys
from collections import deque,defaultdict
import itertools
import heapq
import bisect
import queue

#sys.setrecursionlimit(10 ** 9)
input = lambda: sys.stdin.readline().rstrip()
ii = lambda: int(input())
mi = lambda: map(int, input().split())
li = lambda: list(mi())
li_st = lambda: list(map(str, input().split()))
lli = lambda n: [li() for _ in range(n)]
mod = 998244353

N,M = mi()

B = lli(N)

ans = "Yes"
for i in range(N):
    for j in range(M):
        if i != 0:
            if B[i][j] != B[i-1][j] + 7:
                ans = "No"
        if j != 0:
            if ((B[i][j] - 1) % 7) - ((B[i][j-1] - 1) % 7) != 1:
                ans = "No"
            if B[i][j] - B[i][j-1] != 1:
                ans = "No"

print(ans)
                
