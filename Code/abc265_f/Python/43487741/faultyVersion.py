M = 998244353
N, D = map(int, input().split())
P = list(map(int, input().split()))
Q = list(map(int, input().split()))
size = (D+1)*(D+1)
def idx(i, j):
    return i + j * (D+1)
dp = [0]*size
px, qx = P[0], Q[0]
boaders = [px-D, px+D, qx-D, qx+D]
boaders.sort()
for rx in range(boaders[1], boaders[2]+1):
    dp[idx(abs(px-rx), abs(qx-rx))] += 1
sumu = [0]*size # up
sumd = [0]*size # down
for d in range(1, N):
    for s in range(2*D+1):
        j = min(s, D)
        i = s - j
        idx_ = idx(i, j)
        sumd[idx_] = dp[idx_]
        i, j, idx_ = i+1, j-1, idx_-D
        while i <= D and j >= 0:
            sumd[idx_] = (sumd[idx_+D] + dp[idx_]) % M
            i, j, idx_ = i+1, j-1, idx_-D
    for s in range(-D, D+1):
        j = max(0, s)
        i = j - s
        idx_ = idx(i, j)
        sumu[idx_] = dp[idx_]
        i, j, idx_ = i+1, j+1, idx_+D+2
        while i <= D and j <= D:
            sumu[idx_] = (sumu[idx_-D-2] + dp[idx_]) % M
            i, j, idx_ = i+1, j+1, idx_+D+2
    ndp = [0]*size
    s = abs(P[d] - Q[d])
    for i in range(D+1):
        for j in range(D+1):
            si = max(0, i-s)
            sj = i + j - si - s
            if 0 <= sj:
                tj = max(0, j-s)
                ti = i + j - tj - s
                ndp[idx(i, j)] += sumd[idx(ti, tj)] - (0 if si == 0 or sj == D else sumd[idx(si-1, sj+1)])
                if ti > 0 and tj > 0:
                    ndp[idx(i, j)] += sumu[idx(ti-1, tj-1)]
            if si > 0 and sj > 0 and (si, sj) != (i, j):
                ndp[idx(i, j)] += sumu[idx(si-1, sj-1)]
            ndp[idx(i, j)] %= M
    dp = ndp
print(sum(dp) % M)
