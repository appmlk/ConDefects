from itertools import combinations

N, M, K = map(int, input().split())

edge = []
for _ in range(M):
    u, v, w = map(int, input().split())
    edge.append([u - 1, v - 1, w])

def find(x):
    if R[x] < 0:
        return x
    else:
        R[x] = find(R[x])
        return R[x]
    
def unite(root_a, root_b):  
    if root_a != root_b:
        if R[root_a] > R[root_b]: 
            R[root_a] = root_b
        elif R[root_b] > R[root_a]: 
            R[root_b] = root_a
        elif R[root_a] == R[root_b]:
            R[root_b] = root_a
            R[root_a] -= 1

def group_size(R):
    count = 0
    for r in R:
        if r < 0:
            count += 1
    return count

ans = 1 << 70
for edges in combinations(edge, N - 1): # 辺の数はN-1本でいい
    R = [-1]*N
    cost = 0
    for edge in edges:
        u, v, w = edge
        root_u = find(u)
        root_v = find(v)
        unite(root_u, root_v)
        cost += w
    if group_size(R) == 1:
        ans = min(cost%K, ans)

print(ans)