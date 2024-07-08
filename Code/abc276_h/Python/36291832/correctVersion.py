DEBUG = 0
class VectorSpace01:
    def __init__(self):
        self.E = []
    
    def __contains__(self, n):
        for e in self.E:
            n = min(n, n ^ e)
        return 1 if n == 0 else 0
    
    def add(self, n):
        for e in self.E:
            n = min(n, n ^ e)
        if n:
            self.E.append(n)
            return 0
        return 1
    
    def dimension(self):
        return len(self.E)

def gauss(n, A):
    def disp():
        print("-" * 10)
        print("i, j =", i, j)
        for a in A:
            # print(bin(a % (1 << n))[2:].zfill(n)[::-1], bin(a >> n)[2:].zfill(m)[::-1])
            print(bin(a % (1 << n))[2:].zfill(n)[::-1], bin(a >> n)[2:][::-1])
    
    k = len(A)
    j = 0
    i = 0
    while i < k and j < n:
        if DEBUG:
            disp()
        ii = i
        while ii < k and A[ii] >> j & 1 == 0:
            ii += 1
        if ii >= k:
            j += 1
            continue
        if ii > i:
            A[ii], A[i] = A[i], A[ii]
        
        for ii in range(k):
            if i == ii: continue
            if A[ii] >> j & 1:
                A[ii] ^= A[i]
        i += 1
        j += 1
    if DEBUG:
        disp()
    mmm = (1 << n) - 1
    V = []
    s = 0
    B, C = [], []
    for a in A:
        B.append(a & mmm)
        C.append(a >> n)
    j = 0
    for a1, a2 in zip(B, C):
        if a1:
            aa = a1 & -a1
            if a2: s ^= aa
        else:
            if a2: return (-1, -1, [])
        
        while j < n and a1 & (1 << j) == 0:
            u = 1 << j
            for b in B:
                if b >> j & 1:
                    u ^= b & -b
            V.append(u)
            j += 1
        j += 1
    while j < n:
        u = 1 << j
        for b in B:
            if b >> j & 1:
                u ^= b & -b
        j += 1
        V.append(u)
    return (len(V), s, V)

N, Q = map(int, input().split())
NN = N + 5
NN = 2020
X = []
for _ in range(Q):
    a, b, c, d, e = map(int, input().split())
    a -= 1
    c -= 1
    X.append((a, b, c, d, e))
if DEBUG:
    print("X =", X)

# Zero Check
Z = [[0] * (N + 2) for _ in range(N + 2)]
for a, b, c, d, e in X:
    if e:
        Z[a+1][c+1] += 1
        Z[a+1][d+1] -= 1
        Z[b+1][c+1] -= 1
        Z[b+1][d+1] += 1
if DEBUG:
    print("Z =")
    for z in Z:
        print(*z)
for i in range(N + 1):
    for j in range(N + 2):
        Z[i+1][j] += Z[i][j]
for i in range(N + 2):
    for j in range(N + 1):
        Z[i][j+1] += Z[i][j]
if DEBUG:
    print("Z =")
    for z in Z:
        print(*z)
for i in range(N + 2):
    for j in range(N + 2):
        Z[i][j] = min(Z[i][j], 1)
if DEBUG:
    print("Z =")
    for z in Z:
        print(*z)

ZZ = [z[1:-1] for z in Z[1:-1]]
if DEBUG:
    print("ZZ =")
    for z in ZZ:
        print(*z)

for i in range(N + 1):
    for j in range(N + 2):
        Z[i+1][j] += Z[i][j]
for i in range(N + 2):
    for j in range(N + 1):
        Z[i][j+1] += Z[i][j]
if DEBUG:
    print("Z =")
    for z in Z:
        print(*z)
for a, b, c, d, e in X:
    if e == 0:
        if Z[b][d] - Z[a][d] - Z[b][c] + Z[a][c] == (b - a) * (d - c):
            print("No")
            exit()

Y = []
for a, b, c, d, e in X:
    if e:
        if a == c == 0:
            Y.append((b * NN + d, e - 1))
        elif a == 0:
            Y.append((b * NN + c, b * NN + d, e - 1))
        elif c == 0:
            Y.append((a * NN + d, b * NN + d, e - 1))
        else:
            Y.append((a * NN + c, a * NN + d, b * NN + c, b * NN + d, e - 1))
            
S = set()
for y in Y:
    for yy in y:
        S.add(yy)
SS = sorted(S)
M = len(SS)
D = {a: i for i, a in enumerate(SS)}
DY = []
for y in Y:
    if len(y) == 2:
        a, e = y
        DY.append((D[a], e))
    elif len(y) == 3:
        a, b, e = y
        DY.append((D[a], D[b], e))
    else:
        a, b, c, d, e = y
        DY.append((D[a], D[b], D[c], D[d], e))
        
# DY = [(D[a], D[b], D[c], D[d], e) for a, b, c, d, e in Y]
if DEBUG:
    print("SS =", SS)
    print("D =", D)
    print("Y =", Y)
    print("DY =", DY)
A = []
for y in Y:
    if len(y) == 2:
        a, e = y
        s = (1 << D[a]) | (e << M)
    elif len(y) == 3:
        a, b, e = y
        s = (1 << D[a]) | (1 << D[b]) | (e << M)
    else:
        a, b, c, d, e = y
        s = (1 << D[a]) | (1 << D[b]) | (1 << D[c]) | (1 << D[d]) | (e << M)
    A.append(s)

_, s, _ = gauss(M, A)
if DEBUG:
    print("s =", s)
if s < 0:
    print("No")
    exit()
L = []
for k in range(M):
    if s >> k & 1:
        L.append(SS[k])

L.sort()
if DEBUG:
    print("L =", L)
    print("  =", [(a // NN, a % NN) for a in L])
L.append(-1)
k = 0
cucu = [0] * (N + 1)
for i in range(N):
    cu = 0
    for j in range(N):
        ij = (i + 1) * NN + (j + 1)
        if L[k] == ij:
            a = 1
            k += 1
        else:
            a = 0
        s = cucu[j] ^ cu ^ a
        if ZZ[i][j] and s:
            ZZ[i][j] = 2
        cu ^= s
        cucu[j] ^= cu

print("Yes")
if DEBUG:
    print("ZZ =")
for z in ZZ:
    print(*z)
