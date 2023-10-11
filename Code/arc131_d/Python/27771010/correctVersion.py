import sys
if sys.argv[-1] == 'ONLINE_JUDGE':
    import os
    import re
    with open(__file__) as f:
        source = f.read().split('###''nbacl')
    for s in source[1:]:
        s = re.sub("'''.*", '', s)
        sp = s.split(maxsplit=1)
        if os.path.dirname(sp[0]):
            os.makedirs(os.path.dirname(sp[0]), exist_ok=True)
        with open(sp[0], 'w') as f:
            f.write(sp[1])
    from nbmodule import cc
    cc.compile()
import numpy as np
from numpy import int64
from nbmodule import solve


def main(in_file):
    f = open(in_file)
    N, M, D = np.fromstring(f.readline(), dtype=int64, sep=' ')
    r = np.fromstring(f.readline(), dtype=int64, sep=' ')
    s = np.fromstring(f.readline(), dtype=int64, sep=' ')
    s = np.append(s, 0)
    ans = solve(N, M, D, r[1:], s)
    print(ans)


if __name__ == '__main__':
    main(0)

'''
###nbacl nbmodule.py
import numpy as np
from numpy import int64
from numba import njit, i8
from numba.pycc import CC

cc = CC('nbmodule')


# @njit
@cc.export('solve', (i8, i8, i8, i8[:], i8[:]))
def solve(N, M, D, r, s):
    dif = s[: -1] - s[1:]
    n_left = N // 2
    maxc = D // 2 + 1
    imo = np.zeros(maxc, int64)
    b = -D * n_left
    p = M
    for i in range(n_left):
        babs = -b
        while p > 0 and babs <= r[p - 1]:
            p -= 1
        imo[0] += s[p]
        k = p
        for i in range(k - 1, -1, -1):
            x = babs - r[i]
            if x < maxc:
                imo[x] += dif[i]
            else:
                break
        b += D
    p = 0
    for i in range(n_left, N):
        while p < M and b > r[p]:
            p += 1
        imo[0] += s[p]
        k = p
        for i in range(k, M):
            x = r[i] - b + 1
            if x < maxc:
                imo[x] -= dif[i]
            else:
                break
        b += D
    point = imo[0]
    pmax = imo[0]
    for i in range(1, maxc):
        point += imo[i]
        pmax = max(pmax, point)
    return pmax


if __name__ == '__main__':
    cc.compile()
'''
