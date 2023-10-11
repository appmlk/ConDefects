N, M = map(int, input().split())
A = list(map(int, input().split()))

A.sort()
sum_a = sum(A)
ans = sum_a
cnt = A[0]
flag = False
zero = 0
if A[0] == 0:
    flag = True
for i in range(N-1):
    if A[i] == A[i+1] or A[i] + 1 == A[i+1]:
        cnt += A[i+1]
        if flag:
            zero = cnt
    else:
        cnt = A[i+1]
        flag = False
    ans = min(ans, sum_a - cnt)
ans = min(ans, sum_a - cnt)

if A[-1] == M - 1:
    ans = min(ans, sum_a - cnt - zero)
print(max(ans, 0))