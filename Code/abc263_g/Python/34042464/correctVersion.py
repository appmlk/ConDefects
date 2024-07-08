import sys
readline = sys.stdin.readline


#エラトステネスの篩と素因数分解

from collections import Counter
M = 2*10**7 + 20
prime = [0]*M
for i in range(2, M):
    if prime[i]:
        continue
    for j in range(i, M, i):
        if not prime[j]:
            prime[j] = i

import collections
class Dinic:
    def __init__(self, vnum):
        self.edge = [[] for i in range(vnum)]
        self.n = vnum
        # infはint型の方が良いかもね
        self.inf = float('inf')
    def addedge(self, st, en, c):
        self.edge[st].append([en, c, len(self.edge[en])])
        self.edge[en].append([st, 0, len(self.edge[st])-1])
    def bfs(self, vst):
        dist = [-1]*self.n
        dist[vst] = 0
        Q = collections.deque([vst])
        while Q:
            nv = Q.popleft()
            for vt, c, r in self.edge[nv]:
                if dist[vt] == -1 and c > 0:
                    dist[vt] = dist[nv] + 1
                    Q.append(vt)
        self.dist = dist
    def dfs(self, nv, en, nf):
        nextv = self.nextv
        if nv == en:
            return nf
        dist = self.dist
        ist = nextv[nv]
        for i, (vt, c, r) in enumerate(self.edge[nv][ist:], ist):
            if dist[nv] < dist[vt] and c > 0:
                df = self.dfs(vt, en, min(nf, c))
                if df > 0:
                    self.edge[nv][i][1] -= df
                    self.edge[vt][r][1] += df
                    return df
            nextv[nv] += 1
        return 0
    def getmf(self, st, en):
        mf = 0
        while True:
            self.bfs(st)
            if self.dist[en] == -1:
                break
            self.nextv = [0]*self.n
            while True:
                fl = self.dfs(st, en, self.inf)
                if fl > 0:
                    mf += fl
                else:
                    break
        return mf

def calc(x):
    N = len(D)
    oner = N
    st = N+1
    en = N+2
    As = list(D.keys())
    T = Dinic(en+1)
    for i, a in enumerate(As):
        if a == 1:
            T.addedge(st, i, x)
            T.addedge(oner, en, D[1] - x)
            T.addedge(i, oner, INF)
        else:
            if a&1:
                T.addedge(i, en, D[a])
            else:
                T.addedge(st, i, D[a])
    
    
    for i in range(N):
        for j in range(i):
            if prime[As[i] + As[j]] == As[i]+As[j]:
                ic, jc = i, j
                if As[i]&1:
                    ic, jc = jc, ic
                T.addedge(ic, jc, INF)
    
    
    return T.getmf(st, en)
        
            
            
INF = 2*10**9+7
N = int(readline())
D = Counter()

for _ in range(N):
    a, b = map(int, readline().split())
    D[a] = b
    
l = 0
r = D[1]
D[1] = D[1]
while abs(r-l) > 10:
    m1 = (l*2+r)//3
    m2 = (l+r*2)//3
    c1 = calc(m1)
    c2 = calc(m2)
    if c1 < c2:
        l = m1
    else:
        r = m2

ans = 0
for m in range(l, r+1):
    ans = max(ans, calc(m))

print(ans)
