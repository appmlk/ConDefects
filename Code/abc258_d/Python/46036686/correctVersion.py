n,x=map(int,input().split())
s=[list(map(int,input().split())) for i in range(n)]
ans=10**20
su=[0]*(n+1)
for i in range(n):
  su[i+1]=su[i]+sum(s[i])
su2=[s[0][1]]*n
for i in range(n):
  su2[i]=min(su2[i-1],s[i][1])
for i in range(min(n,x)):
  ans=min(ans,su[i+1]+su2[i]*(x-i-1))
print(ans)