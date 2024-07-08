n, k = map(int, input().split())
a = list(map(int, input().split()))
l = [a[i + 1] - a[i] for i in range(0, k - 1, 2)]
if k % 2 == 0:
    print(sum(l))
    exit()
r = [a[i + 1] - a[i] for i in reversed(range(1, k - 1, 2))]
l = [0] + l
r = [0] + r
for i in reversed(range(k // 2)):
    l[i + 1] += l[i]
    r[i + 1] += r[i]
for i in range(k // 2 + 1):
    l[i] += r[-(i + 1)]
print(min(l))