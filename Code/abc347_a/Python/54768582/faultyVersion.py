n, k = map(int, input().split())
a = list(map(int, input().split()))
ans = []

for i in range(n):
  if a[i] % k == 0:
    ans.append(a[i]//2)

print(*ans)