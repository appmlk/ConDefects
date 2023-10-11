from sys import stdin
input=lambda :stdin.readline()[:-1]

h,w=map(int,input().split())
a=[]
for i in range(h):
  b=list(map(int,input().split()))
  b=[b[j]*(-1)**(i+j) for j in range(w)]
  a.append(b)

p=[0]*h
q=[0]*w
for i in range(h):
  for j in range(w):
    p[i]+=a[i][j]
    q[j]+=a[i][j]

ans=[[0]*w for i in range(h)]
for i in range(h):
  for j in range(w):
    if p[i]>0 and q[j]>0:
      t=min(p[i],q[j])
      ans[i][j]=+t
      p[i]-=t
      q[j]-=t
    elif p[i]<0 and q[j]<0:
      t=max(p[i],q[j])
      ans[i][j]+=t
      p[i]-=t
      q[j]-=t

for i in range(h):
  for j in range(h):
    if p[i]>0 and p[j]<0:
      t=min(p[i],-p[j])
      ans[i][0]+=t
      ans[j][0]-=t
      p[i]-=t
      p[j]+=t

for i in range(w):
  for j in range(w):
    if q[i]>0 and q[j]<0:
      t=min(q[i],-q[j])
      ans[0][i]+=t
      ans[0][j]-=t
      q[i]-=t
      q[j]+=t

ans_sum=0
for i in range(h):
  for j in range(w):
    ans[i][j]*=(-1)**(h+w)
    ans_sum+=abs(ans[i][j])

print(ans_sum)
for i in ans:
  print(*i)