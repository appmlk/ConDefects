N = int(input())
S = list(str(input()))
C = list(map(int, input().split()))


A = []
B = []

for i in range(N+1):
    if i == 0:
        A.append(0)
        B.append(0)
    elif int(S[i-1]) == i % 2: 
        A.append(A[i-1] + C[i-1])
        B.append(B[i-1])
    else:
        A.append(A[i-1])
        B.append(B[i-1] + C[i-1])

ans = 10 ** 20
for j in range(N):
    ans = min(ans, A[j]-A[0] + B[N]-B[j], B[j]-B[0] + A[N]-A[j])

print(ans)
