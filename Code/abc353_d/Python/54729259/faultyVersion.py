def prefix_sum(A) :
  N = len(A)
  res = [0 for i in range(N)]
  res[0] = A[0]
  for i in range(1, N) : res[i] = A[i] + res[i - 1]
  return res

N = int(input())
A = list(map(int, input().split()))
S = list(reversed(prefix_sum(list(reversed(A)))))

dit = [10 ** len(str(A[i])) for i in range(N)]
ditsum = list(reversed(prefix_sum(list(reversed(dit)))))

res = 0
for i in range(N - 1) :
  res += A[i] * ditsum[i + 1] + S[i + 1]
print(res)