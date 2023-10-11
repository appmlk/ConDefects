n = int(input())
a = list(map(int,input().split()))
imos = [0]*(n+1)
for i in range(n):
  imos[0] += a[i]
  imos[i+1] -= a[i]
for i in range(n):
  imos[i+1] += imos[i]
ans = [0]*n
if imos[0] != 0:
  r = 0
  for i in range(1,n):
    r += imos[i]*abs(imos[0])
    ans[i] = abs(imos[0])
  ans[0] = -r//imos[0]
  for i in range(n-1):
    ans[i+1] += ans[i]
  print("Yes")
  print(*ans,sep=' ')
  cal = 0
  for i in range(n):
    cal += a[i]*ans[i]
else:
  x,y = -1,-1
  s = 0
  flag = True
  for i in range(n):
    if imos[i] > 0:
      flag = False
  if flag:
    print("No")
    exit()
  flag = True
  for i in range(n):
    if imos[i] < 0:
      flag = False
  if flag:
    print("No")
    exit()
  for i in range(1,n):
    s += imos[i]
    if imos[i] == -1 and x == -1:
      x = i
    elif imos[i] == 1:
      y = i
  ans = [0]*n
  if s >= 0:
    for i in range(n):
      if i == x:
        ans[i] = 1+s
      else:
        ans[i] = 1
  else:
    for i in range(n):
      if i == y:
        ans[i] = 1-s
      else:
        ans[i] = 1
  for i in range(n-1):
    ans[i] += ans[i]
  print("Yes")
  print(*ans,sep=' ')