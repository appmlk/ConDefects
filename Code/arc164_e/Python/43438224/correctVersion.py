N, Q = map(int, input().split())
X = []
S = {0, N}
for _ in range(Q):
    l, r = map(int, input().split())
    l -= 1
    S.add(l)
    S.add(r)
    X.append((l, r))
SS = sorted(S)
D = {a: i for i, a in enumerate(SS)}
M = len(SS)
X = [(D[l], D[r]) for l, r in X]
d = 0
s = 1
while s < M - 1:
    s *= 2
    d += 1
L = [0] * (M + 1)
R = [0] * (M + 1)
I = [0] * (M + 1)
for l, r in X:
    if l + 1 == r:
        I[l] += 1
    else:
        L[l] += 1
        R[r] += 1

inf = 10 ** 6
Y = [0]
for i in range(M - 1):
    ni = i + 1
    nY = [inf] * (i * 2 + 5)
    for j in range(len(Y)):
        for dj in ((1, ) if j % 2 else (1, 2)):
            nj = j + dj
            if nj >= len(nY):
                continue
            if dj == 2:
                nY[nj] = min(nY[nj], Y[j])
            else:
                if nj % 2:
                    c = I[i] + R[ni]
                else:
                    c = I[i] + L[i]
                nY[nj] = min(nY[nj], Y[j] + c)
        
    Y = nY
print(d, Y[s] * 2 if d else Q)