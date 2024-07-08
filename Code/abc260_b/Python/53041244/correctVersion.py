n, x, y, z = map(int, input().split())
a = [(ai, -i - 1) for i, ai in enumerate(map(int, input().split()))]
b = [(bi, -i - 1) for i, bi in enumerate(map(int, input().split()))]

total = sorted([(ai[0] + bi[0], ai[1]) for ai, bi in zip(a, b)], reverse=True)
a.sort(reverse=True)
b.sort(reverse=True)

ans = set()

for i in range(x):
    ans.add(-a[i][1])

j = 0
for i in range(n):
    if j == y:
        break
    if -b[i][1] in ans:
        continue
    j += 1
    ans.add(-b[i][1])

j = 0
for i in range(n):
    if j == z:
        break
    if -total[i][1] in ans:
        continue
    j += 1
    ans.add(-total[i][1])

print(*sorted(ans), sep='\n')
