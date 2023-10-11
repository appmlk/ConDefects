N, K = map(int, input().split())
A = list(map(int, input().split()))
B = []
for i, a in enumerate(A):
    B.append((a, i))
B.sort(key=lambda x: (x[0], -x[1]))
INF = N + 10
ans = INF
now = -1
for a, i in B:
    if i < K:
        now = max(now, i)
    else:
        if now >= 0:
            ans = min(ans, i - now)
if ans == INF:
    print(-1)
else:
    print(ans)
