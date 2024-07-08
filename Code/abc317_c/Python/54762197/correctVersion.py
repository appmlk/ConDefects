N, M = map(int, input().split())
G = [[0]*N for _ in range(N)]
for _ in range(M):
	A, B, C = map(int, input().split())
	G[A-1][B-1] = C
	G[B-1][A-1] = C
from itertools import permutations
T = [i for i in range(N)]
ans = 0
for t in permutations(T):
	total = 0
	for i in range(len(t)-1):
		if G[t[i]][t[i+1]] != 0:
			total += G[t[i]][t[i+1]]
		else:
			break
	ans = max(ans, total)
print(ans)