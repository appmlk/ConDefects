from collections import defaultdict as dd
import bisect
N = int(input())
A =list(map(int, input().split()))
Q = int(input())

idx = [ [] for _ in range(N+1) ]
for i in range(N):
    idx[A[i]].append(i+1)
for _ in range(Q):
    L,R,X =map(int, input().split())
    l = bisect.bisect_left(idx[X], L)
    r = bisect.bisect_right(idx[X], R)
    print(r-l)