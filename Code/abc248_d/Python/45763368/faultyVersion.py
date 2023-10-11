from bisect import *

N = int(input())
A = list(map(int, input().split()))

at = [[] for _ in range(N)]
for i, x in enumerate(A):
  at[x - 1].append(i)

Q = int(input())
for it in range(Q):
  L, R, X = map(int, input().split())
  L -= 1
  have = bisect_right(at[X - 1], R) - bisect_left(at[X - 1], L)
  print(have)
