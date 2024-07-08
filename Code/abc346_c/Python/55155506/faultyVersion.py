N, K = map(int, input().split())
A = list(map(int, input().split()))

ans = K*(K+1)//2
for a in set(A):
    if a < K:
        ans -= a
print(ans)