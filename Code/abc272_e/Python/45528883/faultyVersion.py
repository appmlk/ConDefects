n, m = map(int, input().split())
a = list(map(int, input().split()))
d = [set() for _ in range(m)]

for i in range(n):
  delta = -(i+1)//a[i] - 2
  for j in range(max(0, delta), m):
    tmp = (i+1)*(j+1)+a[i]
    if tmp > n+1:
      break
    if 0 <= tmp:
      d[j].add(tmp)

ans = [0]*m
for i in range(m):
  for j in range(len(d[i])+2):
    if j not in d[i]:
      ans[i] = j
      break


print(*ans, sep='\n')
