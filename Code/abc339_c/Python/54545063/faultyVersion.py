N = int(input())
A = list(map(int, input().split()))

total = 0
min_diff = float('inf')

for i in range(N):
    total += A[i]
    min_diff = min(min_diff, total)

print(total + abs(min_diff))