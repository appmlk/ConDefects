N = int(input())
A = list(map(int, input().split()))
C = sorted(A)
B = []
ans = 0
for i in range(int(N/2),N):
    ans += C[i]

for i in range(N):
    if A[i] < C[int(N/2)]:
        B.append(1)
    else :
        B.append(-1)
sum = 0
minimum = 0
minkey = -1
for i in range(N):
    sum += B[i]
    if sum < minimum:
        minimum = sum
        minkey = i
if minkey + 1 == N:
    minkey = 0
print(minkey + 1)
print(ans)