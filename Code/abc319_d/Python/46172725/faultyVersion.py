N, M = map(int, input().split())
L = list(map(int, input().split()))

ng, ok = max(L), sum(L) + N - 1
while ng + 1 < ok:
    chk = (ng + ok) // 2
    
    m = 1
    w = L[0]
    for l in L[1:]:
        if (w + 1 + l) > chk:
            m += 1
            w = l
        else:
            w += 1 + l

    if m <= M:
        ok = chk
    else:
        ng = chk

print(ok)