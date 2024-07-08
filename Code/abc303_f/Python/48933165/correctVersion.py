from collections import defaultdict
from operator import itemgetter
import sys
def main():
    input = sys.stdin.readline
    N, H = map(int, input().split())
    TD = [tuple(map(int, input().split())) for _ in range(N)]
    TDD = defaultdict(int)
    for t, d in TD:
        TDD[t] = max(TDD[t], d)
    TD = [(t, d) for t, d in TDD.items()]
    N = len(TD)
    TD.sort(key=itemgetter(0))
    P = [0] * (N + 1)  # P[i] = i番目以降の最大のd
    for i in range(N - 1, -1, -1):
        _, d = TD[i]
        P[i] = max(P[i + 1], d)
    pt = 0
    max_td = 0
    for i in range(N):
        t, d = TD[i]
        max_d = P[i]
        x = max(pt, min((max_td + max_d - 1) // max_d, t))
        dam = max_td * (x - pt) + max_d * (x + t - 1) * (t - x) // 2
        if dam < H:
            H -= dam
            max_td = max(max_td, t * d)
            pt = t
            continue
        l = pt - 1
        r = t - 1
        while r - l > 1:
            m = (l + r) // 2
            dam = max_td * (min(m + 1, x) - pt)
            if m + 1 > x:
                dam += max_d * (x + m) * (m + 1 - x) // 2
            if dam < H:
                l = m
            else:
                r = m
        print(r)
        return
    print(pt - 1 + (H + max_td - 1) // max_td)

if __name__ == '__main__':
    main()
