h,w=map(int,input().split())
c=[input() for _ in range(h)]

q=int(input())

rh=0
sh=1
rw=0
sw=1

for _ in range(q):
  a,b=map(int,input().split())
  a-=1
  b-=1
  if rh<=a:
    rh=a-rh
  else:
    rh=h-(rh-a)
  if sh<=a:
    sh=a-sh
  else:
    sh=h-(sh-a)
  if rw<=b:
    rw=b-rw
  else:
    rw=w-(rw-b)
  if sw<=b:
    sw=b-sw
  else:
    sw=w-(sw-b)
    
ansh=[0]*h
answ=[0]*w

x=sh-rh
y=sw-rw


for i in range(h):
  ansh[rh]=i
  rh+=x
  rh%=h
  
for i in range(w):
  answ[rw]=i
  rw+=y
  rw%=w
  
  
ans=[[0]*w for _ in range(h)]

for i in range(h):
  for j in range(w):
    ans[i][j]=c[ansh[i]][answ[j]]
    
for i in ans:
  print(''.join(i))