X,Y=map(int,input().split());
if X>Y:#下り:
  j=X-Y;
  if j<=3:
    print("Yes");
  else:
    print("No");
else:
  k=Y-X;
  if k>=2:
    print("Yes");
  else:
    print("No")