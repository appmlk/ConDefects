N = int(input())
A = [0 for _ in range(24)]
for _ in range(N) :
  w, x = map(int, input().split())
  A[x] += w
A += A

res = 0
for i in range(0, 24) :
  res = max(res, sum(A[9 + i : 18 + i]))
print(res)