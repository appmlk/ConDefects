import heapq
import math
def dijkstra(graph,start):
	N = len(graph)
	dist = [float('inf')] * len(graph)
	dist[start] = 0
	q = [(0,start)]
	while q:
		cost,current = heapq.heappop(q)
		if dist[current] < cost: continue
		if math.isfinite(dist[goal]):
			print(dist[goal])
			exit() 
		for i, weight in graph[current]:
			if cost + weight < dist[i]:
				dist[i] = cost + weight
				heapq.heappush(q, (dist[i],i,))
	return dist

N,M = map(int,input().split())
A = list(map(int,input().split()))
B = list(map(int,input().split()))

order = sorted(range(N), key=lambda i:B[i])
A = [A[i] for i in order]
B = [B[i] for i in order]
start	= order.index(0)
goal	= order.index(N-1)

graph = [[] for _ in range(2*N)]
for i in range(N-1):
	graph[N+i].append((N+i+1,B[i+1]-B[i],))

import bisect
for i in range(N):
	graph[i  ].append((N,A[i]+B[0]))
	graph[i+N].append((i,0        ))
	idx = bisect.bisect_left(B,M-A[i])
	if idx < N:
		graph[i].append((N+idx,A[i]+B[idx]-M))

dist = dijkstra(graph, start)
print(dist[goal])