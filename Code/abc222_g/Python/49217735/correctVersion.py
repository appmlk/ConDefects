def resolve():
    import sys

    input = sys.stdin.readline
    t = int(input())
    for _ in range(t):
        k = int(input())
        if k == 1 or k == 2:
            print(1)
            continue

        m = 9 * k if k % 2 else 9 * k // 2
        try:
            ans = bsgs(10, pow(10, -1, m), m)
            print(ans + 2 if ans >= 0 else -1)
        except:
            print(-1)


def bsgs(a, b, m, is_prime=False):
    # a^x = b (mod m) たる最小のx
    def inv(a, m):
        x, y = 1, 0
        while m != 0:
            x, y = y, x - (a // m) * y
            a, m = m, a % m
        return x

    if m == 1:
        return 0
    if a == 0:
        if b <= 1:
            return b ^ 1
        return -1

    # gcd(a,m) == 1 にする
    import math

    c = 0
    while True:
        if m == 1:
            return c
        g = math.gcd(a, m)
        if g == 1:
            break
        if b % g:
            return -1
        b //= g
        m //= g
        if is_prime:
            b *= pow(a // g, m - 2, m)
        else:
            b *= inv(a // g, m)
        b %= m
        c += 1

    # baby step
    r = int(m**0.5) + 1
    baby = {}
    x = a
    for i in range(r):
        baby[x] = i
        if x == b:
            return i + c
        x *= a
        x %= m
    x *= inv(a, m)
    x %= m
    # giant step x:a^r
    if is_prime:
        inv_ar = pow(x, m - 2, m)
    else:
        inv_ar = inv(x, m)
    for j in range(1, r + 1):
        b *= inv_ar
        b %= m
        if b in baby:
            return j * r + baby[b] + c

    return -1


if __name__ == "__main__":
    resolve()
