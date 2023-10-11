from collections import deque
    
N, M = map(int, input().split())
relations = [[] for _ in range(N)]
queue = deque([])
cnt = [0 for _ in range(N)]
U = [None] * M
V = [None] * M
for i in range(M):
    U[i], V[i] = map(int, input().split())
    relations[V[i]-1].append(U[i]-1)
    cnt[U[i]-1] += 1
for i in range(N):
    if cnt[i] == 0:
        queue.append(i)
ans = set([n for n in range(N)])
while queue:
    tmp = queue.popleft()
    ans.discard(tmp)
    for r in relations[tmp]:
        cnt[r] -= 1
        if cnt[r] == 0:
            queue.append(r)
print(len(ans))
    