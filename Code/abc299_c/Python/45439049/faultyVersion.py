n=int(input())+2
s='-'+input()+'-'
m=0
x=0
for i in range(n):
  if s[i]=='-':
    m=max(m,x)
    x=0
  else: x+=1
if m==n-2:
  print(-1)
else: print(m)