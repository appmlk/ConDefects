N,M = map(int, input().split())
A = []
B = []
for _ in range(M):
    A_,B_ = map(int, input().split())
    A.append(A_)
    B.append(B_)

res = [0]*N
for i in range(M):
    res[B[i]-1] = 1

if res.count(0) == 1:
    print(A[res.index(0)])
else:
    print(-1)