N, M = map(int, input().split())
P = [0] * N

for _ in range(M):
    a, b = map(int, input().split())
    a -= 1
    b -= 1
    P[b] += 1

ans = []
for i in range(N):
    if P[i] == 0:
        ans.append(i)

if len(ans) == 1:
    print(ans[0])
else:
    print(-1)
