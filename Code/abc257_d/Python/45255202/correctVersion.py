from collections import defaultdict,deque
N=int(input())
XY=[]
P=[]

for i in range(N):
  x,y,p=list(map(int,input().split()))
  XY.append((x,y))
  P.append(p)
  
ok=10**18
ng=0
while abs(ok-ng)>1:
  m=(ok+ng)//2
  path=defaultdict(list)
  for i in range(N):
    p=P[i]
    for j in range(N):
      if i==j:
        continue
      if p*m>=abs(XY[i][0]-XY[j][0])+abs(XY[i][1]-XY[j][1]):
        path[i].append(j)
  c=0
  for i in range(N):
    Q=deque()
    check=set()
    check.add(i)
    Q.append(i)
    while len(Q)>0:
      a=Q.popleft()
      for j in path[a]:
        if j not in check:
          Q.append(j)
          check.add(j)
    if len(check)==N:
      c=1
      break
  if c:
    ok=m
  else:
    ng=m
print(ok)
  