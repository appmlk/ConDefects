n=int(input())
a=list(map(int,input().split()))
s=[list(map(str,input())) for i in range(n)]
q=int(input())
ans=[[[10**9,10**20] for i in range(n)] for i in range(n)]
for i in range(n):
  ans[i][i]=[0,a[i]]
for i in range(n):
  for j in range(n):
    if s[i][j]=="Y":
      ans[i][j]=[1,a[i]+a[j]]
for k in range(n):
  for i in range(n):
    for j in range(n):
      if ans[i][j][0]>ans[i][k][0]+ans[k][j][0]:
        ans[i][j]=[ans[i][k][0]+ans[k][j][0],ans[i][k][1]+ans[k][j][1]-a[k]]
      elif ans[i][j][0]==ans[i][k][0]+ans[k][j][0]:
        ans[i][j][1]=max(ans[i][k][1]+ans[k][j][1]-a[k],ans[i][j][1])
for i in range(q):
  c,b=map(int,input().split())
  c-=1;b-=1
  if ans[c][b][1]==10**20:
    print("Impossible")
  else:
    print(*ans[c][b])