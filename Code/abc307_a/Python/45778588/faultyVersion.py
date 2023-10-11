N = int(input())
l = list(map(int,input().split()))
lis = []
d = []
b = 0
for j in range(int(N/7)):
  for k in l[7*b:7*(b+1)]:
      lis.append(k)
  d.append(sum(lis))
  lis.clear()
  b += 1
print(*d)