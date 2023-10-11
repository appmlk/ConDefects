M = int(input())
D = list(map(int, input().split()))

mid = (sum(D) + 1) // 2

for i in range(M):
    mid -= D[i]
    if mid < 0:
        print(i+1, mid+D[i])
        break
    elif mid == 0:
        print(i+1, D[i])
        break