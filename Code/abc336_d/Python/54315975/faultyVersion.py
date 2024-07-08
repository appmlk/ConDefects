n,*a = map(int,open(0).read().split())
b = [1]*n
for i in range(n-1):
  if a[i]>b[i]:
    b[i+1] = b[i] + 1
  else: b[i+1] = a[i] + 1
c = [1]*n
for i in range(n-1,0,-1):
  if a[i]>c[i]:
    c[i-1] = c[i] + 1
  else: c[i-1] = a[i] + 1
d = [1]*n
for i in range(n):
  if a[i]>=b[i] or a[i]>=c[i]:
    d[i] = min(b[i],c[i])
print(d,max(d))