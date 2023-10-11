from collections import deque

N,M = map(int,input().split())
XY = list(set([tuple(map(int,input().split())) for _ in range(M)]))
to = [[] for _ in range(N+1)]
deg = [0 for _ in range(N+1)]
for X,Y in XY:
    if X == Y: continue
    to[X].append(Y)
    deg[Y] += 1

dq = deque([i for i in range(1,N+1) if deg[i] == 0])

P = []
while dq:
    x = dq.popleft()
    P.append(x)
    for to_x in to[x]:
        deg[to_x] -= 1
        if deg[to_x] == 0:
            dq.append(to_x)
    if len(dq) >= 2:
        print("No"); exit()

if len(P) != N:
    print("No"); exit()

ans = [0 for _ in range(N+1)]
for i in range(N):
    ans[P[i]] = i+1

print("Yes")
print(*ans[1:])
           
            
