N,M=map(int,input().split())
G=[[] for _ in range(N)]
for _ in range(M):
    u,v=map(int,input().split())
    G[u-1].append(v-1)
    G[v-1].append(u-1)
seen=set()
cnt=0
for i in range(N):
    if(i not in seen):
        stack=[i]
        cnt+=1
        while stack:
            x=stack.pop()
            if(x in seen):
                continue
            seen.add(x)
            for j in G[i]:
                if (j not in seen):
                    stack.append(j)
print(cnt)