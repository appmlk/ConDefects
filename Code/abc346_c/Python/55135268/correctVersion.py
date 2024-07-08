N, K = map(int, input().split())

A = list(map(int, input().split()))

sa = set(A)

dis = 0
for a in sa:
  if a <= K:
    dis += a

print((K + 1) * K // 2 - dis)