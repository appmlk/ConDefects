

from heapq import heappop, heappush
from typing import List, Tuple


INF = int(4e18)


def abc245g(
    n: int, edges: List[Tuple[int, int, int]], colors: List[int], criticals: List[int]
) -> List[int]:
    adjList = [[] for _ in range(n)]
    for u, v, w in edges:
        adjList[u].append((v, w))
        adjList[v].append((u, w))

    dist1, dist2 = [INF] * n, [INF] * n
    fromColor1, fromColor2 = [-1] * n, [-1] * n
    pq = []
    for v in criticals:
        dist1[v] = 0
        fromColor1[v] = colors[v]
        heappush(pq, (0, v, colors[v]))

    while pq:
        curDist, cur, curColor = heappop(pq)
        if dist1[cur] != curDist and dist2[cur] != curDist:
            continue
        for next, weight in adjList[cur]:
            nextDist = curDist + weight
            if nextDist < dist1[next]:
                if fromColor1[next] != curColor:
                    dist2[next] = dist1[next]
                    fromColor2[next] = fromColor1[next]
                dist1[next] = nextDist
                fromColor1[next] = curColor
                heappush(pq, (dist1[next], next, curColor))
            elif dist1[next] < nextDist < dist2[next] and fromColor1[next] != curColor:
                dist2[next] = nextDist
                fromColor2[next] = curColor
                heappush(pq, (dist2[next], next, curColor))

    res = [dist1[v] if fromColor1[v] != c else dist2[v] for v, c in enumerate(colors)]

    for i in range(n):
        if res[i] == INF:
            res[i] = -1
    return res


if __name__ == "__main__":
    import sys

    input = lambda: sys.stdin.readline().rstrip("\r\n")

    N, M, K, L = map(int, input().split())
    colors = [v - 1 for v in map(int, input().split())]
    criticals = [v - 1 for v in map(int, input().split())]
    edges = []
    for _ in range(M):
        u, v, w = map(int, input().split())
        edges.append((u - 1, v - 1, w))

    print(*abc245g(N, edges, colors, criticals))
