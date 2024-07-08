# For the sake of speed,
# this convolution is specialized to mod 998244353.


import math
import itertools
import functools
import collections
_fft_mod = 998244353
_fft_sum_e = (911660635, 509520358, 369330050, 332049552, 983190778, 123842337, 238493703, 975955924, 603855026, 856644456, 131300601,
              842657263, 730768835, 942482514, 806263778, 151565301, 510815449, 503497456, 743006876, 741047443, 56250497, 867605899, 0, 0, 0, 0, 0, 0, 0, 0)
_fft_sum_ie = (86583718, 372528824, 373294451, 645684063, 112220581, 692852209, 155456985, 797128860, 90816748, 860285882, 927414960, 354738543,
               109331171, 293255632, 535113200, 308540755, 121186627, 608385704, 438932459, 359477183, 824071951, 103369235, 0, 0, 0, 0, 0, 0, 0, 0)


def _butterfly(a):
    n = len(a)
    h = (n - 1).bit_length()
    for ph in range(1, h + 1):
        w = 1 << (ph - 1)
        p = 1 << (h - ph)
        now = 1
        for s in range(w):
            offset = s << (h - ph + 1)
            for i in range(p):
                l = a[i + offset]
                r = a[i + offset + p] * now % _fft_mod
                a[i + offset] = (l + r) % _fft_mod
                a[i + offset + p] = (l - r) % _fft_mod
            now *= _fft_sum_e[(~s & -~s).bit_length() - 1]
            now %= _fft_mod


def _butterfly_inv(a):
    n = len(a)
    h = (n - 1).bit_length()
    for ph in range(h, 0, -1):
        w = 1 << (ph - 1)
        p = 1 << (h - ph)
        inow = 1
        for s in range(w):
            offset = s << (h - ph + 1)
            for i in range(p):
                l = a[i + offset]
                r = a[i + offset + p]
                a[i + offset] = (l + r) % _fft_mod
                a[i + offset + p] = (l - r) * inow % _fft_mod
            inow *= _fft_sum_ie[(~s & -~s).bit_length() - 1]
            inow %= _fft_mod


def _convolution_naive(a, b):
    n = len(a)
    m = len(b)
    ans = [0] * (n + m - 1)
    if n < m:
        for j in range(m):
            for i in range(n):
                ans[i + j] = (ans[i + j] + a[i] * b[j]) % _fft_mod
    else:
        for i in range(n):
            for j in range(m):
                ans[i + j] = (ans[i + j] + a[i] * b[j]) % _fft_mod
    return ans


def _convolution_fft(a, b):
    a = a.copy()
    b = b.copy()
    n = len(a)
    m = len(b)
    z = 1 << (n + m - 2).bit_length()
    a += [0] * (z - n)
    _butterfly(a)
    b += [0] * (z - m)
    _butterfly(b)
    for i in range(z):
        a[i] = a[i] * b[i] % _fft_mod
    _butterfly_inv(a)
    a = a[:n + m - 1]
    iz = pow(z, _fft_mod - 2, _fft_mod)
    for i in range(n + m - 1):
        a[i] = a[i] * iz % _fft_mod
    return a


def _convolution_square(a):
    a = a.copy()
    n = len(a)
    z = 1 << (2 * n - 2).bit_length()
    a += [0] * (z - n)
    _butterfly(a)
    for i in range(z):
        a[i] = a[i] * a[i] % _fft_mod
    _butterfly_inv(a)
    a = a[:2 * n - 1]
    iz = pow(z, _fft_mod - 2, _fft_mod)
    for i in range(2 * n - 1):
        a[i] = a[i] * iz % _fft_mod
    return a


def convolution(a, b):
    """It calculates (+, x) convolution in mod 998244353. 
    Given two arrays a[0], a[1], ..., a[n - 1] and b[0], b[1], ..., b[m - 1], 
    it calculates the array c of length n + m - 1, defined by

    >   c[i] = sum(a[j] * b[i - j] for j in range(i + 1)) % 998244353.

    It returns an empty list if at least one of a and b are empty.

    Complexity
    ----------

    >   O(n log n), where n = len(a) + len(b).
    """
    n = len(a)
    m = len(b)
    if n == 0 or m == 0:
        return []
    if min(n, m) <= 100:
        return _convolution_naive(a, b)
    if a is b:
        return _convolution_square(a)
    return _convolution_fft(a, b)


# Reference: https://opt-cp.com/fps-fast-algorithms/
def inv(a):
    """It calculates the inverse of formal power series in O(n log n) time, where n = len(a).
    """
    n = len(a)
    assert n > 0 and a[0] != 0
    res = [pow(a[0], _fft_mod - 2, _fft_mod)]
    m = 1
    while m < n:
        f = a[:min(n, 2 * m)]
        g = res.copy()
        f += [0] * (2 * m - len(f))
        _butterfly(f)
        g += [0] * (2 * m - len(g))
        _butterfly(g)
        for i in range(2 * m):
            f[i] = f[i] * g[i] % _fft_mod
        _butterfly_inv(f)
        f = f[m:] + [0] * m
        _butterfly(f)
        for i in range(2 * m):
            f[i] = f[i] * g[i] % _fft_mod
        _butterfly_inv(f)
        f = f[:m]
        iz = pow(2 * m, _fft_mod - 2, _fft_mod)
        iz *= -iz
        iz %= _fft_mod
        for i in range(m):
            f[i] = f[i] * iz % _fft_mod
        res.extend(f)
        m *= 2
    res = res[:n]
    return res


def integ_inplace(a):
    n = len(a)
    assert n > 0
    if n == 1:
        return []
    a.pop()
    a.insert(0, 0)
    inv = [1, 1]
    for i in range(2, n):
        inv.append(-inv[_fft_mod % i] * (_fft_mod//i) % _fft_mod)
        a[i] = a[i] * inv[i] % _fft_mod


def deriv_inplace(a):
    n = len(a)
    assert n > 0
    for i in range(2, n):
        a[i] = a[i] * i % _fft_mod
    a.pop(0)
    a.append(0)


def log(a):
    a = a.copy()
    n = len(a)
    assert n > 0 and a[0] == 1
    a_inv = inv(a)
    deriv_inplace(a)
    a = convolution(a, a_inv)[:n]
    integ_inplace(a)
    return a


def exp(a):
    a = a.copy()
    n = len(a)
    assert n > 0 and a[0] == 0
    g = [1]
    a[0] = 1
    h_drv = a.copy()
    deriv_inplace(h_drv)
    m = 1
    while m < n:
        f_fft = a[:m] + [0] * m
        _butterfly(f_fft)

        if m > 1:
            _f = [f_fft[i] * g_fft[i] % _fft_mod for i in range(m)]
            _butterfly_inv(_f)
            _f = _f[m // 2:] + [0] * (m // 2)
            _butterfly(_f)
            for i in range(m):
                _f[i] = _f[i] * g_fft[i] % _fft_mod
            _butterfly_inv(_f)
            _f = _f[:m//2]
            iz = pow(m, _fft_mod - 2, _fft_mod)
            iz *= -iz
            iz %= _fft_mod
            for i in range(m//2):
                _f[i] = _f[i] * iz % _fft_mod
            g.extend(_f)

        t = a[:m]
        deriv_inplace(t)
        r = h_drv[:m - 1]
        r.append(0)
        _butterfly(r)
        for i in range(m):
            r[i] = r[i] * f_fft[i] % _fft_mod
        _butterfly_inv(r)
        im = pow(-m, _fft_mod - 2, _fft_mod)
        for i in range(m):
            r[i] = r[i] * im % _fft_mod
        for i in range(m):
            t[i] = (t[i] + r[i]) % _fft_mod
        t = [t[-1]] + t[:-1]

        t += [0] * m
        _butterfly(t)
        g_fft = g + [0] * (2 * m - len(g))
        _butterfly(g_fft)
        for i in range(2 * m):
            t[i] = t[i] * g_fft[i] % _fft_mod
        _butterfly_inv(t)
        t = t[:m]
        i2m = pow(2 * m, _fft_mod - 2, _fft_mod)
        for i in range(m):
            t[i] = t[i] * i2m % _fft_mod

        v = a[m:min(n, 2 * m)]
        v += [0] * (m - len(v))
        t = [0] * (m - 1) + t + [0]
        integ_inplace(t)
        for i in range(m):
            v[i] = (v[i] - t[m + i]) % _fft_mod

        v += [0] * m
        _butterfly(v)
        for i in range(2 * m):
            v[i] = v[i] * f_fft[i] % _fft_mod
        _butterfly_inv(v)
        v = v[:m]
        i2m = pow(2 * m, _fft_mod - 2, _fft_mod)
        for i in range(m):
            v[i] = v[i] * i2m % _fft_mod

        for i in range(min(n - m, m)):
            a[m + i] = v[i]

        m *= 2
    return a


def pow_fps(a, k):
    a = a.copy()
    n = len(a)
    l = 0
    while l < len(a) and not a[l]:
        l += 1
    if l * k >= n:
        return [0] * n
    ic = pow(a[l], _fft_mod - 2, _fft_mod)
    pc = pow(a[l], k, _fft_mod)
    a = log([a[i] * ic % _fft_mod for i in range(l, len(a))])
    for i in range(len(a)):
        a[i] = a[i] * k % _fft_mod
    a = exp(a)
    for i in range(len(a)):
        a[i] = a[i] * pc % _fft_mod
    a = [0] * (l * k) + a[:n - l * k]
    return a


# 素因数をcollections.Counterで返す。

# -----ここからρ法ライブラリ-----


def is_prime(n):
    "Miller Rabin primality test. 2 <= n <= 2 ** 64 is required"
    if n % 2 == 0:
        return n == 2
    d = n - 1
    r = (d & -d).bit_length() - 1
    d >>= r
    # witnesses をいい感じに決める。
    if n < 2152302898747:
        if n < 9080191:
            if n < 2047:
                witnesses = [2]
            else:
                witnesses = [31, 73]
        else:
            if n < 4759123141:
                witnesses = [2, 7, 61]
            else:
                witnesses = [2, 3, 5, 7, 11]
    else:
        if n < 341550071728321:
            if n < 3474749660383:
                witnesses = [2, 3, 5, 7, 11, 13]
            else:
                witnesses = [2, 3, 5, 7, 11, 13, 17]
        else:
            if n < 3825123056546413051:
                witnesses = [2, 3, 5, 7, 11, 13, 17, 19]
            else:
                witnesses = [2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31]
    # witnesses の決定終了
    for a in witnesses:
        x = pow(a, d, n)
        if x == 1 or x == n - 1:
            continue
        for _ in range(r - 1):
            x *= x
            x %= n
            if x == n - 1:
                break
        else:
            return False
    return True


@functools.lru_cache()  # お好みで。
def find_factor(n):
    "Find a non-trivial factor of n by using Pollard's rho algorithm."
    m = int(n ** 0.125) + 1
    c = 1
    while True:
        y = 1
        r = 1
        q = 1
        g = 1
        while g == 1:
            x = y
            for _ in range(r):
                y = (y * y + c) % n
            for k in range(0, r, m):
                ys = y
                for _ in range(m):
                    y = (y * y + c) % n
                    q = q * (x - y) % n
                g = math.gcd(q, n)
                if g != 1:
                    break
            else:  # 残りの k から r までをやる
                ys = y
                for _ in range(r-k):
                    y = (y * y + c) % n
                    q = q * (x - y) % n
                g = math.gcd(q, n)
            r *= 2

        if g == n:
            g = 1
            while g == 1:
                ys = (ys * ys + c) % n
                g = math.gcd(x - ys, n)

        if g == n:
            c += 1  # c を変えて続行。
        else:  # g は n の非自明な約数。
            return g


_prime_numbers_200 = [
    2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37,
    41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83,
    89, 97, 101, 103, 107, 109, 113, 127, 131,
    137, 139, 149, 151, 157, 163, 167, 173, 179,
    181, 191, 193, 197, 199
]


def factorize(n, try_small_factor=True):
    """
    引数 n の素因数分解をして、素因数のカウンター(collections.Counter)を返す。\n
    e.g. `factorize(60) == Counter({2: 2, 3: 1, 5: 1})`\n
    計算量は O(n ^ 1/4) 程度。
    """
    if n == 1:
        return collections.Counter()
    if is_prime(n):
        return collections.Counter({n: 1})

    ret = collections.Counter()
    if try_small_factor:
        for p in _prime_numbers_200:
            if n % p == 0:
                ret[p] = 1
                n //= p
                while n % p == 0:
                    ret[p] += 1
                    n //= p
            if n == 1:
                return ret

    while n != 1 and not is_prime(n):
        f = find_factor(n)
        if is_prime(f):
            ret[f] = 1
            n //= f
            while n % f == 0:
                ret[f] += 1
                n //= f
        elif is_prime(n // f):
            g = n // f
            n = f
            ret[g] = 1
            while n % g == 0:
                ret[g] += 1
                n //= g
        else:
            return ret + factorize(f, False) + factorize(n // f, False)

    if n == 1:
        return ret
    else:
        ret[n] = 1
        return ret


def divisors(n_or_factors):
    if isinstance(n_or_factors, int):
        n_or_factors = factorize(n_or_factors)
    iters = [[p ** i for i in range(e + 1)] for p, e in n_or_factors.items()]
    ret = []
    for ts in itertools.product(*iters):
        div = 1
        for t in ts:
            div *= t
        ret.append(div)
    ret.sort()
    return ret

# -----ここまでρ法ライブラリ-----



MOD = 998244353


K, N, M = map(int, input().split())

M_factors = factorize(M)
N_factors = collections.Counter()
for f, e in M_factors.items():
    fe = f ** e
    N_tmp = N % fe + fe
    while N_tmp % f == 0:
        N_factors[f] += 1
        N_tmp //= f
    N_factors[f] = min(N_factors[f], e)

# print(N_factors, M_factors)

answer = 1
inv_val = 1
for f, e in M_factors.items():
    g = [1]
    for _ in range(e):
        g.append(g[-1] * f % MOD)
    g.reverse()
    fe = g[0]
    for i in range(e):
        g[i] -= g[i + 1]
    inv_val *= g[N_factors[f]]
    inv_val %= MOD
    gK = pow_fps(g, K)
    gK.pop()
    s = sum(gK)
    gK.append((pow(fe, K, MOD) - s) % MOD)
    # print(g, gK, N_factors[f])
    answer *= gK[N_factors[f]]
    answer %= MOD

# print(inv_val)

if inv_val == 0:
    answer = int(K == 1)
else:
    answer *= pow(inv_val, MOD - 2, MOD)
    answer %= MOD

print(answer)
