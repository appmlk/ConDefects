import sys
input = sys.stdin.readline

for _ in range(int(input())):
  N = int(input())
  a = list(map(int, input().split()))
  b = list(map(int, input().split()))

  sa = set(a)
  sb = set(b)
  if sa | sb != sa:
    print("No")
    continue

  la = [a[0]]
  lb = [b[0]]
  for i in range(1, N):
    if la[-1] != a[i]: la.append(a[i])
    if lb[-1] != b[i]: lb.append(b[i])


  if sa == sb and len(sa) == N:
    if a == b:
      print("Yes")
    else:
      print("No")
    continue

  if len(la) == N and len(lb) == N and la[0] != la[-1]:
    if a == b:
      print("Yes")
    else:
      print("No")
    continue

  for l in range(N):
    la = [a[l]]
    for i in range(1, N):
      if la[-1] != a[(i + l) % N]: la.append(a[(i + l) % N])

    i = 0
    #print(la, lb)
    for j in range(len(lb)):
      if i > len(la): break
      f = 0
      while i <= len(la):
        if la[i % len(la)] == lb[j]:
          i += 1
          f = 1
          break
        i += 1
      #print(i, j, f)
      if f == 0: break
    else:
      print("Yes")
      break
  else:
    print("No")