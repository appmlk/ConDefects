from collections import deque

N, M = map(int, input().split())
A = list(map(int, input().split()))
cum_sum = [0] * (N + 1)
for i in range(N):
    cum_sum[i + 1] = cum_sum[i] + A[i]

ans = 0
res = 0
que = deque()
A = A[::-1]
idx = 0
for l in range(N):
    while A and len(que) < M:
        n = A.pop()
        que.append(n)
        res += n * len(que)
    if len(que) < M:
        break
    ans = max(ans, res)
    que.popleft()
    res -= cum_sum[idx + M] - cum_sum[idx]
    idx += 1
print(ans)