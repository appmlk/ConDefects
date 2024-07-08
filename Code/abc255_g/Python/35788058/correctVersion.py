from collections import defaultdict
inf = 2 * 10 ** 18
N, M = map(int, input().split())
A = sorted([int(a) for a in input().split()])[::-1]
X = defaultdict(list)
L = [inf]
for _ in range(M):
    x, y = map(int, input().split())
    X[x].append(x - y)
    L.append(x - y)

for x in X:
    X[x].sort()
SA = sorted(X.keys()) + [inf]
L = sorted(set(L))
count = defaultdict(int)
s = 0
ma = 0
pre_x = 0
ii = 0
G = {}
for x in SA:
    while A and A[-1] < x:
        grundy = ma + (A.pop() - pre_x)
        s ^= grundy
    
    while L[ii] < x:
        G[L[ii]] = ma + (L[ii] - pre_x)
        ii += 1
    
    mi = inf
    for y in X[x]:
        g = G[y]
        count[g] -= 1
        if count[g] < 0:
            mi = min(mi, g)
    for y in X[x]:
        g = G[y]
        count[g] += 1
    
    if mi < inf:
        grundy = mi
        count[grundy] += 1
        pre_x += 1
    else:
        ma += x - pre_x
        grundy = ma
        pre_x = x
    
    while A and A[-1] == x:
        s ^= grundy
        A.pop()
    if L[ii] == x:
        G[L[ii]] = grundy
        ii += 1
    
print("Takahashi" if s else "Aoki")