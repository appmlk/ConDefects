"""
3 2
1 2 2 1
1 3 -1 -2
"""
N,M=map(int,input().split())
G=[[]for i in range(N+1)]
for i in range(M):
    a,b,x,y=map(int,input().split())
    G[a].append([b,x,y])
    G[b].append([a,x,y])

visited=[False]*(N+1)
visited[1]=True
listx=[[0]*2 for i in range(N+1)]
set1={1}
while len(set1)>0:
    set2=set()
    for i in set1:
        for num,x,y in G[i]:
            if visited[num]==False:
                set2.add(num)
                visited[num]=True
                listx[num][0]=listx[i][0]+x
                listx[num][1]=listx[i][1]+y
    set1=set2.copy()

for i in range(1,N+1):
    if visited[i]==False:
        print("undecidable")
    else:
        print(listx[i][0],listx[i][1])
            