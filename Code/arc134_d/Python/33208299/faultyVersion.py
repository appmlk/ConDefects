import sys
input = lambda: sys.stdin.readline().rstrip()
ii = lambda: int(input())
mi = lambda: map(int, input().split())
li = lambda: list(mi())
INF = 2 ** 63 - 1
mod = 998244353

n = ii()

a = li()

a1 = a[:n]
a2 = a[n:]

p = min(a1)
t =[]
ind = -1
for i in range(n):
    if a1[i] == p:
        t.append((a1[i], a2[i]))
        ind = i
if sorted(t)[0][1] <= p:
    print(*sorted(t)[0])
else:
    ans1 = []
    ans2 = []
    for v1, v2 in t:
        ans1.append(v1)
        ans2.append(v2)
    p = []
    for i in range(ind + 1, n):
        if a1[i] < ans2[0]:
            p.append((a1[i], i))
    p.sort()
    nowi = ind

    for v, i in p:
        if nowi < i:
            ans1.append(a1[i])
            ans2.append(a2[i])
    
    ans = ans1 + ans2
    ans1 = []
    ans2 = []
    for v1, v2 in t:
        ans1.append(v1)
        ans2.append(v2)
    p = []
    for i in range(ind + 1, n):
        if a1[i] <= ans2[0]:
            p.append((a1[i], i))
    p.sort()
    nowi = ind

    for v, i in p:
        if nowi < i:
            ans1.append(a1[i])
            ans2.append(a2[i])
    
    ans = min(ans, ans1 + ans2)
    print(*ans)
