
n, m, *a = map(int, open(0).read().split())
a.sort()
ans = sum(i*i for i in a)
pairs = n - m
for i in range(pairs):
    ans += 2 * a[i] * a[pairs+pairs-1-i]
print(ans)
