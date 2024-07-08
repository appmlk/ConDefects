N, M = map(int, input().split())

P = 1
ans = 0

for y in range(2, N + 1):
  P = P * (N + 1 - y) % M
  f = P * pow(N, N - y, M)

  ans += f * (y - 1) * y // 2
  ans %= M

print(ans * N % M)
