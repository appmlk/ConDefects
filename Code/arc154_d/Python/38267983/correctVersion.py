from sys import stdin
input=lambda :stdin.readline()[:-1]

def ask(i,j,k):
  print('?',i+1,j+1,k+1,flush=True)
  res=input()
  return res=='Yes'


n=int(input())
if n==1:
  print('!',1)
  exit()

one=0
for i in range(1,n):
  if not ask(i,i,one):
    one=i

now=[one]
for i in range(n):
  if i==one:
    continue
  ng,ok=-1,len(now)
  while abs(ng-ok)>1:
    mid=(ok+ng)//2
    if ask(i,one,now[mid]):
      ok=mid
    else:
      ng=mid
  now=now[:ok]+[i]+now[ok:]

P=[0]*n
for i in range(n):
  P[now[i]]=n-i
  
print('!',*P,flush=True)