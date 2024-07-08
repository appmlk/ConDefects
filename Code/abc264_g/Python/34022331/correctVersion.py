def SPFA(g, start):#g: グラフ, start: 始点
    from collections import deque
    n = len(g)
    relax_max = [n]*n #緩和回数
    is_inQ = [False]*n #キューに入っているか
    dist = [float("inf")]*n
    dist[start] = 0
    
    q = deque([start])
    while q:
        v = q.popleft()
        is_inQ[v] = False
        for to,cost in g[v]:
            if dist[to] <= dist[v] + cost: continue
            dist[to] = dist[v] + cost 
            if is_inQ[to]: continue
            q.append(to)
            is_inQ[to] = True
            relax_max[v] -= 1
            if relax_max[v] < 0: return None, False #負回路
 
    return dist, True


import sys
readline = sys.stdin.readline

#n = int(readline())
#*a, = map(int,readline().split())
#uv = [list(map(int,readline().split())) for _ in range(m)]

INF = 1<<60
tot = offset = 27**3
midpt = 27**2

d1 = [0]*tot
d2 = [0]*tot
d3 = [0]*tot
D = [d1,d1,d2,d3]

def s2n(s):
    r = 0
    for i in s:
        r *= 27
        r += ord(i) - 96
    return r

n = int(readline())
for _ in range(n):
    t,p = readline().split()
    D[len(t)][s2n(t)] = int(p)

"""
?: 0, a: 1, ..., z: 26
"""


dist = []
for i in range(offset):
    dist.append([(offset + i%midpt,0)])
for j in range(midpt):
    r = []
    j0 = j%27*27
    for k in range(1,27):
        v = 0
        v += d1[k]
        v += d2[j0+k]
        v += d3[j*27+k]
        r.append((j*27 + k,-v))
    dist.append(r)

D, tf = SPFA(dist,0)
print(-min(D[1:offset]) if tf else "Infinity")


