from collections import deque
def slidemin(X, k):
    q = deque([])
    ret = []
    for i in range(len(X)):
        while q and q[-1][1] >= X[i]:
            q.pop()
        deque.append(q, (i + k, X[i]))
        if q[0][0] == i:
            deque.popleft(q)
        if i >= k-1:
            ret.append(q[0][1])
    return ret

N, A, B, C, D = map(int, input().split())
X = [int(a) for a in input().split()]
SX = [0] * (N + 1)
for i, x in enumerate(X):
    SX[i+1] = SX[i] + X[i]
Y = [[0] * (N - i + 1) for i in range(N + 1)]
Y[1] = X[:]
for i in range(2, N + 1):
    for j in range(N - i + 1):
        s = SX[j+i] - SX[j]
        Y[i][j] = max(-Y[i-1][j+1] + X[j], -Y[i-1][j] + X[j+i-1])
    for cost, tries in ((A, B), (C, D)):
        if i - tries <= 0:
            for j in range(N - i + 1):
                s = SX[j+i] - SX[j]
                Y[i][j] = max(Y[i][j], s - cost)
            continue
        ii = i - tries
        T = Y[ii][:]
        for j in range(N - ii + 1):
            T[j] += SX[j+ii] - SX[j]
        TT = slidemin(T, tries + 1)
        for j in range(N - i + 1):
            s = SX[j+i] - SX[j]
            Y[i][j] = max(Y[i][j], -TT[j] + s - cost)
print(Y[-1][-1])