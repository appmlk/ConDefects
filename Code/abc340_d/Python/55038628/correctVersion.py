from collections import defaultdict
import heapq

N = int(input())

G = defaultdict(list)
for i in range(1,N):
    a,b,x = map(int,input().split())
    G[i].append((i+1,a))
    G[i].append((x,b))

seen = [False] * (N+1)
dist = [10**15] * (N+1)
dist[1] = 0
q = [(0,1)]
heapq.heapify(q)
while(len(q)>0):
    v = heapq.heappop(q)[1]
    """
    E-TrainではこれをつけてTLE改善
    if seen[v] == True:
        continue
    """
    seen[v] = True
    for next in G[v]:
        if not seen[next[0]] and dist[next[0]] > dist[v] + next[1]:
            dist[next[0]] = dist[v] + next[1]
            heapq.heappush(q,(dist[next[0]],next[0]))
print(dist[N])