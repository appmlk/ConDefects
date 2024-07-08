m,n=map(int,input().split())
x=list(map(int,input().split()))
xi=[0for i in range(m)]
for i in range(m):
  xi[x[i]-1]|=(1<<i)
mod=998244353
s=pow(2,m)
t=[[0for i in range(s)]for j in range(n+1)]
pr=[[-1 for i in range(m)]for j in range(s)]
for i in range(s):
  for j in range(m):
    if i&(1<<j)==0 or(i&(1<<j)!=0 and x[j]==j+1):
      c=(i|xi[j])-xi[j]
      c=c|(1<<j)
      pr[i][j]=c
t[0][0]=1
for i in range(n):
    for j in range(s):
      if t[i][j]!=0:
        for k in range(m):
          if pr[j][k]!=-1:
            t[i+1][pr[j][k]]=(t[i+1][pr[j][k]]+t[i][j])%mod
print(sum(t[-1])%mod)