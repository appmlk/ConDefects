from collections import deque
N,M = map(int,input().split())
edge = []
for _ in range(M):
    a,b,w = map(int,input().split())
    a-=1
    b-=1
    edge.append((a,b,w))

def const_color(G):
    c = [-1] * N
    q = deque()
    for st in range(N):
        if c[st] == -1:
            q.append(st)
            c[st] = 0
            while q:
                now = q.popleft()
                for nx in G[now]:
                    if c[nx] == -1:
                        c[nx] = c[now] ^ 1
                        q.append(nx)
    return c
def is_binary(G,edge):
    c = const_color(G)

    for a,b in edge:
        if c[a] != c[b]:
            pass
        else:
            return False
    return True

def const_G(x):
    G = [[] for _ in range(N)]
    new_edge = []
    for a,b,w in edge:
        if w < x:
            G[a].append(b)
            G[b].append(a)
            new_edge.append((a,b))

    return G,new_edge

def is_ok(x):
    G,new_edge = const_G(x)
    return is_binary(G,new_edge)




INF = float('inf')
G0,edge0 = const_G(INF)
e0 = [[] for _ in range(N)]
ans = INF
for a,b,w in edge:
    e0[a].append(w)
    e0[b].append(w)
for i in range(N):
    e0[i].sort()
    if len(e0[i]) >= 2:
        path_length = e0[i][0] + e0[i][1]
        ans = min(ans,path_length)
if is_binary(G0,edge0):
    print(ans)
else:
    ok = 0
    ng = 10 ** 9 + 1
    while (ng-ok) > 1:
        x = (ng + ok) // 2
        if is_ok(x):
            ok = x
        else:
            ng = x
    ans = min(ok,ans)

    print(ans)