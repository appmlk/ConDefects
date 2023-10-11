import sys

read = sys.stdin.read
readline = sys.stdin.readline
readlines = sys.stdin.readlines


N, *S = map(int, read().split())

L = [0, 0, 0]
mi = [0, 0, 0]

for i in range(N - 1):
    d = S[i + 1] - S[i]
    L.append(L[-3] + d)
    mi[i % 3] = min(mi[i % 3], L[-1])

for i in range(N + 2):
    L[i] -= mi[i % 3]

for i in range(N):
    S[i] -= sum(L[i:i+3])

if len(set(S)) != 1 or len(set(S)) == 1 and S[0] < 0:
    print('No')
else:
    print('Yes')
    num = S[0]
    for i in range(N + 2):
        if i % 3 == 0:
            L[i] += num
    print(*L)
