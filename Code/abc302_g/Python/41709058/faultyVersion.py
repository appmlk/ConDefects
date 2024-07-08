N=int(input())
A=list(map(int,input().split()))
cnt=[0]*4
for Ai in A:
  cnt[Ai-1]+=1
mat=[[0]*4 for _ in range(4)]
now=0
for Ai in A:
  if cnt[now]==0: now+=1
  mat[now][Ai-1]+=1
  cnt[now]-=1
ans=0
for i in range(4):
  mat[i][i]=0
  for j in range(i):
    m=min(mat[i][j],mat[j][i])
    ans+=m
    mat[i][j]-=m ; mat[j][i]-=m
li=[(0,1,2),(1,2,3),(2,3,0),(3,0,1),(0,3,2),(1,0,3),(2,1,0),(3,2,1)]
while True:
  m=-1 ; idx=-1
  for t,(i,j,k) in enumerate(li):
    if min(mat[i][j],mat[j][k],mat[k][i])>m:
      m=min(mat[i][j],mat[j][k],mat[k][i]) ; idx=t
  if not m: break
  ans+=2
  i,j,k=li[idx]
  mat[i][j],mat[j][k],mat[k][i]=mat[i][j]-1,mat[j][k]-1,mat[k][i]-1
S=0
for i in range(4):
  for j in range(4):
    S+=mat[i][j]
ans+=3*S//4
print(ans)