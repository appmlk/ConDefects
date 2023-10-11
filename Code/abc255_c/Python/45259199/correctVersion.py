x, a, d, n = map(int, input().split())

if d == 0:
    print(abs(x - a))
    exit(0)

m = (x - a) // d + 1

if m > n:
    m = n - 5

if m <= 6:
    m = 6

# +-5個調べる
ans = -1
for i in range(-5, 6):
    if m + i <= 0 or m + i > n:
        continue

    an = a + d * (m + i - 1)

    # INF設定がめんどくさい
    if ans == -1:
        ans = abs(x - an)

    ans = min(ans, abs(x - an))

print(ans)
