from random import randrange
from time import time
def calc(t):
    X.sort(key = lambda x: -x[0] * t * 2 - x[1])
    su1, su0 = 0, 0
    for i in range(K):
        su1 += X[i][0]
        su0 += X[i][1]
    return (1 if su1 > t else -1, su1 ** 2 + su0)

sTime = time()
N, K = map(int, input().split())
C = [int(a) * 36 for a in input().split()]
X = []
for i in range(N):
    b = [int(a) for a in input().split()]
    s1 = sum(b)
    s2 = sum([a ** 2 for a in b])
    X.append((s1, 6 * s2 - s1 ** 2 - C[i]))

sss = sorted([x[0] for x in X])
ans = - 10 ** 18
while time() - sTime < 1.8:
    l, r = sum(sss[:K]), sum(sss[-K:])
    m = randrange(l, r + 1)
    d, ret = calc(m)
    if d > 0:
        l = m
    else:
        r = m
    ans = max(ans, ret)
    while r - l > 1:
        m = l + r >> 1
        d, ret = calc(m)
        ans = max(ans, ret)
        if d > 0:
            l = m
        else:
            r = m
print(ans * 859599304 % 998244353)