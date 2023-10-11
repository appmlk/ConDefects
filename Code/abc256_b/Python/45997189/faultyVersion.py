N = int(input())
A = list(map(int, input().split()))
cum_sum = [0] * (N + 1)
for i in range(N):
    cum_sum[i + 1] = cum_sum[i] + A[i]
ans = 0
for i in range(N):
    if 4 < cum_sum[N] - cum_sum[i]:
        ans += 1
    else:
        break
print(ans)