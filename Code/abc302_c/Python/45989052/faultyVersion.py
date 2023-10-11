import itertools

def diff(A, B):
  res = 0
  for a, b in zip(A, B):
    if a != b:
      res += 1
  return res

N, M = map(int, input().split())
S = [input() for _ in range(N)]

for T in itertools.permutations(S):
  ok = True
  for i in range(N-1):
    if diff(T[i], T[i+1]) != 1:
      ok = False
  if ok:
    print("Yes")
    break
print("No")
      