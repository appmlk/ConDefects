N, X = map(int, input().split())
sums = [0] * N
a, b = [], []
for i in range(N):
    ai, bi = map(int, input().split())
    a.append(ai)
    b.append(bi)
    sums[i] = ai + bi
for i in range(1, N):
    sums[i] += sums[i - 1]
ans = [0] * N
INF = 10 ** 10
for i in range(N):
    if X - (i + 1) < 0:
        ans[i] = INF
    else:
        ans[i] = sums[i] + b[i] * (X - (i + 1))
print(min(ans))