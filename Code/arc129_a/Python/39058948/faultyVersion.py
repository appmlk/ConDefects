import sys, math
from collections import deque
sys.setrecursionlimit(10 ** 9)
N, L, R = map(int,input().split())
p = N.bit_length()
ans = 0
for i in range(p):
    if not (N >> i) & 1:
        continue
    ups = min(R, (1 << (i + 1)) - 1)
    los = max(L, (1 << i))
    ans += ups - los + 1
print(ans)    
