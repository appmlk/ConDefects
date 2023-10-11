n = int(input())
H = list(map(int, input().split()))

count = 0
for i in range(n-1):
    if H[i] < H[i+1]:
        count += 1
    else:
        break
print(H[count])

