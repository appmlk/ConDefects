n, t, l = map(int, input().split())
mat = [[t+1]*n for i in range(n)]
for i in range(1, t+1):
    u, v = map(int, input().split())
    mat[u-1][v-1] = i
def mul(A, B):
    res = [[t+1]*n for i in range(n)]
    for i in range(n):
        for j in range(n):
            for k in range(n):
                res[i][k] = min(res[i][k], max(A[i][j], B[j][k]))
    return res

def mat_pow(mat, k):
    res = [[t+1]*n for i in range(n)]
    for i in range(n):
        res[i][i] = 0
    while k:
        if k&1:
            res = mul(res, mat)
        mat = mul(mat, mat)
        k >>= 1
    return res

res = mat_pow(mat, l)
res = res[0]
for i in range(n):
    if res[i] > t:
        res[i] = -1
print(*res)

