N = int(input())
a = [list(map(int, input().split())) for _ in range(N)]
a.sort(key=lambda x: abs(x[0]-x[1]))

cnt = 0
for i in range(N):
    if a[i][0] > a[i][1]:
        cnt += 1
ans = max(a[0]) if cnt & 2 == 0 else min(a[0])
for i in range(1, N):
    ans += max(a[i])

print(ans)
