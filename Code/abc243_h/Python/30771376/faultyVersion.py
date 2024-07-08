from collections import deque
MOD = 998244353

h, w = map(int, input().split())
S = [["."] * (w + 2)] + [list("." + input() + ".") for _ in range(h)] + [["."] * (w + 2)]
h += 2
w += 2

for i in range(h):
    for j in range(w):
        if S[i][j] == "S":
            si, sj = i, j
        elif S[i][j] == "G":
            gi, gj = i, j

if si > gi:
    T = [[""] * w for _ in range(h)]
    for i in range(h):
        for j in range(w):
            T[i][j] = S[h - 1 - i][j]
    si = h - 1 - si
    gi = h - 1 - gi
    S = T

if sj > gj:
    T = [[""] * w for _ in range(h)]
    for i in range(h):
        for j in range(w):
            T[i][j] = S[i][w - 1 - j]
    sj = w - 1 - sj
    gj = w - 1 - gj
    S = T

rb = [[0] * w for _ in range(h)]

i, j = si, sj
while j != gj:
    j += 1
    rb[i][j] = 1

while i != gi:
    rb[i][j] = 1
    i += 1

rb[si][sj] = 0
rb[gi][gj] = 0

if sj == gj:
    i, j = si, sj + 1
    while i <= gi:
        rb[i][j] = 2
        i += 1
elif si == gi:
    i, j = si - 1, sj
    while j <= gj:
        rb[i][j] = 2
        j += 1
else:
    i, j = si - 1, sj
    while j <= gj:
        rb[i][j] = 2
        j += 1
    while i <= gi:
        rb[i][j] = 2
        i += 1

inf = 1 << 30

dx = [-1, -1, -1, 0, 0, 1, 1, 1]
dy = [-1, 0, 1, -1, 1, -1, 0, 1]

used_red = [[False] * w for _ in range(h)]

def solve(x, y):
    def f(i, j, k):
        return (i * w + j) * 2 + k

    def isedge(i, j):
        return i == 1 or i == h - 2 or j == 1 or j == w - 2

    def iswall(i, j):
        return i == 0 or i == h - 1 or j == 0 or j == w - 1

    dist = [inf] * (h * w * 2)
    cnt = [0] * (h * w * 2)
    wall_dist = [inf, inf]
    wall_cnt = [0, 0]
    wall_used = [False, False]

    queue = deque()
    queue.append((x, y, 0, 0))
    dist[f(x, y, 0)] = 0
    cnt[f(x, y, 0)] = 1
    while queue:
        i, j, t, d = queue.popleft()
        if i != -1 and d > dist[f(i, j, t)]:
            continue
        for t_ in range(2):
            if not wall_used[t_] and wall_dist[t_] == d:
                wall_used[t_] = True
                for ni in [1, h - 2]:
                    for nj in range(1, w - 1):
                        if S[ni][nj] != "." or (rb[ni][nj] == 1 and used_red[ni][nj]):
                            continue
                        if rb[ni][nj] == 1:
                            nt = t_ ^ 1
                        else:
                            nt = t_
                        if dist[f(ni, nj, nt)] > d:
                            dist[f(ni, nj, nt)] = d
                            cnt[f(ni, nj, nt)] = wall_cnt[t_]
                            queue.appendleft((ni, nj, nt, dist[f(ni, nj, nt)]))
                        elif dist[f(ni, nj, nt)] == d:
                            cnt[f(ni, nj, nt)] += wall_cnt[t_]
                for nj in [1, w - 2]:
                    for ni in range(2, h - 2):
                        if S[ni][nj] != "." or (rb[ni][nj] == 1 and used_red[ni][nj]):
                            continue
                        if rb[ni][nj] == 1:
                            nt = t_ ^ 1
                        else:
                            nt = t_
                        if dist[f(ni, nj, nt)] > d:
                            dist[f(ni, nj, nt)] = d
                            cnt[f(ni, nj, nt)] = wall_cnt[t_]
                            queue.appendleft((ni, nj, nt, dist[f(ni, nj, nt)]))
                        elif dist[f(ni, nj, nt)] == d:
                            cnt[f(ni, nj, nt)] += wall_cnt[t_]
        
        if i == j == -1:
            continue

        tf = isedge(i, j)
        wall = True
        for di, dj in zip(dx, dy):
            ni = i + di
            nj = j + dj
            if S[ni][nj] != "." or (rb[ni][nj] == 1 and used_red[ni][nj]):
                continue
            if rb[i][j] ^ rb[ni][nj] == 3:
                nt = t ^ 1
            else:
                nt = t
            if iswall(ni, nj):
                if wall:
                    if wall_dist[nt] > d + 1:
                        wall_dist[nt] = d + 1
                        wall_cnt[nt] = cnt[f(i, j, t)]
                        queue.append((-1, -1, -1, wall_dist[nt]))
                    elif wall_dist[nt] == d + 1:
                        wall_cnt[nt] += cnt[f(i, j, t)]
                wall = False
                continue
            elif tf and isedge(ni, nj):
                continue
            if dist[f(ni, nj, nt)] > d + 1:
                dist[f(ni, nj, nt)] = d + 1
                cnt[f(ni, nj, nt)] = cnt[f(i, j, t)]
                queue.append((ni, nj, nt, dist[f(ni, nj, nt)]))
            elif dist[f(ni, nj, nt)] == d + 1:
                cnt[f(ni, nj, nt)] += cnt[f(i, j, t)]
    
    min_ = 1 << 30
    cn = 0
    wall = True
    for di, dj in zip(dx, dy):
        ni = x + di
        nj = y + dj
        if S[ni][nj] != "." or (rb[ni][nj] == 1 and used_red[ni][nj]):
            continue
        if rb[x][y] ^ rb[ni][nj] == 3:
            nt = 0
        else:
            nt = 1
        if iswall(ni, nj):
            if wall:
                if wall_dist[nt] < min_:
                    min_ = wall_dist[nt]
                    cn = wall_cnt[nt]
                elif wall_dist[nt] == min_:
                    cn += wall_cnt[nt]
                #print(min_, cn, "wall")
            wall = False
        else:
            if dist[f(ni, nj, nt)] + 1 < min_:
                min_ = dist[f(ni, nj, nt)] + 1
                cn = cnt[f(ni, nj, nt)]
            elif dist[f(ni, nj, nt)] + 1 == min_:
                cn += cnt[f(ni, nj, nt)]
            #print(min_, cn, ni, nj)
    
    return min_, cn


min_ = inf
ans = 0
for i in range(h):
    for j in range(w):
        if S[i][j] == "." and rb[i][j] == 1:
            mi, cn = solve(i, j)
            used_red[i][j] = True
            #print("=", i, j, mi, cn)
            if mi < min_:
                min_ = mi
                ans = cn
            elif mi == min_:
                ans += cn

if min_ == inf:
    print("No")
else:
    print("Yes")
    if min_ != 2:
        ans //= 2
    print(min_, ans % MOD)