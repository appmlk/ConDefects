import sys
sys.setrecursionlimit(9999)

N,M=map(int,input().split())
con=[[] for _ in range(N)]
for i in range(M):
    A,B,V=map(str,input().split())
    A,B,V=int(A)-1,int(B)-1,1 if V=="(" else -1
    con[A].append((B,V))#(終点、±)のタプル

inc,dec=False,False
go  =[False for _ in range(N)]
back=[False for _ in range(N)]
pm  =[set() for _ in range(N)]
roop=[0 for _ in range(N)]
def DFS(i,dfs_pm):
    global inc,dec
    if go[i] and not back[i]:
        if roop[i]<dfs_pm : inc=True
        if dfs_pm<roop[i] : dec=True
    else:
        if dfs_pm not in pm[i]:
            pm[i].add(dfs_pm)
            go[i]=True
            back[i]=False
            roop[i]=dfs_pm
            for g,v in con[i]:
                DFS(g,dfs_pm+v)
            back[i]=True
DFS(0,0)