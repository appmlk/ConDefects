n, l, r = map(int, input().split())

bin_n = bin(n)[2:]

rev = reversed(bin_n)
wari = []
for i, c in enumerate(rev):
    if c == '1':
        wari.append(2 ** i)

ans = 0
for num in wari:
    s = num
    e = s * 2 - 1
    cnt = min(r, e) - max(l, s) + 1

    ans += max(cnt, 0)
print(ans)
