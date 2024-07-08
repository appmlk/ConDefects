# import sys
# sys.setrecursionlimit(10**6)
# sys.set_int_max_str_digits(10**6)
# from scipy.optimize import bisect
# from collections import defaultdict, Counter
# import bisect
# import heapq

mod = 998244353
# ds = [(-1,0),(0,1),(1,0),(0,-1)]

# S = input()
# N = int(input())
M, N = map(int, input().split())
X = list(map(int, input().split()))
dp = [[0 for _ in range(1 << M)] for _ in range(N+1)]

# for j in range(M):
dp[0][(1 << M)-1] = 1

table = [0 for i in range(M)]
for a in range(M):
    for b in range(M):
        x = X[b]-1
        if x == a:
            table[a] |= 1 << b

for i in range(N):
    for mask in range(1 << M):
        for a in range(M):
            if mask >> a & 1:
                nmask = mask & ~(1 << a)
                nmask |= table[a]
                dp[i+1][nmask] = (dp[i+1][nmask] + dp[i][mask]) % mod


                
# print(dp)
ans = sum(dp[-1]) % mod
print(ans)