p, b, n, m = map(int, input().split())
lr = [list(map(int, input().split())) for i in range(m)]

if p > 16:
    print("Yes")
    exit()

n += 1
ok = []
for i in range(1 << n):
    for l, r in lr:
        if (1 << (l - 1)) & i and (1 << r) & i:
            ok.append(False)
            break
    else:
        ok.append(True)

t = [1 << 30] * (1 << n)
t[0] = 1
msk = (1 << n) - 1
for i in range((1 << n) - 1):
    j = msk ^ i
    k = j
    while j:
        if ok[j]:
            t[i ^ j] = min(t[i ^ j], t[i] + 1)
        j = (j - 1) & k

print("Yes" if t[-1] <= p else "No")
