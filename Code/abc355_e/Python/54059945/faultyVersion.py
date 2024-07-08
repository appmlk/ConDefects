import sys
from collections import deque
INF = 1 << 19

N, L, R = map(int, sys.stdin.readline().rstrip().split())
R += 1
E = [[] for _ in range(2**N + 1)]

# 質問できる組み合わせに辺を張る
for i in range(N + 1):
    for j in range(2**N):
        if pow(2, i) * (j + 1) > 2**N:
            break

        l = pow(2, i) * j
        r = pow(2, i) * (j + 1)
        E[l].append(r)
        E[r].append(l)

# print(E)

# L -> R の最短距離を調べる
D = [INF] * (2**N + 1)

q = deque()
D[L] = 0
q.append(L)

while q:
    u = q.popleft()

    for v in E[u]:
        if D[v] <= D[u] + 1:
            continue

        D[v] = D[u] + 1
        q.append(v)

# print(D)

# L -> R の経路を復元する
P = [R]

u = R
for d in range(D[R] - 1, -1, -1):
    for v in E[u]:
        if D[v] == d:
            P.append(v)
            u = v
            break

P.reverse()

# print(P)


def question(L, R):

    i, j = 0, 0
    for k in range(N + 1):
        b = pow(2, k)
        if (R + 1) // b - L // b == 1:
            i = k
            j = L // b
            break

    print(f"? {i} {j}", flush=True)
    return int(sys.stdin.readline().rstrip())


ans = 0
for s in range(len(P) - 1):
    L = P[s]
    R = P[s + 1]

    coef = 1
    if L > R:
        L, R = R, L
        coef = -1

    ans += question(L, R) * coef

print(f"! {ans % 100}", flush=True)
