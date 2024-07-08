N = int(input())
MOD = 998244353

tree = [[] for _ in range(N)]
for _ in range(N-1):
    u, v = map(int, input().split())
    u -= 1; v -= 1
    tree[u].append(v)
    tree[v].append(u)


def calc_dist(fr):
    dist = [-1] * N
    dist[fr] = 0
    stack = [fr]
    while stack:
        node = stack.pop()
        for nex in tree[node]:
            if dist[nex] == -1:
                dist[nex] = dist[node] + 1
                stack.append(nex)
    return dist


def solve_odd(node):
    parent = [-1] * N
    parent[node] = node
    dist = [-1] * N
    dist[node] = 0
    stack = [node]
    while stack:
        node_ = stack.pop()
        for nex in tree[node_]:
            if dist[nex] == -1:
                dist[nex] = dist[node_] + 1
                parent[nex] = node_
                stack.append(nex)
    
    D = max(dist)
    d1 = D // 2
    now = dist.index(D)
    for _ in range(d1):
        now = parent[now]
    
    now2 = parent[now]
    dist = calc_dist(now)
    cnt = dist.count(d1+1)
    dist2 = calc_dist(now2)
    cnt2 = dist2.count(d1+1)
    return cnt * cnt2


def solve_even(node):
    parent = [-1] * N
    parent[node] = node
    dist = [-1] * N
    dist[node] = 0
    stack = [node]
    while stack:
        node_ = stack.pop()
        for nex in tree[node_]:
            if dist[nex] == -1:
                dist[nex] = dist[node_] + 1
                parent[nex] = node_
                stack.append(nex)
    
    D = max(dist)
    d1 = D // 2
    now = dist.index(D)
    for _ in range(d1):
        now = parent[now]
    
    dist = calc_dist(now)
    cnt = []
    for nex in tree[now]:
        cnt.append(0)
        stack = [(nex, now)]
        while stack:
            node, par = stack.pop()
            if dist[node] == d1:
                cnt[-1] += 1
            for nex_ in tree[node]:
                if nex_ == par:
                    continue
                stack.append((nex_, node))
    
    ans = 1
    for c in cnt:
        ans *= c + 1
        ans %= MOD
    ans -= 1
    for c in cnt:
        ans -= c

    return ans%MOD


dist0 = calc_dist(0)
dmax = max(dist0)
node1 = dist0.index(dmax)

dist1 = calc_dist(node1)
D = max(dist1)
if D % 2:
    ans = solve_odd(node1)
else:
    ans = solve_even(node1)

print(ans)