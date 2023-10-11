n=int(input())
s=list(input())
t=list(input())
L=[False for i in range(n)]
ans=["0" for i in range(n)]
miss=0
for i in range(n):
  if s[i]==t[i]:
    ans[i]=s[i]
    L[i]=True
  else:
    miss+=1
if miss%2==1:
  print(-1)
else:
  SU=0
  TU=0
  for i in range(n):
    if L[i]==False:
      if s[i]=="1":
        SU+=1
      else:
        TU+=1
  num=abs(SU-TU)//2
  for i in range(1,n+1):
    if num==0:
      break
    i=n-i
    if SU>TU:
      if L[i]==False and t[i]=="0":
        ans[i]="1"
        num-=1
    else:
      if L[i]==False and s[i]=="0":
        ans[i]="1"
        num-=1
  print("".join(ans))