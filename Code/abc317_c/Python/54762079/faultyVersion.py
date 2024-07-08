N, M = map(int, input().split())
G = [[0]*N for _ in range(N)]
for _ in range(M):
	A, B, C = map(int, input().split())
	G[A-1][B-1] = C
	G[B-1][A-1] = C
dist = 0
visited = [-1]*N
def rec(v, cost):
	visited[v] = 0
	global dist
	if cost > dist:
		dist = cost
	for v2 in range(N):
		if visited[v2] == -1:
			rec(v2, cost+G[v][v2])
	visited[v] = -1
for i in range(N):
	rec(i, 0)
print(dist)