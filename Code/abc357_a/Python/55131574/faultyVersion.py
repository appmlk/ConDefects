N,M = map(int, input().split())
H = list(map(int, input().split()))

for i in range(N):
    M = M-H[i]
    if M == 0:
        print(i+1)
        break
    if M < H[i]:
        print(i)
        break

if M > 0:
    print(N)