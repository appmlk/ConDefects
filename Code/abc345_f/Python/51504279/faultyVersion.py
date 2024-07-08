import sys
sys.setrecursionlimit(10**7)

N, M, K = map(int, input().split())
G = [[] for _ in range(N)]
for i in range(M):
    u, v = map(int, input().split())
    u -= 1
    v -= 1
    G[u].append((v, i+1))
    G[v].append((u, i+1))

vis = [0]*N
lig = [0]*N
cnt = 0
ans = []
flg = False
def dfs(now, pre=-1, edge=-1):
    global cnt
    vis[now] = 1
    for nxt, e in G[now]:
        if vis[nxt] == 0:
            vis[nxt] = 1
            dfs(nxt, now, e)
    if pre != -1 and lig[now] == 0 and cnt < K:
        lig[now] = 1
        cnt += 1
        if lig[pre] == 0:
            lig[pre] = 1
            cnt += 1
        else:
            lig[pre] = 0
            cnt -= 1
        ans.append(edge)
        if cnt == K:
            global flg
            flg = True
#    print(now, lig)

for i in range(N):
    if vis[i] == 0 and cnt < K:
        dfs(i)

if flg:
    print('Yes')
    print(len(ans))
    print(*ans)
else:
    print('No')
