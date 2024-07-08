n = int(input())
A = list(map(int, input().split()))
count = 0

for i in range(1, n*2-1):
    if A[i-1] == A[i+1]:
        count += 1

print(count)