n,a,b,c,d=map(int,input().split())
if abs(b-c)<=1:
  if b==c==0 and a<n-1 and d<n-1:
    print("No")
  else:
    print("Yes")
else:
  print("No")