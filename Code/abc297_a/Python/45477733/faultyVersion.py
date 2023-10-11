n, d  =map(int, input().split())
t = list(map(int, input().split()))

ans = -1
for i in range(1, len(t)):
  if t[i] - t[i-1] < d:
    ans = t[i]
    break
print(ans)