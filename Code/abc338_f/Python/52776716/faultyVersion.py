f=lambda:map(int,input().split());N,M=f()
I=1<<60
D=[[I]*N for x in range(N)]
for m in range(M):u,v,c=f();D[u-1][v-1]=min(D[u-1][v-1],c)
for i in range(N):
    for j in range(N):
        for k in range(N):
            D[j][k]=min(D[j][k],D[j][i]+D[i][k])
dp=[[I]*N for bit in range(1<<N)]
for bit in range(1,1<<N):
    lst=[i for i in range(N) if bit&1<<i]
    if len(lst)==1:
        dp[bit][lst[0]]=0
    else:
        for i in lst:
            for j in lst:
                if i==j:
                    continue
                dp[bit][i]=min(dp[bit][i],dp[bit^1<<i][j]+D[j][i])
ans=min(dp[-1])
if ans==I:
    ans="No"
print(ans)