n,x=map(int,input().split())
L=[list(map(int,input().split())) for i in range(n)]
total=[0 for i in range(n)]
total[0]=L[0][0]+L[0][1]
for i in range(1,n):
  total[i]=total[i-1]+L[i][0]+L[i][1]
ans=10**20
m=10**10
for i in range(min(n,x)):
  num=0
  num+=total[i]
  if m>L[i][1]:
    m=L[i][1]
  num+=(x-i-1)*m
  if ans>num:
    ans=num
print(ans)