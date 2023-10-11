from sys import stdin
n,m = map(int,input().split())
l = []
for i in range(m):
  x,y = map(int,stdin.readline().split())
  l.append([x-1,y])
l.sort()
if m == 0:
  ans = n % 2
else:
  ans = l[0][0] ^ (n - l[-1][0] - 1)
  for i in range(1,len(l)):
    if l[i][1] == l[i-1][1]:
      ans ^= 1
if ans != 0:
  print("Takahashi")
else:
  print("Aoki")