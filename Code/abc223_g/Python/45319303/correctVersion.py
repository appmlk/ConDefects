from heapq import heappop, heappush

N = int(input())
tree = [[] for _ in range(N)]
for _ in range(N-1):
    u, v = map(int, input().split())
    u -= 1; v -= 1
    tree[u].append(v)
    tree[v].append(u)

root = 0
parent = [-1] * N
parent[root] = root
num_children = [0] * N
stack = [root]
leaf = set(range(N))
while stack:
    node = stack.pop()
    for nex in tree[node]:
        if parent[nex] != -1:
            continue
        parent[nex] = node
        num_children[node] += 1
        stack.append(nex)
        leaf.discard(node)

dp1 = [[0, 0] for _ in range(N)] # dp1[node][0/1] := node を使わない/使う
hq = [[] for _ in range(N)]
leaf = list(leaf)
while leaf:
    node = leaf.pop()
    min_diff = 10**6
    have_child = False
    for nex in tree[node]:
        if nex == parent[node]:
            continue
        have_child = True
        dp1[node][0] += max(dp1[nex])
        heappush(hq[node], dp1[nex][1]-dp1[nex][0])
        min_diff = min(min_diff, dp1[nex][1]-dp1[nex][0]) 
    
    if have_child:
        dp1[node][1] = dp1[node][0] - min_diff + 1
    par = parent[node]
    num_children[par] -= 1
    if num_children[par] == 0:
        leaf.append(par)

dp2 = [[0, 0] for _ in range(N)]
stack = [root]
while stack:
    node = stack.pop()
    par = parent[node]
    for nex in tree[node]:
        if nex == par:
            continue
        stack.append(nex)
    
    if node == par:
        continue
        
    dp2[node][1] = dp2[par][0] + dp1[par][0] - max(dp1[node]) + 1
    if hq[par][0] == dp1[node][1] - dp1[node][0]:
        escape = heappop(hq[par])
        if hq[par]:
            match = dp2[par][0] + dp1[par][0] - max(dp1[node]) - hq[par][0] + 1
        else:
            match = dp2[par][0]
        heappush(hq[par], escape)
    else:
        match = dp2[par][0] + dp1[par][1] - max(dp1[node])
    dp2[node][0] = max(
        dp2[node][1] - 1,
        dp2[par][1] + dp1[par][0] - max(dp1[node]),
        match
    )


ans = 0
max_match = max(dp1[root])
for node in range(N):
    if dp1[node][0] + dp2[node][0] == max_match:
        ans += 1

print(ans)
