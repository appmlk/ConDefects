N, K = map(int, input().split())
A = list(map(int, input().split()))

if K % 2 != 0:
    now = 0
    for i in range(1, K-1, 2):
        now += A[i+1] - A[i]
    ans = now
    for i in range(2, K, 2):
        now += A[i-1] - A[i-2]
        now -= A[i] - A[i-1]
        ans = min(ans, now)
    print(ans)
    exit()
ans = 0
for i in range(0, K-1, 2):
    ans += A[i+1] - A[i]
print(ans)
    