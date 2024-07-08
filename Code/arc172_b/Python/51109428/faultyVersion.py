DIV = 998244353
if True:
    n, k, l = map(int, input().split())
    print(n, k, l)

ans = 1
for i in range(n):
    m = n-k
    print(m)
    diff = min(m, i)
    ans *= l-diff
    ans %= DIV
print(ans)