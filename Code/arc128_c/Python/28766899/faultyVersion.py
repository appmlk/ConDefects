n, m, S = map(int,input().split())
a = list(map(int,input().split()))
asum = [0] * (n + 1)
for i in range(n):
    asum[i + 1] += asum[i] + a[i]
ans = 0
for i in range(n + 1):
    for j in range(i, n + 1):
        s = S
        s -= m * (n - j)
        if s < 0:
            continue
        if j - i == 0:
            ans = max(ans, s * (n - j))
            continue
        one = s / (j - i)
        if one > m:
            continue
        now = (asum[j] - asum[i]) * one + m * (asum[n] - asum[j])
        ans = max(ans, now)
print(ans)