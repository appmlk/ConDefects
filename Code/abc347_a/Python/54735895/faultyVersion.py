n, k = map(int, input().split())
a = list(map(int, input().split()))
b = []

for i in range(n):
  if a[i] % k == 0:
      b.append(a[i] / k)

b.sort()
print(*b)