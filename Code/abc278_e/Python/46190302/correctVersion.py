H, W, N, h, w = map(int, input().split())
A = [list(map(int, input().split())) for _ in range(H)]
ans = [[0] * (W - w + 1) for _ in range(H - h + 1)]
dic = {}
for i in range(H):
    for j in range(W):
        if i >= h or j >= w:
            a = A[i][j]
            if a in dic:
                dic[a] += 1
            else:
                dic[a] = 1
ans[0][0] = len(dic)
for i in range(H - h + 1):
    dic2 = dic.copy()
    for j in range(W - w):
        for k in range(h):
            a = A[i + k][j + w]
            dic2[a] -= 1
            if dic2[a] == 0:
                dic2.pop(a)
            a = A[i + k][j]
            if a in dic2:
                dic2[a] += 1
            else:
                dic2[a] = 1
        ans[i][j + 1] = len(dic2)
    if i < H - h:
        for k in range(w):
            a = A[i + h][k]
            dic[a] -= 1
            if dic[a] == 0:
                dic.pop(a)
            a = A[i][k]
            if a in dic:
                dic[a] += 1
            else:
                dic[a] = 1
        ans[i + 1][0] = len(dic)
for i in range(H - h + 1):
    print(*ans[i])
