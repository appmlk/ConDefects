import sys
input = sys.stdin.readline
inf = 10 ** 18

def Matprod(A, B, N):
    temp = [inf] * N*N
    for i in range(N):
        for j in range(N):
            ij = i * N + j
            for k in range(N):
                temp[ij] = min(temp[ij], max(A[i*N+k], B[k*N+j]))
    return temp

def Matpow_Linear(A, M, N):
    Mat = [inf] * N*N
    for i in range(N):
        Mat[i*N+i] = -1
    while M:
        if M & 1:
            Mat = Matprod(Mat, A, N)
        A = Matprod(A, A, N)
        M >>= 1
    return Mat


N, T, L = map(int, input().split())
N2 = N * N
G = [inf] * N2 
for t in range(T):
    u, v = map(int, input().split())
    u, v = u - 1, v - 1
    G[v*N+u] = t
    
G = Matpow_Linear(G, L, N)
ans = []
for i in range(N):
    if G[i*N] == inf:
        ans.append(-1)
    else:
        ans.append(G[i*N] + 1)
        
print(*ans)