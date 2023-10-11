n = int(input())
a = [list(input()) for _ in range(n)]
l, m = [], "0"
for i in range(n):
  for j in range(n):
    if a[i][j]>m:
      m = a[i][j]
      l = [[i, j]]
    elif a[i][j]==m:
      l.append([i, j])
ans = set()
for i in l:
  for x in range(-1, 2, 1):
    for y in range(-1, 2, 1):
      if x==y==0:
        break
      b = ""
      for j in range(n):
        b = b + a[(i[0] + x*j)%n][(i[1] + y*j)%n]
      ans.add(int(b))
ans = list(ans)
ans.sort()
print(ans[-1])