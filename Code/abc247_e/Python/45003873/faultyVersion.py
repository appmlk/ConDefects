n,y,x=map(int,input().split())
a=list(map(int,input().split()))
ans=0
def calc(B):
  next_x=[n+1]*len(B)
  next_y=[n+1]*len(B)
  for i in range(len(B)):
    if B[i]==x:
      next_x[i]=i
    if B[i]==y:
      next_y[i]=i
  for i in range(len(B)-1,0,-1):
    if next_y[i]!=n+1:
      if next_y[i-1]==n+1:
        next_y[i-1]=next_y[i]
    if next_x[i]!=n+1:
      if next_x[i-1]==n+1:
        next_x[i-1]=next_x[i]
  r=0
  for i in range(len(B)):
    k=max(next_x[i],next_y[i])
    if k!=n+1:
      r+=n-k
  return r
li=[]
inner=[]
for i in range(n):
  if x<=a[i]<=y:
    inner.append(a[i])
  else:
    if inner:
      li.append(inner)
      inner=[]
if inner:
  li.append(inner)
for k in li:
  ans+=calc(k)
print(ans)