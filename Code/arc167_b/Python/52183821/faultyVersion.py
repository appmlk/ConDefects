a, b = map(int, input().split())
i = 2
c = []
while i * i <= a:
    if a % i == 0:
        t = 0
        while a % i == 0:
            t += 1
            a //= i
        c.append(t)

    i += 1

if a > 1:
    c.append(1)
M = 998244353
res = 1
for i in c:
    res = res * (i * b + 1) % M

res = res * b % M
res = res * (M+1) // 2 % M
print(res)
