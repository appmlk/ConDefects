N, L, R = map(int, input().split())

ans = 0
for k in range(100):
    if N&(1<<k):
        rr = (1 << (k+1)) -1
        ll = 1 << k
        nums = rr - ll + 1
        if R < rr:
            nums -= (rr-R)
        if ll < L:
            nums -= (L-ll)
        ans += nums

print(ans)
