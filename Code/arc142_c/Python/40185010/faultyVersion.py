N = int(input())

def question(u, v):
    print('?', u, v)
    d = int(input())
    return d

INF = 1<<62
G = [[] for _ in range(N)]

dist0 = [INF] * N
dist0[0] = 0
dist1 = [INF] * N
dist1[1] = 0

for i in range(2, N):
    dist0[i] = question(1, i+1)
    dist1[i] = question(2, i+1)

cnt = 0
x = []
mn_dist = INF
for i in range(N):
    mn_dist = min(mn_dist, dist0[i] + dist1[i])
    if dist0[i]+dist1[i] == 3:
        cnt += 1
        x.append(i)

if mn_dist != 3:
    print('!', mn_dist)
else:
    if cnt != 2:
        print('!', 1)
    else:
        d = question(x[0], x[1])
        if d == 1:
            print('!', mn_dist)
        else:
            print('!', 1)