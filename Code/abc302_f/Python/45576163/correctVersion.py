N, M = map(int, input().split())
graph = [[] for _ in range(N+M+1)]

for i in range(N):
    A = int(input())
    S = set(map(int, input().split()))
    for j in S:
        graph[j].append(i+M+1)
        graph[i+M+1].append(j)

from collections import deque
q = deque([])
q.append(1)
D = [1<<61] * (N+M+1)
D[1] = 0

while q:
    u = q.popleft()
    d = D[u]
    for v in graph[u]:
        if D[v] <= d + 1:
            continue
        if v == M:
            print(d//2)
            exit()
        D[v] = d + 1
        q.append(v)
        
print(-1)