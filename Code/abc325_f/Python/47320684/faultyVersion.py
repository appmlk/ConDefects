import math
import sys
sys.setrecursionlimit(500_000)
from collections import defaultdict

n = int(input())
d = list(map(int, input().split()))
l, c, k = [0] * 2, [0] * 2, [0] * 2
for i in range(2):
    l[i], c[i], k[i] = map(int, input().split())
dp = [float('inf')] * (k[0] + 1)
dp[0] = 0
for di in d:
    newdp = [float('inf')] * (k[0] + 1)
    limit0 = (di + l[0] - 1) // l[0]
    for i0, v in enumerate(dp):
        for i in range(0, min(limit0, k[0] - i0) + 1):
            j = (di - l[0] * i + l[1] - 1) // l[1]
            v1 = v + j
            if v1 <= k[1] and v1 < newdp[i0 + i]:
                newdp[i0 + i] = v1
    dp = newdp
ans = float('inf')
for i, v in enumerate(dp):
    ans = min(ans, i * c[0] + v * c[1])
if ans == float('inf'):
    ans = -1
print(ans)    


