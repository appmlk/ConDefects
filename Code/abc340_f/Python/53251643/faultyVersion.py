def extgcd(a,b,c):
    if a==0:
        if b==0 or c%b:return (0,0,0)
        else:return(1,0,c//b)
    if b==0:
        if a==0 or c%a:return(0,0,0)
        else:return(1,c//a,0)
    if b<0:
        a=-a
        b=-b
        c=-c
    x1,y1=a%b,c%b
    x2,y2=b-x1,b-y1
    if x1<x2:
        x1,x2=x2,x1
        y1,y2=y2,y1
    
    while x2:x1,y1,x2,y2=x2,y2,x1%x2,(y1-y2*(x1//x2))%b
    if (c-a*y1//x1)%b:return (0,0,0)
    return (1,y1//x1,(c-a*y1//x1)//b)
    
x,y=map(int,input().split())

f,a,b=extgcd(x,-y,2)

assert max(abs(a),abs(b))<=10**18
if f:print(b,a)
else:print(-1)