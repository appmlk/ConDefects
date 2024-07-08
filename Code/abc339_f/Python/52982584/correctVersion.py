import sys
sys.set_int_max_str_digits(0)
input = sys.stdin.readline

from bisect import bisect_left

N = int(input())
A = sorted(int(input()) for _ in range(N))
ans = 0

for i in range(N):
  for j in range(i, N):
    x = A[i] * A[j]
    idx = bisect_left(A, x)
    if idx == N: break
    while idx < N and x == A[idx]:
      ans += 1
      if i != j: ans += 1
      idx += 1

print(ans)