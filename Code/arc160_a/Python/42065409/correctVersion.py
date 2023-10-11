N, K = map(int, input().split())
A = list(map(int, input().split()))

fixed1 = 1
fixed2 = N*(N+1)//2
for i, a in enumerate(A):
    l = []
    r = []
    for j in range(i+1, N):
        if A[j] < A[i]:
            l.append(A[j])
        if A[j] > A[i]:
            r.append(A[j])
    value = -1
    if len(l)+fixed1 > K:
        l.sort()
        value = l[K-fixed1]
    if fixed2-K < len(r):
        r.sort(reverse=True)
        value = r[fixed2-K]
    if value != -1:
        idx = A.index(value)
        print(*A[:i], *A[i:idx+1][::-1], *A[idx+1:])
        exit()
    fixed1 += len(l)
    fixed2 -= len(r)
print(*A)
