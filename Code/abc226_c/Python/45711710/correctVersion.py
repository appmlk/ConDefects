import sys
sys.setrecursionlimit(10**8)
inf = float("INF")
from collections import deque, defaultdict, Counter
from itertools import product, combinations, permutations
from heapq import heapify, heappop, heappush
def I():   return input()
def II():  return int(input())
def IS():  return input().split()
def MII(): return map(int, input().split())
def LI():  return list(input())
def LII(): return list(map(int, input().split()))
def SII(): return set(map(int, input().split()))
def LSI(): return list(map(str, input().split()))

N = II()
T = []
A = [list() for _ in range(N)]

for i in range(N):
    t, k, *a = MII() # 技i 習得には Ti時間必要、事前に k個の技を習得しておく必要あり
    T.append(t)
    for num in a:
        A[i].append(num-1)

stack = list()
visited = [False]*N
stack.append(N-1)

ans = T[N-1]

while stack:
    now = stack.pop()

    for nex in A[now]:
        if visited[nex] == False:
            stack.append(nex)
            visited[nex] = True
            ans += T[nex]

print(ans)

