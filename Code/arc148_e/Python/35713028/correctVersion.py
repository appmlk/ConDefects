from collections import Counter
P = 998244353
N, K = map(int, input().split())
nn = N + 10
fa = [1] * (nn+1)
fainv = [1] * (nn+1)
for i in range(nn):
    fa[i+1] = fa[i] * (i+1) % P
fainv[-1] = pow(fa[-1], P-2, P)
for i in range(nn)[::-1]:
    fainv[i] = fainv[i+1] * (i+1) % P
C = lambda a, b: fa[a] * fainv[b] % P * fainv[a-b] % P if 0 <= b <= a else 0

A = [int(a) * 2 - K for a in input().split()]
R = [a for a in A if a >= 0]
L = [-a for a in A if a < 0]
SS = sorted(set(R + L), key = lambda x: -x)
CR = Counter(R)
CL = Counter(L)
ans = 1
pos = 1
for i in SS:
    a = CR[i]
    ans = ans * C(pos + a - 1, a) % P
    pos += a
    a = CL[i]
    ans = ans * C(pos, a) % P
    pos -= a
print(ans)