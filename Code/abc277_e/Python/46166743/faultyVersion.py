N,M,K = map(int,input().split())
UVA = [list(map(int,input().split())) for _ in range(M)]
S = set(map(int,input().split()))
E = [[] for _ in range(N+1)]
INF = 10 ** 6
for i,(u,v,a) in enumerate(UVA,start = 1):
    E[u].append((v,a,i))
    E[v].append((u,a,i))
C = [[INF,INF] for _ in range(N+1)]
C[1][1] = 0
q = [(1,1)]
cnt = 0
while q:
    cnt += 1
    q2 = []
    while q:
        u,a = q.pop(-1)
        for v,b,i in E[u]:
            if a == b:
                if C[v][a] == INF:
                    C[v][a] = cnt
                    q2.append((v,a))
            else:
                if u in S:
                    if C[v][b] == INF:
                        C[v][b] = cnt
                        q2.append((v, b))
    q,q2 = q2,q
if min(C[N]) == N:
    print(-1)
else:
    print(min(C[N]))

