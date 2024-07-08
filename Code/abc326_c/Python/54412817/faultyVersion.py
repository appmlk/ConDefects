N, M = map(int,input().split())
A = list(map(int,input().split()))
A.sort()

res = 0
r = 0
for l in range(0, N):
  while True:
    if r < N - 1 and A[r + 1] - A[l] < M : r += 1
    else : break
  res = max(res, r - l)
print(res)