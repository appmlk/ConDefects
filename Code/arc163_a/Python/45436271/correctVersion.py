T = int(input())
for _ in range(T):
  N = int(input())
  S = input()
  base = S[0]
  isOk = False
  for i in range(1,N):
    if S[:i] < S[i:]:
      isOk = True
      print("Yes")
      break
  if not isOk:
    print("No")