import heapq
N,M=map(int,input().split())
graph=[[] for _ in range(N)]
reversed_graph=[set() for _ in range(N)]
pairs=set()

que=set(range(N))
for _ in range(M):
    u,v=map(int,input().split())
    pair=(u-1,v-1)
    if pair in pairs:
        continue
    graph[u-1].append(v-1)
    reversed_graph[v-1].add(u-1)
    if v-1 in que:
        que.remove(v-1)

que=list(que)
heapq.heapify(que)

answers=[]
used=[0 for _ in range(N)]
while que:
    now=heapq.heappop(que)
    answers.append(now+1)
    for to in graph[now]:
        if len(reversed_graph[to])==0:
            heapq.heappush(que,to)
    
if len(answers)==N:
    print(*answers)
else:
    print(-1)