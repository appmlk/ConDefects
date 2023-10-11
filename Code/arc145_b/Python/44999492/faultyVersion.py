n,a,b = map(int,input().split())
ans = 0
if a <= b:
    ans = n - a + 1
else:
    ans += b * max(0, n // a - 1)
    if n // a > 0:
        ans += min(b, n % a + 1)
print(ans)