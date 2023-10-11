# import系 ---
from heapq import heappush, heappop
from math import factorial

# 入力用 ---
INT = lambda: int(input())
MI = lambda: map(int, input().split())
MI_DEC = lambda: map(lambda x: int(x) - 1, input().split())
LI = lambda: list(map(int, input().split()))
LI_DEC = lambda: list(map(lambda x: int(x) - 1, input().split()))
LS = lambda: list(input())
LSS = lambda: input().split()

INF = 1 << 60
MOD = 998244353

# コード ---
N = INT()
X = sorted(LI())

print(sum(X[N:4*N]) / 3*N)
