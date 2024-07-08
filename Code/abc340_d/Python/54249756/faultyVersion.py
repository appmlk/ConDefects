import heapq

N = int(input())
Vs = list()
Es = list()
inf = 10 **10
for i in range(1, N):
    Vs.append([i, inf])
    Ai, Bi, Xi = map(int, input().split())
    Es.append([i+i, Ai, Xi, Bi])
Vs.append([N, inf])
Vs[0][1] = 0

U = []
heapq.heappush(U, (Vs[0][1], Vs[0]))
now = heapq.heappop(U)[1]
while now[0] != N:
    i = Es[now[0]-1][0]
    A = Es[now[0]-1][1]
    X = Es[now[0]-1][2]
    B = Es[now[0]-1][3]
    if Vs[now[0]][1] == inf:
        Vs[now[0]][1] = now[1] + A
        heapq.heappush(U, (Vs[now[0]][1], Vs[now[0]]))
    elif Vs[now[0]][1] > now[1] + A:
        Vs[now[0]][1] = now[1] + A
    if Vs[X-1][1] == inf:
        Vs[X-1][1] = now[1] + B
        heapq.heappush(U, (Vs[X-1][1], Vs[X-1]))
    elif Vs[X-1][1] > now[1] + B:
        Vs[X-1][1] = now[1] + B
    now = heapq.heappop(U)[1]

print(Vs[N-1][1])
    
    





