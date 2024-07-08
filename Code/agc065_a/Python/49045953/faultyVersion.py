N, K = map(int, input().split())
A = list(map(int, input().split()))
d = {}
for a in A:
    if a not in d:
        d[a] = 0
    d[a] += 1
n = 0
for a in d:
    n = max(n, d[a])
M = []
for a in d:
    if n == d[a]:
        M.append(a)
M.sort()
M.reverse()
m = len(M)
ans = M[m - 1] - M[0] + (N - n) * K
for j in range(m - 1):
    cand = M[j + 1] - M[j] + (N - n - 1) * K
    ans = max(ans, cand)
print(ans)