N, mod = map(int, input().split())

con = [[0]*N for _ in range(N)]
sep = [[0]*(N+1) for _ in range(N)]
con[0][0] = 1
sep[0][1] = 1
for n in range(N)[1:]:
    con[n][0] = 1
    for m in range(n+1)[1:]:
        con[n][m] = con[n-1][m] + con[n-1][m-1]*3 + sep[n-1][m]
        con[n][m] %= mod
    for m in range(n+2)[2:]:
        sep[n][m] = con[n-1][m-2]*2 + sep[n-1][m-1]
        sep[n][m] %= mod

print(*con[-1])
