from collections import deque
n1, n2, m = map(int,input().split())

g1, g2 = [[] for _ in range(n1)],[[] for _ in range(n2)]
g = [[] for _ in range(n1+n2)]

for _ in range(m):
    a,b = [int(e) -1 for e in input().split()]
    g[a].append(b)
    g[b].append(a)

def dfs(s):
    dd = [-1] * (n1+n2)
    dd[s] = 0
    q = deque()
    q.append(0)
    while q:
        now = q.popleft()
        for b in g[now]:
            if dd[b] == -1:
                dd[b] = dd[now]+1
                q.append(b)
    return dd

len1 = max(dfs(0))
len2 = max(dfs(n1+n2-1))
print(len1+len2+1)




