H, W, N = (int(i) for i in input().split())
A = [int(i) for i in input().split()]

D = [0] * 26

for i in range(N):
    D[A[i]] += 1

s = 0
for i in range(25, -1, -1):
    d = (H // 2 ** i) * (W // 2 ** i)
    if d - 4 * s < D[i]:
        print('No')
        exit()
    s = D[i]
print('Yes')
