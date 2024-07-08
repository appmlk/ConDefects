from heapq import heappop,heappush
INF=1<<60
N,M=map(int,input().split())
A=list(map(int,input().split()))
B=list(map(int,input().split()))
dp=[0]
for i in range(N):
    ndp=[INF]*((i+1)*(i+2)//2+1)
    for j in range(len(dp)):
        ndp[j]=min(ndp[j],dp[j])
        ndp[j+i+1]=min(ndp[j+i+1],dp[j]+A[i])
    dp=ndp
box=[(INF,-1)]
bsum=0
jsum=0
for i in range(M):
    heappush(box,((B[i]+i)//(i+1),i))
    jsum+=i+1
ans=INF
for i in range(len(dp)):
    if box[0][0]<=i:
        _,j=heappop(box)
        bsum+=B[j]
        jsum-=j+1
    ans=min(ans,dp[-i-1]+bsum+jsum*i)
print(ans)