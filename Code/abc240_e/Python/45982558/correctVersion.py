import sys
sys.setrecursionlimit(10**8)
N=int(input())
graph=[[] for _ in range(N)]
inf=10**18

for _ in range(N-1):
    u,v=map(int,input().split())
    graph[u-1].append(v-1)
    graph[v-1].append(u-1)

visited=[0 for _ in range(N)]
answers=[[inf,0] for _ in range(N)]
number=1
def dfs(v):
    global number
    if len(graph[v])==1 and visited[graph[v][0]]:
        answers[v]=[number,number]
        number+=1
    for to in graph[v]:
        if visited[to]:
            continue
        visited[to]=1
        dfs(to)
        answers[v][0]=min(answers[v][0],answers[to][0])
        answers[v][1]=max(answers[v][1],answers[to][1])

visited[0]=1
dfs(0)

for answer in answers:
    print(*answer)