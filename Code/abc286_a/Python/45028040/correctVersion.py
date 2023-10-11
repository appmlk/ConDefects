N,P,Q,R,S = map(int, input().split())
A = list(map(int, input().split()))

ans = A[:P-1] + A[R-1:S] + A[Q:R-1]+ A[P-1:Q] + A[S:]
print(*ans)