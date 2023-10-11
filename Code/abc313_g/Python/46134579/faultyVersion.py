import sys
from collections import defaultdict
ipt = sys.stdin.readline
MOD = 998244353

def iin():
    return int(ipt())
def lmin():
    return list(map(int,ipt().split()))

def floor_sum_unsigned(n, m, a, b):
    mod = MOD # 必要なら
    ans = 0
    while True:
        # 領域①
        if a >= m:
            ans += n * (n - 1) * (a // m) // 2
            a %= m
        # 領域②
        if b >= m:
            ans += n * (b // m)
            b %= m
        if ans >= mod: ans %= mod # 必要なら

        y_max = a * n + b        
        if y_max < m: break
        # 領域③
        n, b, m, a, = y_max // m, y_max % m, a, m
    return ans   


N = iin()
A = lmin()
A.sort()

b = N*A[0]
ans = A[0]+1
#print(b,ans)
for i in range(1,N):
    n = A[i]-A[i-1]
    a = N-i
    m = N
    ans += floor_sum_unsigned(n+1,m,a,b)-floor_sum_unsigned(1,m,a,b)+n
    ans %= MOD
    #print(ans)
    b += n*a
    #print(b,ans)

print(ans)

#print(floor_sum_unsigned(3,3,2,3)-floor_sum_unsigned(1,3,2,3))