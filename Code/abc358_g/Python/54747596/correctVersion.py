h,w,k=map(int,input().split())
sy,sx=map(lambda x: int(x)-1,input().split())
A=[list(map(int,input().split())) for _ in range(h)]
dp=[[0]*w for _ in range(h)]
ans=0
dp=[[[-1<<60]*w for _ in range(h)]for _ in range(h*w+10)]
dp[0][sy][sx]=0

for t in range(min(h*w,k)+1):
  for i in range(h):
    for j in range(w):
      ans=max(ans,A[i][j]*(k-t)+dp[t][i][j])
      for dy,dx in [(0,1),(0,-1),(1,0),(-1,0)]:
        ny=i+dy;nx=j+dx
        if 0<=ny<h and 0<=nx<w:
          dp[t+1][ny][nx]=max(dp[t+1][ny][nx],dp[t][i][j]+A[ny][nx])

print(ans)
