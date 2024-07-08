import sys, bisect, math
sys.setrecursionlimit(10**8)
sys.set_int_max_str_digits(0)
inf = 1<<60
# inf = float('INF')
from collections import deque, defaultdict, Counter
from itertools import product, combinations, permutations, accumulate
from heapq import heapify, heappop, heappush
from sortedcontainers import SortedList
def I():   return input()
def II():  return int(input())
def IS():  return input().split()
def MII(): return map(int, input().split())
def LI():  return list(input())
def TII(): return tuple(map(int, input().split()))
def LII(): return list(map(int, input().split()))
def LSI(): return list(map(str, input().split()))

N, M, L = MII()
A = LII()
B = LII()
ng = defaultdict(lambda: defaultdict(int))
for _ in range(L):
    c, d = MII()
    c -= 1
    d -= 1
    ng[c][d] = 1

A2 = []
for i, a in enumerate(A): # もともとのメニューidを保持した状態でsortする
    A2.append((-a, i))
B2 = []
for i, b in enumerate(B):
    B2.append((-b, i))

A2.sort()
B2.sort()
que = []
heappush(que, (A2[0][0] + B2[0][0], 0, 0, A2[0][1], B2[0][1]))

done = defaultdict(lambda: defaultdict(int))
# heapq に入れたら done にメモする
done[0][0] = 1

# L回回せば 食べ合わせが悪いもの以外がでる
for i in range(L):
    # このループの中ででないのであれば ループ後にでてくる1個目が答えになるはず
    value, apos, bpos, aind, bind = heappop(que)
    # ng に該当かチェック
    if ng[aind][bind] != 1:
        exit(print(-value))

    if apos + 1 <= N - 1:
        if done[apos + 1][bpos] != 1:
            heappush(que, (A2[apos + 1][0] + B2[bpos][0], apos + 1, bpos, A2[apos + 1][1], B2[bpos][1]))
            done[apos + 1][bpos] = 1

    if bpos + 1 <= M - 1:
        if done[apos][bpos + 1] != 1:
            heappush(que, (A2[apos][0] + B2[bpos + 1][0], apos, bpos + 1, A2[apos][1], B2[bpos + 1][1]))
            done[apos][bpos + 1] = 1
else:
    value, _, _, _, _ = heappop(que)
    print(-value)

