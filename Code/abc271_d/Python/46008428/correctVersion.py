n,s=map(int, input().split())
c=[0]*n
d=[]
for i in range(n):
  a,b=map(int, input().split())
  if a<=b:
    d.append(b-a)
    s-=a
  else:
    c[i]=1
    d.append(a-b)
    s-=b

if s<0:
  print("No")
  exit()

dp=[[0]*(s+1) for _ in range(n+1)]
dp[0][0]=1
for i in range(n):
  D=d[i]
  for j in range(s+1):
    if dp[i][j]==1:
      dp[i+1][j]=1
      if j+D<=s:
        dp[i+1][j+D]=1

if dp[-1][-1]==0:
  print("No")
  exit()

ans=""
e=["H","T"]
now=s
for i in reversed(range(n)):
  if dp[i][now]==1:
    ans+=e[c[i]]
  else:
    ans+=e[(c[i]+1)%2]
    now-=d[i]



ans=ans[::-1]
print("Yes")
print(ans)