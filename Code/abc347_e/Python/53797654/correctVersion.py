# INF = 1<<60 # 1152921504606846976 ~ 10^18

# def meguru_bisect(ng, ok):
#     while ok - ng > 1:
#         mid = (ng + ok) // 2
#         if is_ok(mid):  # 小さくできる
#             ok = mid
#         else:
#             ng = mid  # 大きくしないといけない
#     return ok

import sys


def input():
    return sys.stdin.readline()[:-1]


# sys.setrecursionlimit(10**6)

N, Q = map(int, input().split())
A = list(map(int, input().split()))

types = []
from collections import defaultdict
d = defaultdict(int)
for a in A:
    if d[a]%2==0:
        types.append(0)
    else:
        types.append(1)
    d[a] += 1

cumsum = [0]*(Q+1)
l = 0
for i, t in enumerate(types):
    if t==0:
        l += 1
    else:
        l -= 1
    if i < Q+2:
        cumsum[i+1] = cumsum[i] + l

ans = [0]*(N+1)
pre = [-1]*(N+1)
for i, a in enumerate(A):
    if pre[a] == -1:
        pre[a] = i+1
    else:
        ans[a] += cumsum[i] - cumsum[pre[a]-1]
        pre[a] = -1

for i, x in enumerate(pre):
    if x != -1:
        ans[i] += cumsum[-1] - cumsum[pre[i]-1]
print(*ans[1:])
