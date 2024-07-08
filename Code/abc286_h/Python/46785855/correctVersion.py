n=int(input())
p=[tuple(map(int,input().split())) for i in range(n)]
l=[p[i-1]+p[i] for i in range(n)]
s=tuple(map(int,input().split()))
g=tuple(map(int,input().split()))
p+=[s]
p+=[g]
p.sort()

def calc(a,c,b):
  ax,ay=a
  bx,by=b
  cx,cy=c
  return (ax-cx)*(by-cy)-(ay-cy)*(bx-cx)

c1=[]
for i in range(len(p)):
  while len(c1)>=2 and calc(p[i],c1[-1],c1[-2])>=0:
    c1.pop()
  c1+=[p[i]]

c2=[]
for i in range(len(p)):
  while len(c2)>=2 and calc(p[i],c2[-1],c2[-2])<=0:
    c2.pop()
  c2+=[p[i]]

def dist(a,b):
  ax,ay=a
  bx,by=b
  return ((ax-bx)**2+(ay-by)**2)**0.5

c=c1[:len(c1)-1]+c2[::-1]

if (s not in c) or (g not in c):
  print(dist(s,g))
  exit()

d=[0]*len(c)
for i in range(len(c)-1):
  p1=c[i]
  p2=c[i+1]
  d[i]=dist(p1,p2)
  d[i]+=d[i-1]

l=min(c.index(s),c.index(g))
r=max(c.index(s),c.index(g))

print(min(d[-2]-d[r-1]+d[l-1],d[r-1]-d[l-1]))