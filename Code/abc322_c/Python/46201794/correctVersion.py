N, M = map(int, input().split())
ans = [N] * N

for i in map(int, input().split()):
    ans[i-1] = 0
for i in range(N-1, 0, -1):
    ans[i-1] = min(ans[i-1], ans[i]+1)

print('\n'.join(map(str, ans)))
