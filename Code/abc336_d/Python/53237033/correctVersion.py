n = int(input())
a = list(map(int, input().split()))
l = [0]*(n)
r = [0]*(n)
l[0] = 1
r[0] = 1
for i in range(1, n):
    l[i] = min(l[i - 1] + 1, a[i])
for i in range(1, n):
    r[i] = min(r[i - 1] + 1, a[n - i - 1])
ans = 1
for i in range(n):
    if ans < min(l[i], r[n - i - 1]):
        ans = min(l[i], r[n - i - 1])
print(ans)
