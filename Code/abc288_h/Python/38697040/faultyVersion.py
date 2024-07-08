DEBUG = 0
N, M, X = map(int, input().split())
if DEBUG:
    print("N, M, X =", N, M, X)
K = 30
P = 998244353
nn = 1010
fa = [1] * (nn + 1)
fainv = [1] * (nn + 1)
for i in range(nn):
    fa[i+1] = fa[i] * (i + 1) % P
fainv[-1] = pow(fa[-1], P - 2, P)
for i in range(nn)[::-1]:
    fainv[i] = fainv[i+1] * (i + 1) % P

def C(a, b):
    if a <= 1000:
        return fa[a] * fainv[b] % P * fainv[a-b] % P if 0 <= b <= a else 0
    s = 1
    for i in range(b):
        s = s * (a - i) % P
    return s * fainv[b] % P

cc = [[0] * (i + 3) for i in range(N + 1)]
cc[0][0] = 1
for i in range(1, N + 1):
    for j in range(i + 1):
        cc[i][j] = (j * cc[i-1][j-1] + (M + 1 - j) * cc[i-1][j+1])

if DEBUG:
    print("cc =")
    for ccc in cc:
        print(ccc)

po2 = [1]
for _ in range(1010):
    po2.append(po2[-1] * 2 % P)

def calc_arr(n):
    if not n:
        return [0]
    Y = [0] * (n + 1)
    Y[0] = 1
    for i in range(K)[::-1]:
        Mi = (M >> i) & 1
        Xi = (X >> i) & 1
        nY = [0] * (n + 1)
        if Mi:
            j = 0
            for nj in range(j, n + 1):
                f = C(n - j, n - nj)
                if nj & 1 == Xi:
                    nY[nj] = (nY[nj] + Y[j] * f) % P
        else:
            j = 0
            nj = 0
            if Xi == 0:
                nY[nj] = Y[j]
        
        if Mi:
            for j in range(1, n + 1):
                for nj in range(j, n + 1):
                    f = C(n - j, n - nj) * po2[j-1] % P
                    nY[nj] = (nY[nj] + Y[j] * f) % P
        else:
            for j in range(1, n + 1):
                nj = j
                f = po2[j-1]
                nY[nj] = (nY[nj] + Y[j] * f) % P
        
        Y = nY
        if 0:
            print("Y =", Y)
    return Y

for n in range(N + 1):
    if 0:
        print("-" * 10, "n =", n, "-" * 10)
    calc_arr(n)

ARR = [sum(calc_arr(n)) % P for n in range(N + 1)]

if DEBUG:
    print("ARR =", ARR)

def calc_set():
    SET = [0] * (N + 1)
    SET[0] = 0 if X else 1
    for n in range(1, N + 1):
        s = ARR[n]
        for i in range(n):
            s -= SET[i] * cc[n][i] % P
        SET[n] = s % P * fainv[n] % P
    return SET

SET = calc_set()
if DEBUG:
    print("SET =", SET)

def calc_mul(n):
    s = 0
    for i in range(n // 2 + 1):
        s += C(i + M, i) * SET[n - 2 * i] % P
    return s % P

print(calc_mul(N))