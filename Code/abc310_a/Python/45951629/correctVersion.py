N, P, Q = map(int, input().split())
D = list(map(int, input().split()))

ans = P
for i in range(N):
    ret = Q + D[i]
    ans = min(ans, ret)

print(ans)
