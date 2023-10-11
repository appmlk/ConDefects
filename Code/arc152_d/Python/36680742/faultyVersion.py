from math import gcd


N, K = map(int, input().split())
if N % 2 == 0:
    exit(print(-1))


g = gcd(N, K)
a = N // g
x = K // g
ans = []
if g == 1:
    for i in range((a - 1) // 2):
        ans.append((2 * i, 2 * i + 1))
else:
    for i in range((a - 1) // 2):
        base = 2 * g * i
        for j in range(g // 2):
            ans.append((base + 2 * j, base + 2 * j + 1))
            ans.append((base + 2 * j, base + 2 * j + g))
        ans.append((base + g - 1, base + 2 * g - 1))
    for j in range(g // 2):
        ans.append(((a - 1) * g + 1 + 2 * j, (a - 1) * g + 1 + 2 * j + 1))
    

print(len(ans))
for u, v in ans:
    print((u * x) % N, (v * x) % N)

