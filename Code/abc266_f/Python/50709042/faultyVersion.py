from collections import deque

N = int(input())
G = [[] for _ in range(N)]
dist = [0 for _ in range(N)]
for _ in range(N):
    u, v = map(int, input().split())
    u -= 1
    v -= 1
    G[u].append(v)
    G[v].append(u)
    dist[u] += 1
    dist[v] += 1
que = deque([])
for i in range(N):
    if dist[i] == 1:
        que.append(i)
seen = [False for _ in range(N)]
while len(que):
    v = que.popleft()
    dist[v] = 0
    if seen[v]:
        continue
    seen[v] = True
    for nv in G[v]:
        if not seen[nv]:
            dist[nv] -= 1
            if dist[nv] == 1:
                que.append(nv)
K = [-1 for _ in range(N)]
for i in range(N):
    if dist[i] != 0:
        que = deque([])
        que.append(i)
        flag = False
        while len(que):
            vi = que.popleft()
            for ni in G[vi]:
                if dist[ni] == 0 and K[ni] == -1:
                    K[ni] = i
                    flag = True
                    que.append(ni)
        if flag:
            K[i] = i
Q = int(input())
for _ in range(Q):
    u, v = map(int,input().split())
    u -= 1
    v -= 1
    if K[u] == K[v]:
        print("Yes")
    else:
        print("No")