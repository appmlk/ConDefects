import sys
input = sys.stdin.readline

def Matprod(A, B, mod, N):
    temp = [0] * N*N
    for i in range(N):
        for j in range(N):
            ij = i * N + j
            for k in range(N):
                temp[ij] += A[i*N+k] * B[k*N+j]
                temp[ij] %= mod
    return temp

def Matpow_Linear(A, M, mod, N):
    Mat = [0] * N*N
    for i in range(N):
        Mat[i*N+i] = 1
    while M:
        if M & 1:
            Mat = Matprod(Mat, A, mod, N)
        A = Matprod(A, A, mod, N)
        M >>= 1
    return Mat


def f(x, a, b, mod):
    return (x * a + b) % mod

def baby_step_giant_step(s, g, mod, A, B):
    now = g
    D = dict()
    M = int(mod**0.5) + 1
    for j in range(M + 1):
        D[now] = j
        now = f(now, A, B, mod)

    now = s
    L = [A, B, 0, 1]
    L = Matpow_Linear(L, M, mod, 2)
    am, bm = L[0], L[1]
    for i in range(1, M + 1):
        now = f(now, am, bm, mod)
        if now in D:
            return i * M - D[now]
    return -1


T = int(input())
for _ in range(T):
    P, A, B, S, G = map(int, input().split())
    #fの逆関数がない時(A=0)は例外処理する。
    if A == 0:
        if S == G:
            print(0)
        elif G == B:
            print(1)
        else:
            print(-1)
        continue
    print(baby_step_giant_step(S, G, P, A, B))
    