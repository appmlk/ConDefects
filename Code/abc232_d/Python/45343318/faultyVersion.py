from collections import defaultdict

H, W = map(int, input().split())
C = [list(input()) for _ in range(H)]

G = defaultdict(list)
for i in range(H):
    for j in range(W):
        if C[i][j] == "." and i + 1 <= H - 1:
            if C[i + 1][j] == ".":
                G[(i, j)].append((i + 1, j))
        if C[i][j] == "." and j + 1 <= W - 1:
            if C[i][j + 1] == ".":
                G[(i, j)].append((i, j + 1))
visited = set()


def dfs(p):
    global visited
    visited.add(p)
    for next in G[p]:
        if next not in visited:
            dfs(next)


dfs((0, 0))
ans = 0
for v in visited:
    ans = max(ans, v[0] + v[1])
print(ans)
