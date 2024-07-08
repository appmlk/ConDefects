s=input()
n=int(input())
ts=[input() for i in range(n)]

e1=[[-1]*26]
p=[0]
v=1
d=[""]
for i in range(n):
  t=ts[i]
  y=0
  for j in range(len(t)):
    u=ord(t[j])-ord("a")
    if e1[y][u]==-1:
      e1+=[[-1]*26]
      e1[y][u]=v
      p+=[y]
      d+=[t[j]]
      v+=1
    y=e1[y][u]

x=[0]*v
for i in range(n):
  t=ts[i]
  y=0
  for j in range(len(t)):
    u=ord(t[j])-ord("a")
    y=e1[y][u]
    if j==len(t)-1:
      x[y]=1

e2=[0]*v
q=[0]
for i in q:
  if p[i]!=0:
    y=e2[p[i]]
    u=ord(d[i][-1])-ord("a")
    while y!=0 and e1[y][u]==-1:
      y=e2[y]
    if e1[y][u]!=-1:
      e2[i]=e1[y][u]
      x[i]|=x[e2[i]]
  for j in e1[i]:
    if j!=-1:
      q+=[j]

g=0
y=0
i=-1
while i+1<len(s):
  c=s[i+1]
  i+=1
  u=ord(c)-ord("a")
  if e1[y][u]!=-1:
    y=e1[y][u]
  else:
    y=e2[y]
    while y!=0 and e1[y][u]==-1:
      y=e2[y]
    if e1[y][u]!=-1:
      y=e1[y][u]
  if x[y]:
    g+=1
    y=0

print(g)