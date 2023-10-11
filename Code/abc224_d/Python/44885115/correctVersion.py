from collections import deque
import heapq
m=int(input())
v=[tuple(map(int,input().split())) for i in range(m)]
p=list(map(int,input().split()))
p=list(set(p)^set(range(1,10)))+p
p=[i-1 for i in p]
p=tuple([p.index(i) for i in range(9)])
g={i:[] for i in range(9)}
for i in range(m):
    a,b=v[i]
    g[a-1]+=[b-1]
    g[b-1]+=[a-1]

start=p
visited=set()
visited.add(p)
dist={}
dist[p]=0
que=deque([start])

while len(que):
    v=que.popleft()
    nvs=[]
    ind=v.index(0)
    for i in g[ind]:
        temp=list(v)
        temp[i],temp[ind]=temp[ind],temp[i]
        nvs+=[tuple(temp)]
    for nv in nvs:
        if nv in visited:
            continue
        visited.add(nv)
        que.append(nv)
        dist[nv]=dist[v]+1
if (1,2,3,4,5,6,7,8,0) in dist:
	  print(dist[(1,2,3,4,5,6,7,8,0)])
else:
	  print(-1)