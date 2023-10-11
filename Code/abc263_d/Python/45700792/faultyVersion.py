n, l, r = map(int, input().split())
a = list(map(int, input().split()))
sma = 0
smb = 0
mx = 0
ans = float("INF")
for i in range(n):
    sma += a[i]
    smb += a[i] - l
    mx = max(mx, smb)
    ans = min(ans, sma + (n - i - 1) * r - mx)
print(ans)
