n = int(input())
A = list(map(int, input().split()))
que = [[0 for i in range(n * 2 + 100)] for i in range(11)]
ql = [n + 2 for i in range(11)]
qr = [n + 2 for i in range(11)]
l = 0; r = -1
ans = 0
while l <= n - 1:
    fl = 0
    while fl == 0 and r <= n - 1:
        for i in range(11):
            if qr[i] > ql[i] and fl == 0:
                for j in range(11):
                    if qr[j] > ql[j]:
                        k = j * 2 - i
                        if i == j:
                            if qr[j] - ql[j] < 3:
                                continue
                        if k in range(11) and qr[k] > ql[k]:
                            # print('nownumber:', i, j, k)
                            L = que[i][ql[i] + 1]
                            ql[i] += 1
                            R = que[k][qr[k]]
                            qr[k] -= 1
                            liml = n
                            limr = -1
                            # print(L, R)
                            bl = ql[j] + 1; br = qr[j]
                            # print(bl, br)
                            while bl <= br:
                                mid = (bl+br) // 2
                                # print(mid, que[j][mid])
                                if que[j][mid] > L:
                                    liml = mid; br = mid - 1
                                else:
                                    bl = mid + 1
                            # print(liml)
                            bl = ql[j] + 1; br = qr[j]
                            # print(bl, br)
                            while bl <= br:
                                mid = (bl+br) // 2
                                if que[j][mid] < R:
                                    limr = mid; bl = mid + 1
                                else:
                                    br = mid - 1
                            # print(limr)
                            ql[i] -= 1
                            qr[k] += 1
                            if liml <= limr:
                                fl = 1
                                break
        # print('result :: ', fl, l, r)
        # for i in range(11):
        #     print(i, end = ': ')
        #     for o in range(ql[i] + 1, qr[i] + 1):
        #         print(que[i][o], end=' ')
        #     print('\n')
        if fl == 1:
            break
        if r == n - 1:
            break
        r += 1
        qr[A[r]] += 1
        que[A[r]][qr[A[r]]] = r
    # print(fl, l, r)
    if fl == 1:
        ans += n - r
    if l == n - 1:
        break
    ql[A[l]] += 1
    l += 1
print(ans)
