from collections import defaultdict
from string import ascii_letters, ascii_lowercase, ascii_uppercase
from collections import deque
import sys
input = sys.stdin.readline
sys.setrecursionlimit(10**9)

INF = 10**18
numChar = 26
numChar2 = numChar*numChar

N = int(input())
scores = defaultdict(int)
for _ in range(N):
    Ts, P = input().split()
    P = int(P)
    scores[Ts] = -P

adjL = [[] for _ in range(numChar2)]
for ix in range(numChar):
    x = ascii_lowercase[ix]
    for iy in range(numChar):
        y = ascii_lowercase[iy]
        Ss = x+y
        v = ix*numChar + iy
        adjLNow = []
        for iz in range(numChar):
            z = ascii_lowercase[iz]
            S2s = y+z
            v2 = iy*numChar + iz
            wt = scores[z] + scores[S2s] + scores[Ss+z]
            adjLNow.append((v2, wt))
        adjL[v] = adjLNow

def BellmanFord(adjList, INF):
    numV = len(adjList)
    negINF = -INF
    costs = [INF] * numV
    for ix in range(numChar):
        x = ascii_lowercase[ix]
        for iy in range(numChar):
            y = ascii_lowercase[iy]
            Ss = x+y
            v = ix*numChar + iy
            costs[v] = scores[x] + scores[y] + scores[Ss]
    prevs = [-1] * numV
    vNegINFs = set()
    for tm in range(numV):
        isChanged = False
        for vNow in range(numV):
            cNow = costs[vNow]
            if cNow == INF: continue
            for v2, wt in adjList[vNow]:
                c2 = cNow + wt
                if c2 < costs[v2]:
                    if tm == numV-1:
                        costs[v2] = negINF
                        vNegINFs.add(v2)
                    else:
                        costs[v2] = c2
                    prevs[v2] = vNow
                    isChanged = True
        if not isChanged:
            return (costs, prevs)
    QQQ = deque(vNegINFs)
    while QQQ:
        vNow = QQQ.popleft()
        for v2, wt in adjList[vNow]:
            if costs[v2] == negINF: continue
            costs[v2] = negINF
            QQQ.append(v2)
    return (costs, prevs)

costs, prevs = BellmanFord(adjL, INF)

ans = INF
for x in ascii_lowercase:
    s = scores[x]
    if s < ans:
        ans = wt
#print('# ans:', ans)

minC = min(costs)
#print('# costs:', costs, '/ minC:', minC)
if minC < ans:
    ans = minC

ans = -ans
if ans == INF:
    print('Infinity')
else:
    print(ans)
