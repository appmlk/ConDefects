s=[list(map(int,input().split())) for i in range(4)]
for i in range(4):
  cn=0
  for j in range(1,4):
    a,b=0,10
    for k in range(1,4):
      if k!=j:
        a=k;b=min(b,k)
    x=[s[(i+a)%4][0]-s[(i+b)%4][0],s[(i+a)%4][1]-s[(i+b)%4][1]]#0=x[1]*x-x[0]*y+c
    c=x[0]*s[(i+b)%4][1]-x[1]*s[(i+b)%4][0]
    if (x[1]*s[i][0]-x[0]*s[i][1]+c)*(x[1]*s[(i+j)%4][0]-x[0]*s[(i+j)%4][1]+c)>0:
      cn+=1
  if cn==3:
    exit(print("No"))
print("Yes")
  