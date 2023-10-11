from collections import deque


T=int(input())
for _ in range(T):
    N=int(input())
    G=[[] for i in range(N)]
    for i in range(N-1):
        a,b=map(int,input().split())
        G[a-1].append(b-1)
        G[b-1].append(a-1)
    S=input()
    ans=[""]*N
    Ecnt=[0]*N
    todo=deque()
    for i in range(N):
        Ecnt[i]=len(G[i])
        if Ecnt[i]==1:
            todo.append(i)
    flag=[0]*N
    while todo:
        node = todo.popleft()
        flag[node]=1
        x=S[node]
        b=0
        w=0
        # print(node,ans)
        if Ecnt[node]==0:
            if ans[node]=="":
                ans[node]="B"
            for nextnode in G[node]:
                if ans[nextnode]!="":
                    if ans[nextnode]=="B":
                        b+=1
                    else:
                        w+=1
                else:
                    if flag[nextnode]==1:
                        ans[nextnode]=x
                        if x=="B":
                            b+=1
                        else:
                            w+=1                        
            if b>len(G[node])//2:
                if x=="B":
                    continue
                else:
                    break
            elif w>len(G[node])//2:
                if x=="W":
                    continue
                else:
                    break
        for nextnode in G[node]:
            if ans[nextnode]!="":
                if ans[nextnode]=="B":
                    b+=1
                else:
                    w+=1
            else:
                if flag[nextnode]==1:
                    ans[nextnode]=x
                    if x=="B":
                        b+=1
                    else:
                        w+=1                        
            Ecnt[nextnode]-=1
            if Ecnt[nextnode]==1:
                todo.append(nextnode)

        if b>len(G[node])//2:
            if x=="B":
                continue
            else:
                break
        elif w>len(G[node])//2:
            if x=="W":
                continue
            else:
                break
        elif b==w:
            for nextnode in G[node]:
                if ans[nextnode]=="":
                    ans[nextnode]=x
                    if x=="B":
                        b+=1
                    else:
                        w+=1                        
                    
            if b>len(G[node])//2:
                if x=="B":
                    continue
                else:
                    break
            elif w>len(G[node])//2:

                if x=="W":
                    continue
                else:
                    break
        else:
            break
    else:
        print("".join(ans))
        continue
    print(-1)
