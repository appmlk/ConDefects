import heapq
N,M=map(int,input().split())
G=[[] for i in range(N+1)]
H=[[0,0]]
for i in range(M):
    a,b,c=map(int,input().split())
    G[a].append(i+1)
    G[b].append(i+1)
    H.append([a+b,c])
    
Q=[]
inf=10**9+10
D=[inf for i in range(N+1)]
memo=[-1 for i in range(N+1)]
D[1]=0
heapq.heappush(Q,[0,1])
while Q:
    cost,cur=heapq.heappop(Q)
    if D[cur]<cost:
        continue
    for i in G[cur]:
        dst=H[i][0]-cur
        w=H[i][1]
        if D[dst]>D[cur]+w:
            D[dst]=D[cur]+w
            memo[dst]=i
            heapq.heappush(Q,[D[dst],dst])
            

ans=[]
for i in range(N+1):
    if memo[i]!=-1:
        ans.append(memo[i])
print(*ans)