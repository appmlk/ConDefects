INF=pow(2,61)-1
N=int(input())
dot=[tuple(map(int,input().split())) for i in range(N)]
dot.append(dot[0])
M=int(input())
move=[tuple(map(int,input().split())) for i in range(M)]
line=[]
for i in range(N):
    maxi=-INF
    a=0
    b=0
    for u,v in move:
        x1=dot[i][0]+u
        y1=dot[i][1]+v
        x2=dot[i+1][0]+u
        y2=dot[i+1][1]+v
        a=(y2-y1)
        b=-(x2-x1)
        c=y2*(x2-x1)-x2*(y2-y1)
        maxi=max(maxi,c)
    line.append((a,b,maxi))
Q=int(input())
for i in range(Q):
    a,b=map(int,input().split())
    ans="Yes"
    for A,B,C in line:
        if A*a+B*b+C>0:
            ans="No"
            break
    print(ans)