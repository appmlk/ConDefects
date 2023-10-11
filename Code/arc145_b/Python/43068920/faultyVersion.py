from sys import setrecursionlimit, stdin
setrecursionlimit(10**6); readline = stdin.readline
M998 = 998244353; M007 = 10**9+7; INF = 10**18
mulint = lambda: map(int, readline().split()); anint = lambda: int(readline())
astr = lambda: readline().rstrip()

N, A, B = mulint()

def f(X):
    p, q = divmod(X,A)
    return p*min(A, B) + min(q, B-1)

print(f(N)-f(A-1))