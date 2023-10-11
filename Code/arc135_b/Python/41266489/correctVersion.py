N=int(input())
S=list(map(int,input().split()))
a=[0]*(N+2)
mini=0
minj=0
mink=0
for i in range(3,N+2):
  a[i]=a[i-3]+S[i-2]-S[i-3]
  if i%3==0:
    if mini>a[i]:
      mini=a[i]
  elif i%3==1:
    if minj>a[i]:
      minj=a[i]
  else:
    if mink>a[i]:
      mink=a[i]
if -mini-minj-mink<=S[0]:
  print("Yes")
  mini=-mini
  minj=-minj
  mink=S[0]-mini-minj
  for i in range(N+2):
    if i%3==0:
      print(mini+a[i],end=" ")
    elif i%3==1:
      print(minj+a[i],end=" ")
    else:
      print(mink+a[i],end=" ")
else:
  print("No")