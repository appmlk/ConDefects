N, M = map(int, input().split())
A = [list(map(int, input().split())) for _ in range(N)]
Asum = [[0]*M for _ in range(N)]
for i in range(N):
    Asum[i][0] = A[i][0]
    for j in range(1, M):
        Asum[i][j] = Asum[i][j-1] + A[i][j]
    if i > 0:
        for j in range(M):
            Asum[i][j] += Asum[i-1][j]
ans = 0
for m in range(1, 6): # 最小値
    histo = [0]*M
    for imax in range(N):
        for j in range(M):
            if A[imax][j] < m:
                histo[j] = 0
            else:
                histo[j] += 1
        lr = [[j, j] for j in range(M)]
        left = [(-1, -1)] # (hight, id)
        for j in range(M):
            while left[-1][0] >= histo[j]:
                left.pop()
            lr[j][0] = left[-1][1]
            left.append((histo[j], j))
        right = [(-1, M)]
        for j in range(M)[::-1]:
            while right[-1][0] >= histo[j]:
                right.pop()
            lr[j][1] = right[-1][1] - 1
            right.append((histo[j], j))
        for j in range(M):
            if histo[j] > 0:
                l, r = lr[j]
                u = imax - histo[j]
                sum = Asum[imax][r]
                if l >= 0:
                    sum -= Asum[imax][l]
                    if u >= 0:
                        sum += Asum[u][l]
                if u >= 0:
                    sum -= Asum[u][r]
                ans = max(ans, sum*m)
print(ans)
