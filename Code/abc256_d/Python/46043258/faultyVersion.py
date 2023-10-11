n=int(input())
s=[list(map(int,input().split())) for i in range(n)]
s.sort(key=lambda x:x[0])
ans=[]
now=0
while now<n:
  appending=[s[now][0],s[now][1]]
  while now<n-1 and s[now+1][0]<=appending[-1]:
    now+=1
    appending[-1]=s[now][1]
  ans.append(appending)
  now+=1
for k in ans:
  print(*k)