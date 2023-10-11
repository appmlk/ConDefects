from collections import defaultdict


def find_bridge(n, s, links, double_links, aaa, bbb, ans):
    pre_order = 0
    q = [s]
    while q:
        u = q[-1]
        if status[u] < len(links[u]):
            if pre[u] == -1:
                pre[u] = low[u] = pre_order
                pre_order += 1

            v = links[u][status[u]]
            status[u] += 1

            if v == parents[u]:
                continue
            if pre[u] < pre[v]:
                continue

            i = double_links[u][v][0]
            if aaa[i] == v:
                ans[i] = 1

            if pre[v] != -1:
                low[u] = min(low[u], low[v])
                continue

            q.append(v)
            parents[v] = u


        else:
            q.pop()

            p = parents[u]
            if p != -1:
                low[p] = min(low[p], low[u])

                if pre[u] == low[u]:
                    if len(double_links[p][u]) > 1:
                        i, j = double_links[p][u][:2]
                        if aaa[i] == aaa[j]:
                            ans[i] = 0
                            ans[j] = 1
                        else:
                            ans[i] = 0
                            ans[j] = 0


n, m = map(int, input().split())
double_links = [defaultdict(list) for _ in range(n)]
aaa = [a - 1 for a in map(int, input().split())]
bbb = [b - 1 for b in map(int, input().split())]
for i in range(m):
    a = aaa[i]
    b = bbb[i]
    double_links[a][b].append(i)
    double_links[b][a].append(i)

links = []
for u in range(n):
    links.append(list(double_links[u].keys()))

ans = [0] * m
pre = [-1] * n
low = [1 << 60] * n
status = [0] * n
parents = [-1] * n

for s in range(n):
    if pre[s] != -1:
        continue
    find_bridge(n, s, links, double_links, aaa, bbb, ans)

print(''.join(map(str, ans)))
