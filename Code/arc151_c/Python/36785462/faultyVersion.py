n,m = map(int,input().split())
xy = [list(map(int,input().split())) for i in range(m)]
g = 0
if m == 0:
  if n%2:
    print("Takahashi")
  else:
    print("Aoki")
  exit()
for i in range(m+1):
  if i == 0:
    x,y = xy[i]
    g ^= x-1
  elif i == m:
    x,y = xy[i-1]
    g ^= n-x
  else:
    x,y = xy[i]
    px,py = xy[i-1]
    if y != py:
      continue
    g ^= x-px-2

if g:
  print("Takahashi")
else:
  print("Aoki")