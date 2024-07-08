n,M=map(int,input().split())
q=[[0]*(n+1) for i in range(n+2)]
q[0][0]=1
q[1][0]=-1
for i in range(n+1):
  for j in range(n+1):
    q[i][j]+=q[i-1][j]
    q[i][j]%=M
    for k in range(1,5):
      if i+10**(k-1)<=n and j+1+k<=n:
        q[i+10**(k-1)][j+1+k]+=q[i][j]*(25+(i==0))
        if i+10**k<=n:
          q[i+10**k][j+1+k]-=q[i][j]*(25+(i==0))
print(sum(q[n][:n])%M)