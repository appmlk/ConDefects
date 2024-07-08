DIV = 998244353
if True:
    n, k, l = map(int, input().split())

ans = 1
for i in range(n):
    m = n-k
    diff = min(m, i)
    ans *= l-diff
    ans %= DIV
print(ans)