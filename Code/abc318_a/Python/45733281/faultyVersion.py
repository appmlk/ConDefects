N, M, P = map(int, input().split())

ans = 0
if N > M:
    ans += 1 + int((N - M) / P)
print(ans)