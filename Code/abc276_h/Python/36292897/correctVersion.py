DEBUG = 0
class BitSet():
    def __init__(self, L = [], X = None):
        self.m = 63
        if X is None:
            self.n = (len(L) - 1) // self.m + 1
            self.X = [0] * self.n
            for i in range(self.n):
                for j in range(self.m):
                    ij = i * self.m + j
                    if ij >= len(L):
                        continue
                    if L[ij]:
                        self.X[i] |= 1 << j
        else:
            self.n = len(X)
            self.X = X
    
    def copy(self):
        return BitSet(None, self.X)
    
    def __getitem__(self, sl):
        if type(sl) == int:
            i = sl // self.m
            j = sl % self.m
            return self.X[i] >> j & 1 if i < len(self.X) else 0
        if not sl.start:
            if sl.stop is None or sl.stop == self.n:
                return 1 // 0
            else:
                return 1 // 0
        else:
            if sl.stop is None or sl.stop == self.n:
                return 1 // 0
            else:
                return 1 // 0
    def __and__(self, other):
        print(self.n, other.n)
        n = min(self.n, other.n)
        X = [0] * n
        for i in range(n):
            X[i] = self.X[i] & other.X[i]
        return BitSet(None, X)
    def __or__(self, other):
        n = min(self.n, other.n)
        if self.n > other.n:
            X = [0] * n + self.X[n:]
        else:
            X = [0] * n + other.X[n:]
            
        for i in range(n):
            X[i] = self.X[i] | other.X[i]
        return BitSet(None, X)
    def __xor__(self, other):
        n = min(self.n, other.n)
        if self.n > other.n:
            X = [0] * n + self.X[n:]
        else:
            X = [0] * n + other.X[n:]
            
        for i in range(n):
            X[i] = self.X[i] ^ other.X[i]
        return BitSet(None, X)
    def __irshift__(self, k):
        i = k // self.m
        j = k % self.m
        if j == 0:
            self.X = self.X[i:]
        else:
            X = []
            mmm = (1 << j) - 1
            for ii in range(i, self.n - 1):
                X.append((self.X[ii] >> j) | ((self.X[ii+1] & mmm) << self.m - j))
            a = self.X[self.n - 1] >> j
            if a:
                X.append(a)
            self.X = X
        self.n = len(self.X)
        return self
    def __rshift__(self, k):
        i = k // self.m
        j = k % self.m
        if j == 0:
            X = self.X[i:]
        else:
            X = []
            mmm = (1 << j) - 1
            for ii in range(i, self.n - 1):
                X.append((self.X[ii] >> j) | ((self.X[ii+1] & mmm) << self.m - j))
            a = self.X[self.n - 1] >> j
            if a:
                X.append(a)
        return BitSet(None, X)
    def __ilshift__(self, k):
        i = k // self.m
        j = k % self.m
        if j == 0:
            self.X = [0] * i + self.X
        else:
            X = [0] * (self.n + i + 1)
            mmm = (1 << self.m - j) - 1
            for ii, x in enumerate(self.X):
                x1 = x & mmm
                x2 = x >> self.m - j
                X[ii+i] |= x1 << j
                X[ii+i+1] |= x2
            self.X = X
        self.n = len(self.X)
        return self
    def __lshift__(self, k):
        i = k // self.m
        j = k % self.m
        if j == 0:
            X = [0] * i + self.X
        else:
            X = [0] * (self.n + i + 1)
            mmm = (1 << self.m - j) - 1
            for ii, x in enumerate(self.X):
                x1 = x & mmm
                x2 = x >> self.m - j
                X[ii+i] |= x1 << j
                X[ii+i+1] |= x2
        return BitSet(None, X)
    def xor_kth_bit(self, k, x = 1):
        if x:
            i = k // self.m
            j = k % self.m
            while i >= len(self.X):
                self.X.append(0)
            self.n = len(self.X)
            self.X[i] ^= 1 << j
    def or_kth_bit(self, k, x = 1):
        if x:
            i = k // self.m
            j = k % self.m
            while i >= len(self.X):
                self.X.append(0)
            self.n = len(self.X)
            self.X[i] |= 1 << j
    def and_kth_bit(self, k, x = 0):
        if x == 0:
            i = k // self.m
            j = k % self.m
            while i >= len(self.X):
                self.X.append(0)
            self.n = len(self.X)
            if self.X[i] >> j & 1:
                self.X[i] ^= 1 << j
    def __bool__(self):
        for a in self.X:
            if a:
                return True
        return False
    def lsb(self):
        for i, x in enumerate(self.X):
            if x:
                a = (x & -x).bit_length() - 1
                return i * self.m + a
        return -1
    def disp(self):
        s = 0
        for x in self.X[::-1]:
            s <<= self.m
            s |= x
        print(bin(s)[2:])
bs = BitSet([1,0,1,1,0,1,1])

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
        while ii < k and A[ii][j] == 0:
            ii += 1
        if ii >= k:
            j += 1
            continue
        if ii > i:
            A[ii], A[i] = A[i], A[ii]
        
        for ii in range(k):
            if i == ii: continue
            if A[ii][j]:
                A[ii] ^= A[i]
        i += 1
        j += 1
    if DEBUG:
        disp()
    # mmm = (1 << n) - 1
    V = []
    s = BitSet()
    B, C = [], []
    for a in A:
        b = a.copy()
        c = a[n]
        b.and_kth_bit(n)
        B.append(b)
        C.append(c)
    j = 0
    for a1, a2 in zip(B, C):
        if a1:
            # aa = a1 & -a1
            l = a1.lsb()
            if a2:
                s.xor_kth_bit(l)
        else:
            if a2:
                return (None, None, None)
        
        while j < n and a1[j] == 0:
            u = BitSet()
            u.xor_kth_bit(j)
            for b in B:
                if b[j]:
                    l = b.lsb()
                    if l >= 0:
                        u.xor_kth_bit(l)
            V.append(u)
            j += 1
        j += 1
    while j < n:
        u = BitSet()
        u.xor_kth_bit(j)
        for b in B:
            if b[j]:
                l = b.lsb()
                if l >= 0:
                    u.xor_kth_bit(l)
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
J = []
for y in Y:
    if len(y) == 2:
        a, e = y
        bs = BitSet()
        bs.xor_kth_bit(D[a])
        bs.xor_kth_bit(M, e)
        s = (1 << D[a]) | (e << M)
    elif len(y) == 3:
        a, b, e = y
        bs = BitSet()
        bs.xor_kth_bit(D[a])
        bs.xor_kth_bit(D[b])
        bs.xor_kth_bit(M, e)
        s = (1 << D[a]) | (1 << D[b]) | (e << M)
    else:
        a, b, c, d, e = y
        bs = BitSet()
        bs.xor_kth_bit(D[a])
        bs.xor_kth_bit(D[b])
        bs.xor_kth_bit(D[c])
        bs.xor_kth_bit(D[d])
        bs.xor_kth_bit(M, e)
        s = (1 << D[a]) | (1 << D[b]) | (1 << D[c]) | (1 << D[d]) | (e << M)
    A.append(s)
    J.append(bs)

_, s, _ = gauss(M, J)
if DEBUG:
    print("s =", s)
if s is None:
    print("No")
    exit()
L = []
for k in range(M):
    if s[k]:
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
