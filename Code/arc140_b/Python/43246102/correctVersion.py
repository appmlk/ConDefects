n=int(input())
s=list(map(str,input()))
R=[]
for i in range(1,n-1):
  if s[i]=="R":
    R.append(i)
ARCnum=0
ARClen=[]
for k in R:
  le=0
  nu=0
  for i in range(1,min(k+1,n-k)):
    if s[k-i]=="A" and s[k+i]=="C":
      le+=1
      nu=1
    else:
      ARCnum+=nu
      if le>0:
        ARClen.append(le-2)
      break
    if i==min(k,n-k-1):
      ARCnum+=nu
      if le>0:
        ARClen.append(le-2)
      break
ARClen.sort()
su=0
i=0
odd=0
even=0
while su<=0 and i<len(ARClen):
  su+=ARClen[i]
  if ARClen[i]==-1:
    even+=1
  i+=1
if su<=0:
  print(2*ARCnum+sum(ARClen))
else:
  print(2*ARCnum)