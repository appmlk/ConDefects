import os
import sys

import numpy as np


def solve(inp):
    n = inp[0]
    points = []
    offset = 100_001
    for i in range(n):
        x = inp[i * 2 + 1]
        y = inp[i * 2 + 2]
        X = x + y
        Y = x - y + offset
        points.append((X, 1, Y))

    inp_offset = n * 2 + 1
    q = inp[inp_offset]
    xxx_ = inp[inp_offset + 1::3]
    yyy_ = inp[inp_offset + 2::3]
    query_xs = xxx_ + yyy_
    query_ys = xxx_ - yyy_ + offset
    query_ks = inp[inp_offset + 3::3]

    # === Fenwick Tree 定義 ====
    fenwick_n = 200_200  # CUSTOM
    fenwick_table = np.zeros(fenwick_n + 1, np.int64)

    def fenwick_add(i, x):
        i += 1
        while i <= fenwick_n:
            fenwick_table[i] += x
            i += i & -i

    def fenwick_sum(i):
        s = 0
        i += 1
        while i > 0:
            s += fenwick_table[i]
            i -= i & -i
        return s

    # === / ここまで Fenwick Tree 定義 ====

    lo_list = np.full(q, -1, np.int64)
    hi_list = np.full(q, 200_000, np.int64)
    mid_list = np.zeros(q, np.int64)
    res_list = np.zeros(q, np.int64)

    for _ in range(18):
        events = points.copy()
        for i in range(q):
            lo = lo_list[i]
            hi = hi_list[i]
            if lo + 1 == hi:
                continue
            mid_list[i] = mid = (lo + hi) >> 1
            x = query_xs[i]
            events.append((x - mid, 0, i))
            events.append((x + mid, 2, i))
        events.sort()

        fenwick_table.fill(0)

        for x, op, i in events:
            if op == 0:
                y1 = query_ys[i] - mid_list[i]
                y2 = query_ys[i] + mid_list[i]
                res = fenwick_sum(min(fenwick_n - 1, y2))
                if y1 > 0:
                    res -= fenwick_sum(y1 - 1)
                res_list[i] = -res
            elif op == 1:
                y1 = i
                fenwick_add(y1, 1)
            elif op == 2:
                y1 = query_ys[i] - mid_list[i]
                y2 = query_ys[i] + mid_list[i]
                res = fenwick_sum(min(fenwick_n - 1, y2))
                if y1 > 0:
                    res -= fenwick_sum(y1 - 1)
                res_list[i] += res
                if res_list[i] < query_ks[i]:
                    lo_list[i] = mid_list[i]
                else:
                    hi_list[i] = mid_list[i]

    return hi_list


SIGNATURE = '(i8[:],)'
if sys.argv[-1] == 'ONLINE_JUDGE':
    from numba.pycc import CC

    cc = CC('my_module')
    cc.export('solve', SIGNATURE)(solve)
    cc.compile()
    exit()

if os.name == 'posix':
    # noinspection PyUnresolvedReferences
    from my_module import solve
else:
    from numba import njit

    solve = njit(SIGNATURE, cache=True)(solve)
    print('compiled', file=sys.stderr)

inp = np.fromstring(sys.stdin.read(), dtype=np.int64, sep=' ')
ans = solve(inp)
print('\n'.join(map(str, ans)))
