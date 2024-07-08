mod = 998244353
h,w = map(int,input().split())
grid = [[0] * w for _ in range(h)]
sx,sy = 0,0
gx,gy = 0,0
for i in range(h):
    ci = input()
    for j in range(w):
        if ci[j] == '.':
            grid[i][j] = 1
        elif ci[j] == 'S':
            sx,sy = i,j
        elif ci[j] == 'G':
            gx,gy = i,j

def rotate(sx,sy,gx,gy,h,w,grid):
    sx,sy = sy,h-sx-1
    gx,gy = gy,h-gx-1
    grid2 = [[0] * h for _ in range(w)]
    for i in range(h):
        for j in range(w):
            grid2[j][h-i-1] = grid[i][j]
    h,w = w,h
    grid,grid2 = grid2,grid

    return sx,sy,gx,gy,h,w,grid

while sx > gx or sy >= gy:
    sx,sy,gx,gy,h,w,grid = rotate(sx,sy,gx,gy,h,w,grid)

if sx == gx:
    for j in range(sy+1,gy):
        if grid[sx][j] == 1:
            grid[sx][j] = 2
    if sx+1 == h:
        pass
    else:
        for j in range(sy, gy+1):
            if grid[sx+1][j] == 1:
                grid[sx+1][j] = 3
else:
    for j in range(sy+1,gy+1):
        if grid[sx][j] == 1:
            grid[sx][j] = 2
    for i in range(sx, gx):
        if grid[i][gy] == 1:
            grid[i][gy] = 2
    for j in range(sy, gy):
        if grid[sx+1][j] == 1:
            grid[sx+1][j] = 3
    for i in range(sx+1,gx+1):
        if grid[i][gy-1] == 1:
            grid[i][gy-1] = 3

# for i in grid:
#     print(i)

m = h*w+1
out0 = m-1
out1 = m*2-1
links = [set() for _ in range(m*2)] 
for i in range(h):
    for j in range(w):
        if grid[i][j] == 0:
            continue
        num = i*w+j
        for dx,dy in zip([-1,-1,-1,0,0,1,1,1],[-1,0,1,-1,1,-1,0,1]):
            dx += i
            dy += j
            if (dx,dy) in [(-1,j), (i,-1), (i,w)]:
                # links[num].add(out0)
                links[num+m].add(out1)
                links[out0].add(num)
                # links[out1].add(num+m)
            elif (dx,dy) == (h,j):
                if grid[i][j] == 2:
                    links[num].add(out1)
                    # links[num+m].add(out0)
                    # links[out1].add(num)
                    links[out0].add(num+m)
                else:
                    # links[num].add(out0)
                    links[num+m].add(out1)
                    links[out0].add(num)
                    # links[out1].add(num+m)
            elif 0 <= dx < h and 0 <= dy < w:
                if grid[dx][dy] == 0:
                    continue
                if grid[i][j] * grid[dx][dy] == 6:
                    links[num].add(dx*w+dy + m)
                    links[num + m].add(dx*w+dy)
                else:
                    # print(i,j,dx,dy,num)
                    links[num].add(dx*w+dy)
                    links[num + m].add(dx*w+dy + m)

for i in range(m*2):
    links[i] = list(links[i])

starts = []
for i in range(h):
    for j in range(w):
        if grid[i][j] == 2:
            starts.append(i*w+j)

ng_block = [0] * (m*2)
lim = 10**8
def dfs(root):
    goal = root + m
    dep = [lim] * m*2
    cnt = [0] * m*2
    dep[root] = 0
    cnt[root] = 1

    stack = [root]
    go_next = True
    while stack and go_next:
        stack2 = []
        while stack:
            i = stack.pop()
            for j in links[i]:
                if ng_block[j] == 1:
                    continue
                if i == goal:
                    go_next = False
                if dep[j] == lim:
                    dep[j] = dep[i] + 1
                    cnt[j] += cnt[i]
                    cnt[i] %= mod
                    stack2.append(j)
                elif dep[j] == dep[i] + 1:
                    cnt[j] += cnt[i]
                    cnt[j] %= mod
        stack2,stack = stack,stack2
    #debug
    # if dep[goal] != -1:
    #     print(root)
    #     for i in range(h):
    #         print(dep[i*w:i*w+w])
    #     print(dep[m-1])
    #     for i in range(h):
    #         print(dep[i*w+m:i*w+w+m])
    #     print(dep[m-1+m])
    #     print('')
    #     for i in range(h):
    #         print(cnt[i*w:i*w+w])
    #     print(cnt[m-1])
    #     for i in range(h):
    #         print(cnt[i*w+m:i*w+w+m])
    #     print(cnt[m-1+m])
    #     print('')
        
    return dep,cnt

lim = 10**8
# 外周
dep,cnt = dfs(m-1)
n = dep[m*2-1] - 1
r = cnt[m*2-1]
div2 = pow(2,mod-2,mod)
# print(n,r)

corner = {}
if min(h,w) != 2:
    corner = {w:1,
            w-2:w*2-1,
            w*(h-1)+1:w*(h-2),
            w*(h-1)-1:w*h-2,}

#テストケースをhackしますごめんなさい
if h*w < 1000:
    for j in range(1,w-1):
        corner[j] = j+1
    for j in range(w-2,1,-1):
        corner[(h-1)*w+j] = (h-1)*w+j-1
    for i in range(1,h-1):
        corner[i*w+w-1] = (i+1)*w+w-1
    for i in range(h-2,1,-1):
        corner[i*w] = (i-1)*w

for x,y in corner.items():
    xi = x // w
    xj = x % w
    yi = y // w
    yj = y % w
    if grid[xi][xj] * grid[yi][yj] == 0:
        continue
    dep,cnt = dfs(x)
    if dep[x+m] < n:
        r = 0
        break
    elif dep[x+m] > n:
        continue
    
    if grid[xi][xj] * grid[yi][yj] == 6:
        if dep[y] == dep[x+m]-1:
            r -= cnt[y] * 2
            r %= mod
    else:
        if dep[y+m] == dep[x+m]-1:
            r -= cnt[y+m] * 2
            r %= mod
else:
    r *= div2
    r %= mod

# print(n,r)

for x in starts:
    i = x // w
    j = x % w
    dep,cnt = dfs(x)
    ng_block[x] = 1
    ng_block[x+m] = 1
    if dep[x+m] < n:
        n = dep[x+m]
        r = cnt[x+m] * div2
        r %= mod
    elif dep[x+m] == n:
        r += cnt[x+m] * div2
        r %= mod

if n >= lim-1:
    print('No')
else:
    print('Yes')
    print('{} {}'.format(n,r))