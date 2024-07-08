import sys
input = sys.stdin.readline
sys.setrecursionlimit(10**9)

def solve():
    INF = 10**18
    #INF = 10**2

    N, M = map(int, input().split())
    Ass = [[0]*(N+2)] + [[0]+list(map(int, input().split())) for _ in range(N)]

    accss = [[0]*(N+1) for _ in range(N+1)]
    for x in range(1, N+1):
        for y in range(1, N+1):
            accss[x][y] = accss[x][y-1] + Ass[x][y]
    for x in range(1, N+1):
        for y in range(1, N+1):
            accss[x][y] += accss[x-1][y]
    # print('# accss:')
    # for accs in accss:
    #     print('# ', accs)

    def getRangeSum2D(accAss, xFr, xTo, yFr, yTo):
        return accAss[xTo+1][yTo+1] - accAss[xTo+1][yFr] - accAss[xFr][yTo+1] + accAss[xFr][yFr]

    M1 = M-1

    smss = [[0]*(N+1) for _ in range(N+1)]
    for x in range(N-M1):
        for y in range(N-M1):
            smss[x+1][y+1] = getRangeSum2D(accss, x, x+M1, y, y+M1)
    # print('# smss:')
    # for sms in smss:
    #     print('# ', sms)

    N2 = N+2

    mxULss = [[-INF]*(N2) for _ in range(N2)]
    for x in range(M, N+1):
        for y in range(M, N+1):
            mxULss[x][y] = max(smss[x-M1][y-M1], mxULss[x-1][y], mxULss[x][y-1])
    mxURss = [[-INF]*(N2) for _ in range(N2)]
    for x in range(M, N+1):
        for y in reversed(range(1, N-M1+1)):
            mxURss[x][y] = max(smss[x-M1][y], mxURss[x-1][y], mxURss[x][y+1])
    mxDLss = [[-INF]*(N2) for _ in range(N2)]
    for x in reversed(range(1, N-M1+1)):
        for y in range(M, N+1):
            mxDLss[x][y] = max(smss[x][y-M1], mxDLss[x+1][y], mxDLss[x][y-1])
    mxDRss = [[-INF]*(N2) for _ in range(N2)]
    for x in reversed(range(1, N-M1+1)):
        for y in reversed(range(1, N-M1+1)):
            mxDRss[x][y] = max(smss[x][y], mxDRss[x+1][y], mxDRss[x][y+1])
    # print('# mxULss:')
    # for mxULs in mxULss:
    #     print('# ', mxULs)
    # print('# mxURss:')
    # for mxURs in mxURss:
    #     print('# ', mxURs)
    # print('# mxDLss:')
    # for mxDLs in mxDLss:
    #     print('# ', mxDLs)
    # print('# mxDRss:')
    # for mxDRs in mxDRss:
    #     print('# ', mxDRs)

    mxRows = list(map(max, smss))
    mxColumns = list(max(sms) for sms in zip(*smss))
    #print('# mxRows:', mxRows)
    #print('# mxColumns:', mxColumns)

    ans = 0
    for x1 in range(M, N-M1+1):
        a1 = mxULss[x1][N]
        a2 = 0
        for x3 in range(x1+M+1, N-M1+1):
            mx = mxRows[x3-M]
            if mx > a2:
                a2 = mx
            a3 = mxDLss[x3][N]
            area = a1+a2+a3
    #        print('# (x1, x3):', (x1, x3), '/ (a1, a2, a3):', (a1, a2, a3), '/ area:', area)
            if area > ans:
                ans = area

    for y1 in range(M, N-M1+1):
        a1 = mxULss[N][y1]
        a2 = 0
        for y3 in range(y1+M+1, N-M1+1):
            mx = mxColumns[y3-M]
            if mx > a2:
                a2 = mx
            a3 = mxURss[N][y3]
            area = a1+a2+a3
    #        print('# (x1, x3):', (x1, x3), '/ (a1, a2, a3):', (a1, a2, a3), '/ area:', area)
            if area > ans:
                ans = area

    for x in range(M, N-M+1):
        a1 = mxULss[x][N]
        for y in range(M, N-M+1):
            a2 = mxDLss[x+1][y]
            a3 = mxDRss[x+1][y+1]
            area = a1+a2+a3
    #        print('# (x, y):', (x, y), '/ (a1, a2, a3):', (a1, a2, a3), '/ area:', area)
            if area > ans:
                ans = area

    for y in range(M, N-M+1):
        a1 = mxULss[N][y]
        for x in range(M, N-M+1):
            a2 = mxURss[x][y+1]
            a3 = mxDRss[x+1][y+1]
            area = a1+a2+a3
    #        print('# (x, y):', (x, y), '/ (a1, a2, a3):', (a1, a2, a3), '/ area:', area)
            if area > ans:
                ans = area

    for x in range(M, N-M+1):
        a1 = mxDRss[x+1][1]
        for y in range(M, N-M+1):
            a2 = mxULss[x][y]
            a3 = mxURss[x][y+1]
            area = a1+a2+a3
    #        print('# (x, y):', (x, y), '/ (a1, a2, a3):', (a1, a2, a3), '/ area:', area)
            if area > ans:
                ans = area

    for y in range(M, N-M+1):
        a1 = mxDRss[1][y+1]
        for x in range(M, N-M+1):
            a2 = mxULss[x][y]
            a3 = mxDLss[x+1][y]
            area = a1+a2+a3
    #        print('# (x, y):', (x, y), '/ (a1, a2, a3):', (a1, a2, a3), '/ area:', area)
            if area > ans:
                ans = area

    print(ans)


solve()
