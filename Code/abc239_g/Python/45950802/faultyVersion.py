from atcoder.maxflow import MFGraph

INF = 1 << 60
N, M = map(int, input().split())
g = MFGraph(N * 2)
for _ in range(M):
    A, B = map(lambda x: int(x)-1, input().split())
    g.add_edge(A+N, B, INF)  # A_out -> B_in
    g.add_edge(B+N, A, INF)  # B_out -> A_in

C = list(map(int, input().split()))
for i, c in enumerate(C):
    cap = INF if i in [0, N-1] else c
    g.add_edge(i, i+N, cap)  # i_in -> i_out

ans = g.flow(0, N-1)
mc = g.min_cut(0)
vs = []
for i in range(N):
    if mc[i] and not mc[i+1]:
        vs.append(i+1)

print(ans)
print(len(vs))
print(*vs)
