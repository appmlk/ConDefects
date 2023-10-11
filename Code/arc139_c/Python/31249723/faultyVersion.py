n, m = map(int, input().split())
ab = []
nexts = list(range(8))
for a in range(n + 3 * m):
    br = (3 * a) % 8
    b = nexts[br]
    mn = max((a + 2) // 3, 3 * a - 8 * m + 1)
    mx = min(3 * a, (a + 8 * n - 1) // 3)
    if mn > mx:
        continue
    if b < mn:
        b += (mn - br + 7) // 8 * 8
    if b <= mx:
        ab.append((a, b))
        nexts[br] = b + 8
print(len(ab))
for a, b in ab:
    x, y = (-a + 3 * b) // 8, (3 * a - b) // 8
    print(x + 1, y + 1)