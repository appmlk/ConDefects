n,l,r=map(int,input().split())
a=[]

for k in range(1,l):
  a.append(k+1)
for R in range(r,l-1,-1):
  a.append(R)
for x in range(r+1,n+1):
  a.append(x)
print(*a)