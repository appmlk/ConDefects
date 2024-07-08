M,D = map(int,input().split())
y,m,d = map(int,input().split())

if d+1 > D:
  d = 1
  if m+1 > M:
    m = 1
    y = y+1
  else:
    m = m+1
else:
  d = d+1

print(y,m,d)