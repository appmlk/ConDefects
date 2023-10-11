n, x = map(int, input().split())
a, b = [0] * n, [0] * n
for i in range(n):
    a[i], b[i] = map(int, input().split())

cum_a = [0]
cum_b = [0]
for i in range(n):
    cum_a.append(cum_a[-1] + a[i])
    cum_b.append(cum_b[-1] + b[i])

ans = 10 ** 20
for i in range(n):
    temp = cum_a[i + 1] + cum_b[i] + b[i] * (x - i)
    ans = min(ans, temp)

print(ans)