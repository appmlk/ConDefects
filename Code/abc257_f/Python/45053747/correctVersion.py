from collections import deque
import sys
INF = 10**9
def main():
    input = sys.stdin.readline
    N, M = map(int, input().split())
    G = [[] for _ in range(N + 1)]
    T = []
    for _ in range(M):
        u, v = map(int, input().split())
        u = u - 1 if u > 0 else N
        v = v - 1
        G[u].append(v)
        G[v].append(u)
        if u == N:
            T.append(v)
    D1 = bfs(G, 0)
    D2 = bfs(G, N - 1)
    minT = min(D2[t] for t in T) if T else INF
    A = []
    for i in range(N):
        ans = INF
        ans = min(ans, D1[N - 1])
        ans = min(ans, D1[N] + D2[i])
        ans = min(ans, D1[i] + 1 + minT)
        ans = min(ans, D1[N] + 1 + minT)
        A.append(ans if ans < INF else -1)
    print(*A)

def bfs(G, start=0):
    D = [INF] * len(G)
    D[start] = 0
    q = deque([start])
    while q:
        v = q.popleft()
        nd = D[v] + 1
        for nv in G[v]:
            if D[nv] < INF: continue
            D[nv] = nd
            q.append(nv)
    return D

if __name__ == '__main__':
    main()
