n, m, *a = map(int, open(0).read().split())
a.sort()
ans = sum(i*i for i in a)
pairs = n - m
for i in range(pairs):
    ans += 2 * a[i] * a[i+pairs]
print(ans)
