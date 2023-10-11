x1,y1,x2,y2 = map(int,input().split())
if ((abs(x1-x2)==1 or abs(x1-x2)==3) and (abs(y1-y2)==1 or abs(y1-y2)==3)) or ((abs(x1-x2)==2 and abs(y1-y2)==4) or (abs(x1-x2)==4 and abs(y1-y2)==2)) or  ((abs(x1-x2)==0 and (abs(y1-y2)==2 or abs(y1-y2)==4)) or  ((abs(x1-x2)==2 or abs(x1-x2)==4) and abs(y1-y2)==0)):
  print("Yes")
else:
  print("No")