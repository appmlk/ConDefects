

def solve():
    from sys import stdin

    def input():
        return stdin.readline().rstrip()

    n, t, l = map(int, input().split())
    edges = []
    for i in range(t):
        x, y = map(int, input().split())
        x, y = x - 1, y - 1
        edges.append((x, y, i + 1))

    def mat_prod(m1, m2):
        return [[min(max(m1[i][k], m2[k][j]) for k in range(n)) for j in range(n)] for i in range(n)]

    def mat_pow(m, p):
        if p == 1:
            return m
        mh = mat_pow(m, p // 2)
        ans = mat_prod(mh, mh)
        if p % 2:
            ans = mat_prod(ans, m)
        return ans

    def mat_vec(m, v):
        return [min(max(m[i][k], v[k]) for k in range(n)) for i in range(n)]

    infty = l + 10
    mt = [[infty] * n for i in range(n)]
    for x, y, ti in edges:
        mt[y][x] = ti

    ve = [infty] * n
    ve[0] = 0
    mp = mat_pow(mt, l)
    ans = mat_vec(mp, ve)
    for i in range(n):
        if ans[i] == infty:
            ans[i] = -1
    print(*ans)
solve()


