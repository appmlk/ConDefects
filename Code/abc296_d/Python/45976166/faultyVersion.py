n, m = map(int, input().split())
if n**2 < m:
    print(-1)

    exit()
sq = int(m**0.5)

ans = 10**12 + 1
for i in range(1, sq + 1):
    j = m // i + 1
    if i * j >= m and 1 <= i <= n and 1 <= j <= n:
        ans = min(ans, i * j)
    j = m // i
    if i * j >= m and 1 <= i <= n and 1 <= j <= n:
        ans = min(ans, i * j)
if ans == 10**12 + 1:
    print(-1)
else:
    print(ans)
