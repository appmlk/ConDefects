P = 998244353
i12 = P + 1 >> 1

def chk(x, nx, dx, lx, rx):
    ng = 0
    if dx < lx:
        ng ^= 1
    if rx < dx:
        ng ^= 2
    if (x ^ 3) & ng :
        return 0
    
    nnx = x
    if lx < dx:
        nnx |= 1
    if dx < rx:
        nnx |= 2
    
    if nnx == nx:
        return 1
    return 0

def subcalc(v, l1, r1, l2, r2):
    if l1 > r1 or l2 > r2:
        return 0
    X = [[0] * 4 for _ in range(4)]
    X[0][0] = 1
    for kk in range(59, -1, -1):
        d = v >> kk & 1
        lx = l1 >> kk & 1
        rx = r1 >> kk & 1
        ly = l2 >> kk & 1
        ry = r2 >> kk & 1
        nX = [[0] * 4 for _ in range(4)]
        for x in range(4):
            for nx in range(4):
                for dx in range(2):
                    fx = chk(x, nx, dx, lx, rx)
                    for y in range(4):
                        for ny in range(4):
                            for dy in range(2):
                                if d != dx ^ dy:
                                    continue
                                fy = chk(y, ny, dy, ly, ry)
                                nX[nx][ny] = (nX[nx][ny] + X[x][y] * fx * fy) % P
        X = nX
    ret = sum([sum(x) % P for x in X])
    return ret % P

def calc(L, R, V):
    def td(x):
        if x == 0:
            return (1, 0)
        if x == 1:
            return (0, 1)
        if x == 2:
            return (1, 3)
        if x == 3:
            return (0, 0)
    def calccalc(L1, R1, L2, R2, V):
        ret = 0
        for x in range(4):
            tx, dx = td(x)
            for y in range(4):
                ty, dy = td(y)
                l1 = (L1 - x + 3) // 4
                r1 = (R1 - x) // 4
                l2 = (L2 - y + 3) // 4
                r2 = (R2 - y) // 4
                v = V // 4
                if l1 > r1 or l2 > r2:
                    continue
                if dx ^ dy != V % 4:
                    continue
                if tx == ty == 0:
                    if V == dx ^ dy:
                        ret += (r1 - l1 + 1) * (r2 - l2 + 1) % P
                        if x == y:
                            ret += min(r1, r2) - max(l1, l2) + 1
                elif tx == 0:
                    if l2 <= v <= r2:
                        ret += (r1 - l1 + 1)
                elif ty == 0:
                    if l1 <= v <= r1:
                        ret += (r2 - l2 + 1)
                else:
                    r = subcalc(v, l1, r1, l2, r2)
                    if x == y and V == 0:
                        r += min(r1, r2) - max(l1, l2) + 1
                    ret += r
        return ret % P
    
    ret = 0
    ret += calccalc(L, R - 1, L, R - 1, V) * i12
    ret += calccalc(L - 1, L - 1, L, R, V)
    ret += calccalc(L, R - 1, R, R, V)
    if V == 0:
        ret -= R - L
    return ret % P

L, R, V = map(int, input().split())
print(calc(L, R, V))

