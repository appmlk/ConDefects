from collections import deque

def solve():
    n, m = map(int,input().split())
    c = list(map(int,input().split()))
    g = [[] for _ in range(n)]
    for _ in range(m):
        u, v = map(int,input().split())
        u -= 1
        v -= 1
        g[u].append(v)
        g[v].append(u)
    
    if c[0] == c[n-1]:
        print(-1)
        return
    
    visited = set()
    visited.add((0, n-1))
    dq = deque()
    dq.append((0, n-1, 0))
    while dq:
        p1, p2, cnt = dq.popleft()
        if (p1, p2) == (n-1, 0):
            print(cnt)
            return
        for p1nex in g[p1]:
            for p2nex in g[p2]:
                if c[p1nex] != c[p2nex] and (p1nex, p2nex) not in visited:
                    dq.append((p1nex, p2nex, cnt+1))
                    visited.add((p1nex, p2nex))

T = int(input())
for _ in range(T):
    solve()