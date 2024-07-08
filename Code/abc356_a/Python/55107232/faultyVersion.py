N, L, R = map(int, input().split())

A = [0] + [i for i in range(1, N+1)]

Larr = A[:L]
center = reversed(A[L:R+1])
Rarr = A[R+1:]

ans = Larr + list(center) + Rarr
ans = ans[1:]

print(ans)
