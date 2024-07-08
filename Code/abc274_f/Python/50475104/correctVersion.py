N,T=map(int,input().split())
result=0
L=[]
for i in range(N):
  w,x,v=map(int,input().split())
  L.append((w,x,v))
from collections import defaultdict
for q in range(N):
  R=set()
  score=0
  w,x,v=L[q][:]
  score+=w
  P=defaultdict(int)
  for i in range(N):
    if i==q:
      continue
    w2,x2,v2=L[i][:]
    if x<=x2<=x+T:
      score+=w2
    if v2<v:
      t1=(x2-x)/(v-v2)
      t2=(x2-x-T)/(v-v2)
      if t1>-10**(-11):
        k=int((10**13)*(t1+10**(-11)))
        R.add(k)
        P[k]-=w2
      if t2>10**(-11):
        k=int(t2*10**13)
        R.add(k)
        P[k]+=w2
    elif v2==v:
      continue
    else:
      t1=(x-x2)/(v2-v)
      t2=(x-x2+T)/(v2-v)
      if t1>10**(-11):
        k=int(t1*10**13)
        P[k]+=w2
        R.add(k)
      if t2>-10**(-11):
        k=int((10**13)*(t2+10**(-11)))
        R.add(k)
        P[k]-=w2
  R=list(R)
  R.sort()
  result=max(result,score)
  for i in range(len(R)):
    y=P[R[i]]
    score+=y
    result=max(result,score)
print(result)