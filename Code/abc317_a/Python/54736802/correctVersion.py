N, H, X = map(int,input().split())
P = list(map(int,input().split()))
for i in range(N):
    if X <= H + P[i]:
        print(i+1)
        break