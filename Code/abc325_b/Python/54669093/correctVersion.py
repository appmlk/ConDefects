N = int(input())
W = [0] * N
X = [0] * N

for i in range(N):
    w, x = (int(x) for x in input().split())
    W[i] = w
    X[i] = x

ans = 0
for i in range(24):
    c = 0
    for j in range(N):
        t = (X[j] + i) % 24
        if 9 <= t < 18:
            c += W[j]
    ans = max(ans, c)

print(ans)

