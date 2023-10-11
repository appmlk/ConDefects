from collections import deque
N=int(input())
ans=[]
i=0
x=N**2
temp=[]
mod=[deque([]) for _ in range(3)]
while x>0:
  if x%2==1:
    temp.append(x)
    i+=1
  else:
    mod[x%3].append(x)
  x-=1
  if i==N:
    ans.append(temp)
    temp=[]
    i=0

l=len(temp)
if l!=0:
  a=ans[-1][l]
  l1=l
  while temp[-1]%3!=ans[-1][l]%3:
    l-=1
   
  
  b=ans[-1][l]
  ans[-1][l]=a
  ans[-1][l1]=b
j=0
while j<N:
  x=3-ans[-1][i]%3
  if x==3:
    x=0
  temp.append(mod[x].popleft())
  i+=1
  j+=1
  if i==N:
    ans.append(temp)
    temp=[]
    i=0
  

while len(mod[0])+len(mod[1])+len(mod[2])>0:
  if len(mod[0])>0:
    temp.append(mod[0].popleft())
  elif len(mod[1])>0:
    temp.append(mod[1].popleft())
  elif len(mod[2])>0:
    temp.append(mod[2].popleft())

  i+=1
  if i==N:
    ans.append(temp)
    temp=[]
    i=0

for i in range(N):
  print(*ans[i])