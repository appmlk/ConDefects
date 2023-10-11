def solve(N, K, A):
  ans = 10 ** 9
  ls = [(A[i], -i) for i in range(N)]
  ls.sort()
  j = None
  for ai, mi in ls:
    i = -mi
    if i >= K:
      if (not j is None) and A[j] < ai:
        ans = min(i - j, ans)
    else:
      if j: j = max(i, j)
      else: j = i
  if ans > 10 ** 8:
    return -1
  else:
    return ans

N, K = [int(x) for x in input().split()]
A = [int(x) for x in input().split()]

print(solve(N, K, A))
