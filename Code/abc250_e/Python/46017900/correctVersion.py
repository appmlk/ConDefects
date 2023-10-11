from collections import defaultdict

n=int(input())
a=list(map(int,input().split()))
b=list(map(int,input().split()))

ans=[[0,-1] for _ in range(n)]

nowa=set()
nowb=set()
notcheck=set()

d=defaultdict(int)

cnt=0

for i in range(n):
  if a[i] in notcheck:
    notcheck.remove(a[i])
  if notcheck:
    ans[i][0]=ans[i-1][0]
  if a[i] in nowa:
    ans[i]=ans[i-1]
    continue
  if a[i] in nowb:
    ans[i][0]=max(ans[i-1][0],d[a[i]])
    if not notcheck:
      ans[i][1]=cnt-1
    nowa.add(a[i])
    continue
  nowa.add(a[i])
  bool=False
  while cnt<n:
    if b[cnt] not in nowa:
      notcheck.add(b[cnt])
    if b[cnt] in nowb:
      cnt+=1
      continue
    else:
      d[b[cnt]]=cnt
      nowb.add(b[cnt])
    if b[cnt]==a[i]:
      bool=True
      ans[i][0]=cnt
      cnt+=1
      while cnt<n:
        if b[cnt] not in nowb:
          break
        cnt+=1
      if not notcheck:
        ans[i][1]=cnt-1
    if bool:
      break
    cnt+=1
  if not bool:
    break
    
q=int(input())

for i in range(q):
  x,y=map(int,input().split())
  x-=1
  y-=1
  if ans[x][0]<=y<=ans[x][1]:
    print('Yes')
  else:
    print('No')