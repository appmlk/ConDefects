n,d=map(int,input().split())
a=[list(map(int,input().split())) for _ in range(n)]
a2=[[0]*n for _ in range(n)]
tmp=0
for r in range(n):
  for c in range(n):
    x=(a[r][c]+d*(r+c&1))%(2*d)
    if x<=d:
      tmp+=x
      a2[r][c]=a[r][c]-x
    else:
      tmp+=2*d-x
      a2[r][c]=a[r][c]+(2*d-x)
if tmp<=((n**2)*d)//2:
  for i in a2:
    print (*i)
  exit()
for r in range(n):
  for c in range(n):
    x=(a[r][c]+d*(r+c+1&1))%(2*d)
    if x<=d:
      a2[r][c]=a[r][c]-x
    else:
      a2[r][c]=a[r][c]+(2*d-x)
for i in a2:
  print (*i)