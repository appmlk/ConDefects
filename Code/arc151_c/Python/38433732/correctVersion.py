import bisect, collections, copy, heapq, itertools, math, sys
sys.setrecursionlimit(10**7)
input = sys.stdin.readline
# P = 10**9+7
P = 998244353

N, M = map(int, input().split())
S = list(list(map(int, input().split())) for _ in range(M))

ans = 0
if M == 0:
    ans ^= N%2
if M > 0:
    ans ^= S[0][0]-1
    ans ^= N-S[-1][0]
for i in range(M-1):
    if S[i][0]+1 == S[i+1][0]:
        continue
    if S[i][1] == S[i+1][1]:
        ans ^= 1
if ans != 0:
    print("Takahashi")
else:
    print("Aoki")