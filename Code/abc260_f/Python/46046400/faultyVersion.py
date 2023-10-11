s,t,m=map(int,input().split())
dp=[[-1]*t for i in range(t)]
g=[[] for i in range(s)]
for i in range(m):
  a,b=map(int,input().split())
  g[a-1].append(b-s-1)
for i in range(s):
  if not g[i]:
    continue
  for x in g[i]:
    for y in g[i]:
      if x==y:
        continue
      if dp[x][y]!=-1:
        exit(print(x+t,y+t,dp[x][y]+1,i+1))
      else:
        dp[x][y]=i
print(-1)