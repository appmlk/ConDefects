n,m=map(int,input().split())
ans=set()
use=set()
use2=set()

for i in range(10**5):
  for dx in range(1,4):
    for dy in range(1,4):
      x=2*i+dx
      y=2*i+dy
      if 1<=x<=n and 1<=y<=m:
        ans.add((x,y))
        use.add(3*x+y)
        use2.add(x+3*y)

if n<=m:
  for y in range(1,m+1):
    x=n
    if 3*x+y not in use and x+3*y not in use2:
      ans.add((x,y))
      use.add(3*x+y)
      use2.add(x+3*y)
else:
  for x in range(1,n+1):
    y=m
    if 3*x+y not in use and x+3*y not in use2:
      ans.add((x,y))
      use.add(3*x+y)
      use2.add(x+3*y)

print(len(ans))
for x,y in ans:
  print(x,y)