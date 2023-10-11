n = int(input())
A = list(map(int, input().split()))

p = int(input())
X = list(map(int, input().split()))

q = int(input())
Y = list(map(int, input().split()))

MOD1 = 10 ** 9 + 7
MOD2 = 10 ** 9 + 7
base = 123451000

hX = [0]
hX2 = [0]
for v in X:
    nex = (hX[-1] * base) + v
    hX.append(nex % MOD1)

    nex = (hX2[-1] * base) + v
    hX2.append(nex % MOD2)
    
hY = [0]
hY2 = [0]
for v in Y:
    nex = (hY[-1] * base) + v
    hY.append(nex % MOD1)

    nex = (hY2[-1] * base) + v
    hY2.append(nex % MOD2)

hA = [0]
hA2 = [0]
for v in A:
    nex = (hA[-1] * base) + v
    hA.append(nex % MOD1)

    nex = (hA2[-1] * base) + v
    hA2.append(nex % MOD2)

lo = 0
hi = min(p, q) + 1
while hi - lo > 1:
    mid = (lo + hi) // 2

    mult = pow(base, mid, MOD1)
    mult2 = pow(base, mid, MOD2)

    wX = []
    for i in range(p - mid + 1):
        h1 = (hX[i + mid] - mult * hX[i]) % MOD1
        h2 = (hX2[i + mid] - mult2 * hX2[i]) % MOD2
        wX.append((h1 << 30) + h2)

    wY = []
    for i in range(q - mid + 1):
        h1 = (hY[i + mid] - mult * hY[i]) % MOD1
        h2 = (hY2[i + mid] - mult2 * hY2[i]) % MOD2
        wY.append((h1 << 30) + h2)

    good = True

    seen = set(wX)
    
    for v in wY:
        if v in seen:
            good = False
            break

    if good:
        hi = mid
    else:
        lo = mid

if lo > 0:
    print(p + q - 2 * lo)
    exit()

'''
xLoc = [0] * (n + 1)
mult = pow(base, p, MOD1)
mult2 = pow(base, p, MOD2)
for i in range(n - p + 1):
    h1 = (hA[i + p] - mult * hA[i]) % MOD1
    h2 = (hA2[i + p] - mult2 * hA2[i]) % MOD2
    if h1 == hX[p] and h2 == hX2[p]:
        xLoc[i] += 1
        xLoc[i + p] -= 1

yLoc = [0] * (n + 1)
mult = pow(base, q, MOD1)
mult2 = pow(base, q, MOD2)
for i in range(n - q + 1):
    h1 = (hA[i + q] - mult * hA[i]) % MOD1
    h2 = (hA2[i + q] - mult2 * hA2[i]) % MOD2
    if h1 == hY[q] and h2 == hY2[q]:
        yLoc[i] += 1
        yLoc[i + q] -= 1


distX = {}
distY = {}

last = -n - 100
cX = 0
for i in range(n):
    cX += xLoc[i]
    if cX > 0:
        last = i

    v = A[i]
    d = i - last
    if v in distX:
        distX[v] = min(distX[v], d)
    else:
        distX[v] = d
last = n + 100
cX = 0
for i in range(n-1,-1,-1):
    cX -= xLoc[i+1]
    if cX > 0:
        last = i

    v = A[i]
    d = last - i
    if v in distX:
        distX[v] = min(distX[v], d)
    else:
        assert False
        distX[v] = d

last = -n - 100
cY = 0
for i in range(n):
    cY += yLoc[i]
    if cY > 0:
        last = i

    v = A[i]
    d = i - last
    if v in distY:
        distY[v] = min(distY[v], d)
    else:
        distY[v] = d
last = n + 100
cY = 0
for i in range(n-1,-1,-1):
    cY -= yLoc[i+1]
    if cY > 0:
        last = i

    v = A[i]
    d = last - i
    if v in distY:
        distY[v] = min(distY[v], d)
    else:
        assert False
        distY[v] = d

poss = []
for v in A:
    poss.append(distX[v] + distY[v])'''


from collections import defaultdict, deque

base = p + q - 2

adj = defaultdict(list)
dist = dict()
for i in range(n - 1):
    adj[A[i]].append(A[i + 1])
    adj[A[i + 1]].append(A[i])

q = deque()

for v in X:
    if v not in dist:
        dist[v] = 0
        q.append(v)

while q:
    u = q.popleft()

    for v in adj[u]:
        if v not in dist:
            dist[v] = dist[u] + 1
            q.append(v)

poss = []
for v in Y:
    poss.append(dist[v])
best = min(poss)
print(base + 2 * best)

    
