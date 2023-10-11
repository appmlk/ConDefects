# 0を超頂点として持つ
# 考えられる経路としては、
# 0を経由しない状態での経路、0経由する経路の2つ
# 0を経由しない場合は、1を始点としてBFSすれば良い
# 0を経由する場合を考える
# any -> 0へ行く場合をコスト1として、0->anyをコスト0と考える
# 0 -> iを考える。
# iが最短経路上に行くときは、1 -> 0 -> iのときのコストと1 -> iのときのコストを比べる
# このときにcost(1 -> i) > cost(1 -> 0 -> i)だったらcost(1->0->i->N)にすればいい
# そうでなければcost(1 -> i)を採用する
# 1 -> Nへいけない場合
# この場合は、頂点0を経由しないと無理
# 1->0->i->Nよりi->Nまでの最短経路が必要
# 1からの最短距離をNからの最短距離が必要
# 1->i->0->Nもあった(指摘を受けた)
# 1->N, 1->0->i->N, 1->i->0->N
# 1->i->N

from collections import deque

def BFS(start):
    deq = deque([(start, 0)])
    dist = [INF] * (N+1)

    while deq:
        node, cost = deq.popleft()

        if dist[node] != INF:
            continue

        dist[node] = cost

        for nx in town[node]:
            deq.append((nx, cost + 1))

    return dist


N, M = map(int, input().split())
town = [[] for i in range(N+1)]
INF = 1 << 60

for i in range(M):
    u, v = map(int, input().split())
    town[v].append(u)
    if u != 0:
        town[u].append(v)

dist1 = BFS(1)
distN = BFS(N)

ans = []
# to := 頂点0の向かう先
for to in range(1, N+1):
    tmp = min(dist1[N], dist1[0] + distN[to], dist1[to] + distN[0], dist1[0] + distN[0])
    ans.append(tmp if tmp != INF else -1)

print(*ans)