from collections import Counter
p, g, ig = 998244353, 3, 332748118
W = [pow(g, (p - 1) >> i, p) for i in range(24)]
iW = [pow(ig, (p - 1) >> i, p) for i in range(24)]

def fft(f_, k):
    f = f_[:]
    for l in range(k, 0, -1):
        d = 1 << l - 1
        U = [1]
        for i in range(d):
            U.append(U[-1] * W[l] % p)

        for i in range(1 << k - l):
            for j in range(d):
                s = i * 2 * d + j
                t = s + d
                f[s], f[t] = (f[s] + f[t]) % p, U[j] * (f[s] - f[t]) % p
    return f

def ifft(f_, k):
    f = f_[:]
    for l in range(1, k + 1):
        d = 1 << l - 1
        U = [1]
        for i in range(d):
            U.append(U[-1] * iW[l] % p)

        for i in range(1 << k - l):
            for j in range(d):
                s = i * 2 * d + j
                t = s + d
                f[s], f[t] = (f[s] + f[t] * U[j]) % p, (f[s] - f[t] * U[j]) % p
    return f

def convolve(a, b):
    def fft(f):
        for l in range(k, 0, -1):
            d = 1 << l - 1
            U = [1]
            for i in range(d):
                U.append(U[-1] * W[l] % p)

            for i in range(1 << k - l):
                for j in range(d):
                    s = i * 2 * d + j
                    t = s + d
                    f[s], f[t] = (f[s] + f[t]) % p, U[j] * (f[s] - f[t]) % p

    def ifft(f):
        for l in range(1, k + 1):
            d = 1 << l - 1
            U = [1]
            for i in range(d):
                U.append(U[-1] * iW[l] % p)

            for i in range(1 << k - l):
                for j in range(d):
                    s = i * 2 * d + j
                    t = s + d
                    f[s], f[t] = (f[s] + f[t] * U[j]) % p, (f[s] - f[t] * U[j]) % p

    n0 = len(a) + len(b) - 1
    if len(a) < 50 or len(b) < 50:
        ret = [0] * n0
        if len(a) > len(b): a, b = b, a
        for i, aa in enumerate(a):
            for j, bb in enumerate(b):
                ret[i+j] = (ret[i+j] + aa * bb) % p
        return ret
    
    k = (n0).bit_length()
    n = 1 << k
    a = a + [0] * (n - len(a))
    b = b + [0] * (n - len(b))
    fft(a), fft(b)
    for i in range(n):
        a[i] = a[i] * b[i] % p
    ifft(a)
    invn = pow(n, p - 2, p)
    for i in range(n0):
        a[i] = a[i] * invn % p
    del a[n0:]
    return a

P = 998244353
nn = 1001001

fa = [1] * (nn+1)
fainv = [1] * (nn+1)
for i in range(nn):
    fa[i+1] = fa[i] * (i+1) % P
fainv[-1] = pow(fa[-1], P-2, P)
for i in range(nn)[::-1]:
    fainv[i] = fainv[i+1] * (i+1) % P

C = lambda a, b: fa[a] * fainv[b] % P * fainv[a-b] % P if 0 <= b <= a else 0

N, M = map(int, input().split())
A = Counter([int(a) for a in input().split()])

def plus(A, B):
    if len(A) < len(B):
        A, B = B, A
    L = A[:]
    for i, b in enumerate(B):
        L[i] = (L[i] + b) % P
    return L
def minus(A, B):
    L = A[:]
    L += [0] * (len(B) - len(A))
    for i, b in enumerate(B):
        L[i] = (L[i] - b) % P
    return L

t = 20
nnn = 1 << t
Fe = fft([1] + [0] * (nnn - 1), t)
Fo = fft([1] + [0] * (nnn - 1), t)

for k, c in A.items():
    # print(k, c)
    fe = [0] * nnn
    fo = [0] * nnn
    for i in range(c + 1):
        if i * k >= nnn:
            break
        fe[i*k] = C(c, i)
        if i % 2:
            fo[i*k] = C(c, i)
        else:
            fo[i*k] = (-C(c, i)) % P
    fft_fe = fft(fe, t)
    fft_fo = fft(fo, t)
    Fe = [a * b % P for a, b in zip(Fe, fft_fe)]
    Fo = [a * b % P for a, b in zip(Fo, fft_fo)]

Fe = ifft(Fe, t)
Fo = ifft(Fo, t)
F = minus(Fe, Fo)
print(pow(2, (P - 2) * (t + 1), P) * F[M] % P if M < len(F) else 0)
