import sys
from collections import defaultdict
MOD = 998_244_353
INF = 10**18

N, Q = map(int, sys.stdin.readline().rstrip().split())
A = list(map(int, sys.stdin.readline().rstrip().split()))
D = defaultdict(list)
for i in range(N):
    D[A[i]].append(i + 1)

print(D)

for q in range(Q):
    x, k = list(map(int, sys.stdin.readline().rstrip().split()))
    if len(D[x]) >= k:
        print(D[x][k - 1])
    else:
        print(-1)
