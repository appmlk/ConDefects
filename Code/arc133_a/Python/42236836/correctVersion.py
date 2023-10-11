N = int(input())
A = list(map(int, input().split()))

tmp = A[0]
for i in range(1, N):
    if tmp > A[i]: break
    tmp = A[i]

for i in A:
    if i != tmp:
        print(i, end=" ")

print()
