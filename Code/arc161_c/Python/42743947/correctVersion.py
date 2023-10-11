import sys
sys.setrecursionlimit(10**7)
def dfs(u,v):
    seen[v]=True
    temp=0
    X[v]=u
    for next_v in G[v]:
        if seen[next_v]:
            continue
        temp+=1
        dfs(v,next_v)
        if ans[next_v] in set(['',S[v]]) :
            count[v]+=1
    if temp>0:
        if (count[v]>len(G[v])//2):
            pass
        elif (count[v]==len(G[v])//2) & (u!=v) & (ans[u] in set(['',S[v]])):
            ans[u]=S[v]
        else:
            ans[v]='-1'
    else:
        if ans[u] in set(['',S[v]]):
            ans[u]=S[v]
        else:
            ans[v]='-1'
        
T=int(input())

for _ in range(T):
    N=int(input())
    G=[[] for _ in range(N)]
    for _ in range(N-1):
        A,B=map(int,input().split())
        A-=1
        B-=1
        G[A].append(B)
        G[B].append(A)
    S=input()
    ans=['']*N
    count=[0]*N
    seen=[False]*N
    check=False
    X=[0]*N
    dfs(0,0)
    for i in range(N):
        if ans[i]=='-1':
            check=True
            break
        elif ans[i]=='':
            ans[i]=S[X[i]]
    if check:
        print('-1')
    else:
        print(''.join(ans))
    
    