#0502

N, H, X = map(int, input().split())
P = list(map(int, input().split()))

for i in range(N):
    if H + P[i] >= X:
        print(i + 1)
        exit()

