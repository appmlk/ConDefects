import collections
T=int(input())
for t in range(T):
    N,M=map(int,input().split())
    C=list(map(int,input().split()))
    G=[[] for i in range(N)]
    for i in range(M):
        u,v=map(int,input().split())
        u-=1
        v-=1
        G[u].append(v)
        G[v].append(u)
        
    inf=10**9+10
    D=[[inf]*N for i in range(N)]
    D[0][N-1]=0
    Q=collections.deque()
    Q.append((0,N-1,0))
    while Q:
        taka,aoki,cost=Q.popleft()
        for dst_t in G[taka]:
            for dst_a in G[aoki]:
                if D[dst_t][dst_a]==inf and C[dst_t]!=C[dst_a]:
                    D[dst_t][dst_a]=cost+1
                    Q.appendleft((dst_t,dst_a,cost+1))
                    
    ans=D[N-1][0]
    if ans==inf:
        ans=-1
    print(ans)
                    
    