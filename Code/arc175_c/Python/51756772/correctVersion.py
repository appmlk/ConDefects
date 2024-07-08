N = int(input())
LR = []
for _ in range(N):
    l, r = map(int, input().split())
    LR.append((l, r))
D = []
INF = 10**18
mi, ma = -INF, INF
for l, r in reversed(LR):
    if ma < l:
        mi = ma = l
    elif r < mi:
        mi = ma = r
    else:
        mi = max(mi, l)
        ma = min(ma, r)
    D.append((mi, ma))
D.reverse()
ans = []
v = D[0][0]
for (mi, ma), (l, r) in zip(D, LR):
    v = max(l, min(v, ma))
    ans.append(v)
print(*ans)
