P, B, N, M = map(int, input().split())
N += 1
G = [[False] * N for _ in range(N)]
for _ in range(M):
    l, r = map(int, input().split())
    l -= 1
    G[l][r] = G[r][l] = True

INF = 10**18
dp = [INF] * (1 << N)
for msk in range(1 << N):
    cnt = 0
    for i in range(N):
        for j in range(i + 1, N):
            if msk & (1 << i) and msk & (1 << j) and G[i][j]:
                cnt += 1
    if cnt == 0:
        dp[msk] = 1
        continue

    msk2 = msk
    while msk2 > 0:
        cmsk = msk - msk2
        dp[msk] = min(dp[msk], dp[msk2] + dp[cmsk])
        msk2 = (msk2 - 1) & msk

if dp[-1] <= P:
    print("Yes")
else:
    print("No")