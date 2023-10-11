N, M = map(int, input().split())
p = [int(i) for i in input().split()]

memo = [-1] * (N + 1)
for i in range(M):
    x, y = map(int, input().split())
    memo[x] = max(memo[x], y)

for j in range(2, N + 1):
    memo[j] = max(memo[j], memo[p[j - 2]] - 1)

ans = 0
for i in range(N + 1):
    ans += memo[i] >= 0

print(ans)
