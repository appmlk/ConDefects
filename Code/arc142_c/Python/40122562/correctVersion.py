from sys import stdout
mi = 10 ** 15
a = []
n = int(input())
for i in range(3,n+1):
  print("?",1,i)
  stdout.flush()
  d1 = int(input())
  print("?",2,i)
  stdout.flush()
  d2 = int(input())
  mi = min(mi,d1+d2)
  if d1+d2 == 3:
    a.append(i)
if mi == 3 and len(a) == 2:
  print("?",a[0],a[1])
  stdout.flush()
  dd = int(input())
  if dd == 1:
    ans = 3
  else:
    ans = 1
elif mi == 3:
  ans = 1
else:
  ans = mi
print("!",ans)
stdout.flush()
  
  
