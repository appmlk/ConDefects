N, K = map(int, input().split())
A = set(map(int, input().split()))

sum = K * (K + 1) // 2
for a in A:
    if 1 <= a <= K:
        sum -= a
print(sum)
