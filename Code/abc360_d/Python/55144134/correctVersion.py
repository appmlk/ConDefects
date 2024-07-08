n,t=[int(x) for x in input().split()]
s=input()
x=[int(x) for x in input().split()]

x0=[]
x1=[]
for i,s1 in enumerate(s):
  if(s1=="0"):
    x0.append(x[i])
  else:
    x1.append(x[i])
x0.sort()
x1.sort()
nx0=len(x0)
nx1=len(x1)
i=0
j=0
ans=0
#print(x0)
#print(x1)
for y in x1:
  while(i<(nx0) and x0[i]<y):
    i+=1
  while(j<(nx0) and x0[j]<(y+2*t+0.1)):
    j+=1
  ans+=(j-i)
  #print(ans,j,i)
print(ans)
  