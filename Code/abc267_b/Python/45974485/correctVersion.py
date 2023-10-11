import sys
S=list(input())
L=[0,0,0,0,0,0,0]
for i in range(10):
  if S[i]=="1":
    if i+1 in [1,5]:
      L[3]=1
    if i+1 in [2,8]:
      L[2]=1
    if i+1 in [3,9]:
      L[4]=1
    if i+1==4:
      L[1]=1
    if i+1==6:
      L[5]=1
    if i+1==7:
      L[0]=1
    if i+1==10:
      L[6]=1
if S[0]=="1":
  print("No")
  sys.exit()
flag=0
for i in range(7):
  if flag==0 and L[i]==1:
    flag=1
  if flag==1 and L[i]==0:
    flag=2
  if flag==2 and L[i]==1:
    flag=3
if flag==3:
  print("Yes")
else:
  print("No")