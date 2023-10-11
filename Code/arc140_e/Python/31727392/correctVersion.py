n,m=map(int,input().split())
ans=[]
for i in range(n):
  ans.append([])
  for j in range(m):
    ans[-1].append(1)
for i in range(n):
  for j in range(m):
    ans[i][j]=(i+j+(i//23)*(j//23))%23+1
for i in range(n):
  print(' '.join(map(str,ans[i])))