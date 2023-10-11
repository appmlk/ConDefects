import sys
input = lambda: sys.stdin.readline().rstrip()
ii = lambda: int(input())
mi = lambda: map(int, input().split())
li = lambda: list(mi())
INF = 2 ** 63 - 1
mod = 998244353

n, m, d = mi()

r = li()

s = li()

L = []
R = []
S = []
for i in range(m - 1, 0, -1):
    L.append(-r[i + 1])
    R.append(-r[i])
    S.append(s[i])
L.append(-r[1])
R.append(r[1] + 1)
S.append(s[0])

for i in range(1, m):
    L.append(r[i] + 1)
    R.append(r[i + 1] + 1)
    S.append(s[i])

x = [0] * (d + 1)
a = -(n * d // 2)
for i in range(2 * m - 1):
    if L[i] - a >= n * d:
        x[0] -= n * S[i]
        x[d] += n * S[i]
    elif L[i] - a >= 1:
        x[0] -= (L[i] - a + d) // d * S[i]
        x[(L[i] - a) % d] += S[i]
        x[d] += (L[i] - a) // d * S[i]
    if R[i] - a >= n * d:
        x[0] += n * S[i]
        x[d] -= n * S[i]
    elif R[i] - a >= 1:
        x[0] += (R[i] - a + d) // d * S[i]
        x[(R[i] - a) % d] -= S[i]
        x[d] -= (R[i] - a) // d * S[i]
ans = 0
now = 0
for i in range(0, d):
    now += x[i]
    ans = max(ans, now)

print(ans)




