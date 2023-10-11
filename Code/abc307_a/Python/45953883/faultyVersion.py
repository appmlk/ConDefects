N = int(input())
A = list(map(int,input().split()))

res = [0]*N
SUM = 0
for i in range(len(A)):
    SUM += A[i]
    if (i + 1)%7==0:
        res[i//7] = SUM
        SUM = 0

print(res)