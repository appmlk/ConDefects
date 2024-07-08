import sys
from pprint import pprint

sys.setrecursionlimit(10**7)
read_int = lambda: int(sys.stdin.readline())
read_ints = lambda: list(map(int, sys.stdin.readline().split()))
read_float = lambda: float(sys.stdin.readline())
read_floats = lambda: list(map(float, sys.stdin.readline().split()))


def get_logger(debug=True):
    if not debug:
        return type("Dummy", (object,), {"debug": lambda self, a: None})()
    import logging

    logger = logging.getLogger("")
    logger.setLevel(logging.DEBUG)
    handler = logging.StreamHandler(sys.stdout)
    handler.setFormatter(logging.Formatter("[%(funcName)s:%(lineno)s] %(message)s"))
    logger.addHandler(handler)
    return logger


def modpow(a: int, n: int, mod: int) -> int:
    """二分累乗法"""

    res = 1
    while n > 0:
        if n & 1:
            res = res * a % mod
        a = a * a % mod
        n >>= 1
    return res


def modinv(a: int, mod: int) -> int:
    """逆元の計算

    オイラーの小定理より
    法modにおけるaの逆元a^-1 = a^(mod-2) % mod
    """

    return modpow(a, mod - 2, mod)


# -------------------------------
log = get_logger(False)

MOD = 998244353
N, K = read_ints()

invn2 = modinv(N * N, MOD)
p0 = 1
for _ in range(K):
    p0 = p0 * ((N - 1) ** 2 + 1) + 2 * (1 - p0)
    p0 = p0 * invn2 % MOD
log.debug(p0)

ans = p0 + (2 + N) * (1 - p0) // 2
print(ans % MOD)

"""test cases

"""
