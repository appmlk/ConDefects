n, m, k = map(int, input().split())
a = list(map(int, input().split()))
r = 1 << 40
l = 0
while r - l > 1:
    mid = (r + l) // 2
    c = []
    for i in a:
        msk = (1 << 30) - 1
        imid = mid
        while i > imid:
            msk >>= 1
            i &= msk
            imid &= msk
        c.append(imid - i)
    c.sort()
    if sum(c[:k]) <= m:
        l = mid
    else:
        r = mid

print(l)
