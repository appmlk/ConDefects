MOD=998244353
n,m=map(int,input().split())
graph=[[] for _ in range(n)]
for _ in range(m):
    u,v=map(lambda x:int(x)-1,input().split()) 
    graph[u].append(v)
    graph[v].append(u)
visited=[0]*n
from collections import deque
def dfs(s):
    if visited[s]:
        return []
    visited[s]=1
    q=deque([s])
    link=[s]
    while q:
        v=q.popleft()
        for u in graph[v]:
            if visited[u]:
                continue
            visited[u]=1
            q.append(u)
            link.append(u)
    return link

ans=1
for i in range(n):
    link=dfs(i)
    num_edge=0
    if len(link)<1:
        continue
    for l in link:
        num_edge+=len(graph[l])
    if num_edge==len(link)*2:
        ans*=2
        ans%=MOD
    else:
        ans=0
print(ans)