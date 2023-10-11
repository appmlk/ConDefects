import bisect
n, k = map(int, input().split())
a = list( map(int, input().split()) )
for i in range(k):
  a[i] -= 1
p = []
for _ in range(n):
  p.append(list(map(int, input().split())))
  
ans = 0
for i in range(n):
  minr = float("inf")
  for j in a:
    if i==j:
      continue
    dis0 = ( (p[i][0] - p[j][0])**2 + (p[i][1] - p[j][1])**2 )
    minr = min(minr, dis0)
  ans = max(ans, minr)
print(ans**0.5)