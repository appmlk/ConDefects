import sys
n,m = map(int, sys.stdin.readline().split())
mp = map(int, sys.stdin.read().split())
abc = list(zip(mp,mp, mp))

graph = [[] for i in range(n+1)]

for a,b,c in abc:
    graph[a].append((b,c))
    graph[b].append((a,c))


ok = float("inf")
# ng = 0

minng = 1000000007
for i in range(1, n+1):
    graph[i].sort(key=lambda x: x[1])
    if ok > graph[i][0][1]:
        ok = graph[i][0][1]

    if len(graph[i]) >= 2:
        temp = graph[i][0][1] + graph[i][1][1]
        if minng > temp:
            minng = temp

ng = minng + 1

def bipw(graph, w):
    # 二部グラフであるかどうか判定する
    # 二部グラフであるならば、同じ色を結ぶ最短パスの最小値がw以上か判定する

    # graph[v]:= [(nv, c)]
    n = len(graph) - 1

    # -1: 未訪問, 0or1: グループ分け
    visited = [-1 for i in range(n+1)]

    for i in range(1, n+1):
        if visited[i] != -1:
            continue

        visited[i] = 0
        que = [i]
        while que:
            nque = []
            while que:
                v = que.pop()
                for nv, c in graph[v]:
                    if c >= w:
                        break
                    if visited[nv] == visited[v]:
                        return False
                    if visited[nv] == -1:
                        visited[nv] = visited[v] ^ 1
                        nque.append(nv)
                    else:
                        continue
            que = nque

    return True


def solve(mid):
    global n
    global graph

    if bipw(graph, mid):
        return True
    else:
        return False

while abs(ok-ng) > 1:
    mid = (ok+ng)//2
    # print(ok,ng)
    if solve(mid):
        ok = mid
    else:
        ng = mid

print(ok)