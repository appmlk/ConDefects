
N, M, K = list(map(int, input().split()))
C = []
A = []
X = []
for _ in range(M):
    buf = input().split()
    C.append(int(buf[0]))
    A.append(list(map(int, buf[1:-1])))
    X.append(buf[-1])

ans = 0
for bit in range(1<<N):
    flg = True
    for i in range(M):
        cnt = 0
        for j in range(C[i]):
            if (bit >> (A[i][j] - 1)) & 0b1:
                cnt += 1
        if cnt >= K and X[i] == "o": continue
        if cnt < K and X[i] == "x": continue
        break
    else:
        ans += 1
        
print(ans)