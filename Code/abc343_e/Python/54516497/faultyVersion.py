# cf. https://atcoder.jp/contests/abc343/editorial/9435

def f(v):
    return max(0, min(v)) + 7 - max(v)

def count2(a1, b1, c1, a2, b2, c2):
    return f((a1, a2)) * f((b1, b2)) * f((c1, c2))

def count3(a1, b1, c1, a2, b2, c2, a3, b3, c3):
    return f((a1, a2, a3)) * f((b1, b2, b3)) * f((c1, c2, c3))

def generate():
    candidates = range(-1, 8)
    for a in candidates:
        for b in candidates:
            for c in candidates:
                yield a, b, c

def solve(v1, v2, v3):
    for a2, b2, c2 in generate():
        for a3, b3, c3 in generate():
            nv3 = count3(0, 0, 0, a2, b2, c2, a3, b3, c3)
            nv2 = count2(0, 0, 0, a2, b2, c2) + count2(0, 0, 0, a3, b3, c3) + count2(a2, b2, c2, a3, b3, c3) - 3*nv3
            nv1 = 3*(7**3) - 2*nv2 - 3*nv3
            if v1 == nv1 and v2 == nv2 and v3 == nv3:
                return f"Yes\n0 0 0 {a2} {b2} {c2} {a3} {b3} {c3}"
    return "No"

v1, v2, v3 = map(int, input().split())
print(solve(v1, v2, v3))

