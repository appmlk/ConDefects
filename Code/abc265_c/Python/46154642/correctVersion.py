h,w=map(int,input().split())
g=[]
for i in range(h):
    tmp=input()
    g.append(list(tmp))
visited=[[False]*w for _ in range(h)]
i,j=0,0
for k in range(10**6):
    if visited[i][j]==True:
        print(-1)
        exit()
    if g[i][j]=="U":
        if i==0:
            print(i+1,j+1)
            exit()
        else:
            visited[i][j]=True
            i-=1
    if g[i][j]=="D":
        if i==h-1:
            print(i+1,j+1)
            exit()
        else:
            visited[i][j]=True
            i+=1
    if g[i][j]=="L":
        if j==0:
            print(i+1,j+1)
            exit()
        else:
            visited[i][j]=True
            j-=1
    if g[i][j]=="R":
        if j==w-1:
            print(i+1,j+1)
            exit()
        else:
            visited[i][j]=True
            j+=1