def enum_sum(a: list[int]) -> dict[int, int]:
    n = len(a)
    s = [0] * (1 << n)
    for i in range(n):
        for u in range(1 << i):
            s[u | 1 << i] = s[u] + a[i]
    return {s[u]: u for u in range(1 << n)}


def solve(a: list[int], X: int) -> list[int] | None:
    sa = sum(a)
    sr = sa + X
    if sr & 1:
        return None
    sr >>= 1
    n = len(a)
    m = n >> 1
    b1 = enum_sum(a[:m])
    b2 = enum_sum(a[m:])
    for s1, u1 in b1.items():
        s2 = sr - s1
        u2 = b2.get(s2)
        if u2 is None:
            continue
        res = []
        for i in range(m):
            res.append(u1 >> i & 1)
        for i in range(n - m):
            res.append(u2 >> i & 1)
        return res
    return None


def main():
    N, X, Y = map(int, input().split())
    A = list(map(int, input().split()))

    res_y = solve(A[::2], Y)
    res_x = solve(A[1::2], X)
    if res_y is None or res_x is None:
        print("No")
        return
    print("Yes")

    A[::2] = res_y
    A[1::2] = res_x
    A = [1] + A
    res = []
    f = 0
    for i in range(N):
        f ^= 1
        res.append("L" if A[i] ^ A[i + 1] ^ f else "R")
    print("".join(res))


main()
