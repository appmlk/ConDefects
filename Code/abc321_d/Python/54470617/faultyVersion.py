N, M, P = map(int, input().split())
A = list(map(int, input().split()))
B = list(map(int, input().split()))
B.sort()
S = [B[0]]
for j in range(1, M) : S.append(B[j] + S[j - 1])

res = 0
for i in range(N) :
  l, r = -1, M
  while l + 1 < r :
    m = (l + r) // 2
    if A[i] + B[m] <= P : l = m
    else : r = m
  res += (S[l] + A[i] if l >= 0 else 0) + P * (M - r)
print(res)