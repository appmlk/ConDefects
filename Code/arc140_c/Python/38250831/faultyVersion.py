from collections import deque


N, X = map(int, input().split())

ans = []
Q = deque()
for i in range(N):
    if i + 1 != X:
        Q.append(i + 1)

for i in range(N - 1):
    if i % 2 != (X - N // 2) % 2:
        ans.append(Q.pop())
    else:
        ans.append(Q.popleft())
ans.append(X)
print(*ans[::-1])