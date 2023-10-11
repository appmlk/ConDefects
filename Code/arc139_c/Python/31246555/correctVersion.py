n,m=map(int,input().split())
hi=False
if n<m:
  hi=True
  n,m=m,n

l1=[2,4,-2,0,2,-4,-2,0]
canr=(m-1)//2

ll=[[4,4]]
nows=4
for i in range(canr):
  for j in l1:
    nows+=1
    ll.append([nows,nows+j])
    
if m%2==1:
  nowt=nows
  for i in range(n-m):
    nows+=1
    nowt+=3
    ll.append([nows,nowt])
else:
  nowt=nows
  if m==n:
    ll.append([nows+1,nowt+3])
    ll.append([nows+3,nowt+1])
    ll.append([nows+4,nowt+4])
  else:
    ll.append([nows+1,nowt+3])
    ll.append([nows+2,nowt+6])
    nows+=2
    nowt-=2
    for i in range(n-m+2):
      nows+=1
      nowt+=3
      ll.append([nows,nowt])
  
print(len(ll))

if hi==False:
  for l in ll:
    s=l[0]
    t=l[1]
    x=(3*t-s)//8
    y=(3*s-t)//8
    print(x,y)
else:
  for l in ll:
    s=l[0]
    t=l[1]
    y=(3*t-s)//8
    x=(3*s-t)//8
    print(x,y)