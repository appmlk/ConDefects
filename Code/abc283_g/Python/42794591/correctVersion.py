n, l, r = map(int, input().split())
a = list(map(int, input().split()))

base = []
for i in a:
    for j in base:
        i = min(i, i ^ j)
    if i:
        base.append(i)

base.sort()
for i in range(len(base) - 1, -1, -1):
    for j in range(i - 1, -1, -1):
        base[i] = min(base[i], base[i] ^ base[j])


ans = []
l -= 1
while l != r:
    sm = 0
    for i in range(len(base)):
        if (1 << i) & l:
            sm ^= base[i]
    l += 1
    ans.append(sm)

print(*ans)
