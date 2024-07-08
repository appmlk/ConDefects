n,m=map(int,input().split())
s=0
d={}
for i in range(n):
  a,b=map(int,input().split())
  s+=a
  if b-a not in d:
    d[b-a]=0
  d[b-a]+=1
p=[]
for dd in d:
  pp=1
  while pp<=d[dd]:
    p.append((dd,pp))
    d[dd]-=pp
    pp*=2
  if d[dd]>0:
    p.append((dd,d[dd]))
q=[n+1]*(m+1)
q[s]=0
for dd,pp in p:
  nq=[n+1]*(m+1)
  for i in range(m+1):
    if q[i]<n+1:
      nq[i]=min(nq[i],q[i])
      if 0<=i+dd*pp<=m and dd!=0:
        nq[i+dd*pp]=min(nq[i+dd*pp],q[i]+pp)
  q=nq
for qq in q:
  print(qq if qq<n+1 else -1)