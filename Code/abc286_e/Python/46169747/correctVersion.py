from collections import deque
n=int(input())
A=list(map(int,input().split()))
S=[input() for i in range(n)]
graph=[[] for i in range(n)]
for i in range(n):
  for j in range(n):
    if i==j:
      continue
    if S[i][j]=="Y":
      graph[i].append(j)
ans=[[[10**12,-1] for i in range(n)] for j in range(n)]
for i in range(n):
  d=deque()
  d.append([i,0,A[i]])
  while d:
    v=d.popleft()
    if v[1]>ans[i][v[0]][0] or v[2]<ans[i][v[0]][1]:
      continue
    for j in graph[v[0]]:
      if v[1]<ans[i][j][0]-1:
        ans[i][j]=[v[1]+1,v[2]+A[j]]
        d.append([j,v[1]+1,v[2]+A[j]])
      elif v[1]==ans[i][j][0]-1 and v[2]+A[j]>ans[i][j][1]:
        ans[i][j]=[v[1]+1,v[2]+A[j]]
        d.append([j,v[1]+1,v[2]+A[j]])
q=int(input())
for i in range(q):
  a,b=map(int,input().split())
  a-=1
  b-=1
  if ans[a][b][0]==10**12:
    print("Impossible")
  else:
    print(ans[a][b][0],ans[a][b][1])