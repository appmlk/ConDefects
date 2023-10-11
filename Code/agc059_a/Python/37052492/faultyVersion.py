import sys
def input(): return sys.stdin.readline().strip()
def mapint(): return list(map(int, input().split()))
sys.setrecursionlimit(10**9)

N, Q = mapint()
S = list(input())
LR = [mapint() for _ in range(Q)]

last = "-"
diffs = [0]*N
for i in range(N):
    s = S[i]
    if s!=last:
        diffs[i] = 1
    last = s

from itertools import accumulate
diff_cum = [0]+list(accumulate(diffs))
for l, r in LR:
    ans = diff_cum[r] - diff_cum[l-1]
    if diffs[l-1]==1:
        ans -= 1
    ans = -(-ans//2)
    print(ans)





