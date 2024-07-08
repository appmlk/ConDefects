def resolve():
    import sys

    input = sys.stdin.readline
    t = int(input())
    for _ in range(t):
        p, a, b, s, g = map(int, input().split())

        def f(x):
            return (a * x + b) % p

        r = int(p**0.5) + 1
        xr = pow(a, r, p)
        yr = b
        for _ in range(r - 1):
            yr = (a * yr + b) % p

        def fr(x):
            return (xr * x + yr) % p

        if a == 0:
            print(0 if s == g else 1 if g == b else -1)
        else:
            print(bsgs2(s, g, r, f, fr))


def bsgs2(a, b, r, f, fr):
    # f^x(a) = b (mod m) たる最小のx
    # r = int(m**0.5) + 1, fr:f^r
    if a == b:
        return 0
    # baby step
    baby = {}
    for i in range(r):
        baby[b] = i
        b = f(b)
    # giant step
    for j in range(1, r + 3):
        a = fr(a)
        if a in baby:
            return j * r - baby[a]
    return -1


if __name__ == "__main__":
    resolve()
