T = int(input())
for _ in range(T):
    N,K = map(int,input().split())
    P = [0,0]+list(map(int,input().split()))
    A = [0]+list(map(int,input().split()))
    G = {i:[] for i in range(1,N+1)}
    for i in range(2,N+1):
        G[P[i]].append(i)
    lack = {i:[True,0,set()] for i in range(1,N+1)}
    B = set(range(K))
    def dfs(x):
        if A[x]==K:
            lack[x][0] = False
        if len(G[x])==0:
            if A[x]>=0:
                lack[x][2] = B-set([A[x]])
            else:
                lack[x][1] += 1
                lack[x][2] = B
        else:
            lack[x][2] = B
            for y in G[x]:
                dfs(y)
                lack[x][0] = lack[x][0] and lack[y][0]
                lack[x][1] += lack[y][1]
                lack[x][2] = lack[x][2]&lack[y][2]
            if A[x]==-1:
                lack[x][1] += 1
            else:
                lack[x][2] = lack[x][2]-set([A[x]])
    dfs(1)
    flag = "Bob"
    for i in range(1,N+1):
        if not lack[i][0]:continue
        if (len(lack[i][2])==0 and lack[i][1]<=1) or (len(lack[i][2])==1 and lack[i][1]==1):
            flag = "Alice"
            break
    print(flag)