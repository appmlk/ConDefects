n,k=map(int,input().split())
p=list(map(int,input().split()))
q=[0]*n
for i in range(n):
  p[i]-=1
  q[p[i]]=i
e=[]
for i in range(n):
  r=[j for j in range(i) if q[j]-q[i]>=k]
  s=i
  for t in r[::-1]:
    e+=[(q[s],q[t])]
    q[s],q[t]=q[t],q[s]
    s=t
print(len(e))
for i,j in e:
  print(i+1,j+1)