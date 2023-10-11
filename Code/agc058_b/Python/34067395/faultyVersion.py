n = int(input())
P = list(map(int, input().split()))
L = []
stack = [(n + 1, 0)]
for i, p in enumerate(P, 1):
    while stack[-1][0] < p:
        stack.pop()
    L.append(stack[-1][1])
    stack.append((p, i))

R = []
stack = [(n + 1, n)]
for i in range(n - 1, -1, -1):
    p = P[i]
    while stack[-1][0] < p:
        stack.pop()
    R.append(stack[-1][1])
    stack.append((p, i))
R = R[::-1]
dp = [0] * (n + 1)
dp[0] = 1
for l, r in zip(L, R):
    tot = 0
    for i in range(l, r + 1):
        tot += dp[i]
        dp[i] = tot
print(dp[-1])