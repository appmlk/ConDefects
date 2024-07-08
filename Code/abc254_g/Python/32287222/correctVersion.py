from bisect import bisect, bisect_left
from collections import defaultdict

n, m, q = map(int, input().split())
elevators = [[] for _ in range(n)]
for _ in range(m):
    a, b, c = map(int, input().split())
    a -= 1
    elevators[a].append((b, c))

# 同じビルで範囲が被ってるものを集約
bbb = []
ccc = []
max_c = defaultdict(int)
for a in range(n):
    elevators[a].sort()
    elb = []
    elc = []
    for b, c in elevators[a]:
        if len(elb) == 0:
            elb.append(b)
            elc.append(c)
        elif elc[-1] < b:
            elb.append(b)
            elc.append(c)
        else:
            elc[-1] = max(elc[-1], c)
    bbb.append(elb)
    ccc.append(elc)
    for b, c in zip(elb, elc):
        max_c[b] = max(max_c[b], c)

# 全エレベーターを通しても行き来できない区間で分割するとともに、
# 他のエレベーターに内包されるやつを除く
all_elevators = sorted(max_c.items())
all_segmented_elevators = []
cur_segment = []
all_segment_start_floors = []
for b, c in all_elevators:
    if len(cur_segment) == 0:
        cur_segment.append((b, c))
    elif cur_segment[-1][1] < b:
        all_segmented_elevators.append(cur_segment)
        all_segment_start_floors.append(cur_segment[0][0])
        cur_segment = [(b, c)]
    elif cur_segment[-1][1] < c:
        cur_segment.append((b, c))
if cur_segment:
    all_segmented_elevators.append(cur_segment)
    all_segment_start_floors.append(cur_segment[0][0])

# 各エレベーターから 2^k 台の利用で行ける最高階層を求める
segment_start_floors = []
doubling_max_floor = []
for segment in all_segmented_elevators:
    m = len(segment)
    seg_max_floor = segment[-1][1]
    cur_start_floors = [b for b, c in segment]
    cur_doubling = [[c for b, c in segment]]
    while cur_doubling[-1][0] < seg_max_floor:
        cur = cur_doubling[-1]
        nxt = [0] * m
        for i in range(m):
            el = bisect(cur_start_floors, cur[i]) - 1
            nxt[i] = cur[el]
        cur_doubling.append(nxt)
    segment_start_floors.append(cur_start_floors)
    doubling_max_floor.append(cur_doubling)

# やっとクエリに答えるよ！
for i in range(q):
    x, y, z, w = map(int, input().split())
    x -= 1
    z -= 1
    res = 0

    if y == w:
        if x == z:
            print(0)
        else:
            print(1)
        continue

    if y > w:
        x, y, z, w = z, w, x, y

    si = bisect(all_segment_start_floors, y) - 1
    if si == -1 or all_segmented_elevators[si][-1][1] < w:
        print(-1)
        continue

    # 出発ビル・到着ビルのエレベーターで近づける分は近づく
    xi = bisect(bbb[x], y)
    if xi > 0:
        xc = ccc[x][xi - 1]
        # print(bbb[x], ccc[x], xi, xc, y)
        if xc >= y:
            if xc >= w:
                print(w - y + (x != z))
                continue
            res += xc - y
            y = xc

    zi = bisect_left(ccc[z], w)
    if zi < len(ccc[z]):
        zb = bbb[z][zi]
        # print(bbb[z], ccc[z], zi, zb, w)
        if zb <= w:
            if zb <= y:
                print(res + w - y + (x != z))
                continue
            res += w - zb
            w = zb

    # 出発・到着ビル内で最大限近づいた後、まだ y<w なので、
    # その間の距離と、あと必ず2回は乗り換える必要がある
    res += w - y + 2

    ssf = segment_start_floors[si]
    dmf = doubling_max_floor[si]
    lim = len(dmf)
    for k in range(lim - 1, -1, -1):
        el = bisect(ssf, y) - 1
        if dmf[k][el] < w:
            res += 1 << k
            y = dmf[k][el]

    print(res)
