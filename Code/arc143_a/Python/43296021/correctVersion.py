A, B, C = map(int, input().split())
L = [A, B, C]
L.sort()
if L[0] + L[1] < L[2]:
    print(-1)
else:
    # A = B
    d = L[1] - L[0]
    L[1] -= d
    L[2] -= d
    cnt = d

    cnt += (L[2] - L[0]) * 2
    cnt += L[0] - (L[2] - L[0])
    print(cnt)