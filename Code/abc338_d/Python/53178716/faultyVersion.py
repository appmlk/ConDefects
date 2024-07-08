n, m = map(int, input().split())
x = list(map(int, input().split()))
cost = [0]*(n + 1)

for i in range(m - 1):
    l, r = min(x[i], x[i + 1]), max(x[i], x[i + 1])
    cost[1] += r - l
    cost[l] += n - 2*(r - l)
    cost[r] -= n - 2*(r - l)

ans = 1 << 32
for i in range(1, n + 1):
    cost[i] += cost[i - 1]
    if cost[i] < ans:
        ans = cost[i]
print(ans)