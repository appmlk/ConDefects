N,Q=map(int,input().split())
connects=[[-1,-1] for _ in range(N)]

for _ in range(Q):
    query=list(map(int,input().split()))
    t=query[0]
    x=query[1]-1
    if t==1:
        y=query[2]-1
        connects[x][1]=y
        connects[y][0]=x
    elif t==2:
        y=query[2]-1
        connects[x][1]=-1
        connects[y][0]=-1
    else:
        front=[]
        back=[]
        now=x
        while connects[now][0]!=-1:
            now=connects[now][0]
            front.append(now)
        now=x
        while connects[now][1]!=-1:
            now=connects[now][1]
            back.append(now+1)
        answer=front[::-1]+[x+1]+back
        print(len(answer),*answer)