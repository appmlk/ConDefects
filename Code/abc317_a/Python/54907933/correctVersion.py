N, H, X = map(int, list(input().split()))
P = list(map(int, input().split()))

min_num = 999999
result = 0
for i in range(N):
    if H + P[i] >= X:
        if min_num > H + P[i]:
            min_num = H + P[i]
            result = i + 1

print(result)
