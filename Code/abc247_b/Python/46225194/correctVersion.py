from collections import defaultdict

N = int(input())
name = []
d = defaultdict(int)

for i in range(N):
    s, t = input().split()
    name.append((s, t))
    d[s] += 1
    if s == t:
        continue
    d[t] += 1


dp = [[set()]*(2) for _ in range(N+1)]

for i in range(N):
    for j in range(2):
        if d[name[i][j]] != 1:
            continue
        dp[i+1][j] = dp[i][j].copy()
        dp[i+1][j].add(name[i][j])

if len(dp[-1][0]) == N or len(dp[-1][1]) == N:
    print("Yes")
else:
    print("No")
