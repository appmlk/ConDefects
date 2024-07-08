def gcd(a, b):
    while b: a, b = b, a % b
    return a
def isPrimeMR(n):
    d = n - 1
    d = d // (d & -d)
    L = [2, 7, 61] if n < 1<<32 else [2, 3, 5, 7, 11, 13, 17] if n < 1<<48 else [2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37]
    for a in L:
        t = d
        y = pow(a, t, n)
        if y == 1: continue
        while y != n - 1:
            y = y * y % n
            if y == 1 or t == n - 1: return 0
            t <<= 1
    return 1
def findFactorRho(n):
    m = 1 << n.bit_length() // 8
    for c in range(1, 99):
        f = lambda x: (x * x + c) % n
        y, r, q, g = 2, 1, 1, 1
        while g == 1:
            x = y
            for i in range(r):
                y = f(y)
            k = 0
            while k < r and g == 1:
                ys = y
                for i in range(min(m, r - k)):
                    y = f(y)
                    q = q * abs(x - y) % n
                g = gcd(q, n)
                k += m
            r <<= 1
        if g == n:
            g = 1
            while g == 1:
                ys = f(ys)
                g = gcd(abs(x - ys), n)
        if g < n:
            if isPrimeMR(g): return g
            elif isPrimeMR(n // g): return n // g
            return findFactorRho(g)
def primeFactor(n):
    i = 2
    ret = {}
    rhoFlg = 0
    while i * i <= n:
        k = 0
        while n % i == 0:
            n //= i
            k += 1
        if k: ret[i] = k
        i += i % 2 + (3 if i % 3 == 1 else 1)
        if i == 101 and n >= 2 ** 20:
            while n > 1:
                if isPrimeMR(n):
                    ret[n], n = 1, 1
                else:
                    rhoFlg = 1
                    j = findFactorRho(n)
                    k = 0
                    while n % j == 0:
                        n //= j
                        k += 1
                    ret[j] = k

    if n > 1: ret[n] = 1
    if rhoFlg: ret = {x: ret[x] for x in sorted(ret)}
    return ret

def mult(A, B):
    n = len(A)
    L = [0] * n
    for i, a in enumerate(A):
        for j, b in enumerate(B):
            ij = min(i + j, n - 1)
            L[ij] = (L[ij] + a * b) % P
    return L

def power(A, k):
    if k == 1:
        return A
    if k % 2 == 0:
        return power(mult(A, A), k // 2)
    return mult(power(A, k - 1), A)


P = 998244353
K, N, M = map(int, input().split())
if M == 1 or K == 1:
    print(1)
    exit()
pf = primeFactor(M)
primes = [p for p in pf]
n = len(primes)
A = [0] * n
if N == 0:
    for i in range(n):
        A[i] = pf[primes[i]]
else:
    for i in range(n):
        p = primes[i]
        c = 0
        NN = N
        while NN % p == 0:
            NN //= p
            c += 1
        A[i] = c

ans = 1
for k, p in enumerate(primes):
    a = min(A[k], pf[p])
    C = [0] * (pf[p] + 1)
    nn = p ** pf[p] * (p - 1) // p
    for i in range(pf[p]):
        C[i] = nn
        nn //= p
    C[-1] = p ** pf[p] - sum(C)
    D = power(C, K)
    s = D[a] % P * pow(C[a], P - 2, P) % P
    ans = ans * s % P
    if 0:
        print("A =", A)
        print("C =", C)
        print("D =", D)
        print("p, ans =", p, ans)
print(ans)