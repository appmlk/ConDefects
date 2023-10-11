N = int(input())
H = list(map(int, input().split()))
ans = 0
for i in range(N -1):
    if H[i] < H[i + 1] :
        ans = H[i + 1]
    elif H[0] >= H[1]:
        ans = H[0]
    else: break
print(ans)
