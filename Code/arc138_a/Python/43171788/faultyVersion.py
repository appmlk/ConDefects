from sys import setrecursionlimit, stdin
setrecursionlimit(10**6); readline = stdin.readline
M998 = 998244353; M007 = 10**9+7; INF = 10**18
mulint = lambda: map(int, readline().split()); anint = lambda: int(readline())
astr = lambda: readline().rstrip()
from bisect import bisect_right

N, K = mulint()
A = list(mulint())

L, R = A[:K], A[K:]

M = [R[0]]
V = [0]
for i in range(1, N-K):
    if R[i] > M[-1]:
        M.append(R[i])
        V.append(i)
        
V.append(INF)
m = INF
for i in range(K):
    j = bisect_right(M, L[i])
    m = min(m, V[j]+(K-i-1)+1)
print(m)
