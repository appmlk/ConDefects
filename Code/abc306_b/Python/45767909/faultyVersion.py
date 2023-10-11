A = list(map(int, input().split()))

ans = 0
for i in range(64):
  ans += A[i] * 2 ** A[i]
print(ans)