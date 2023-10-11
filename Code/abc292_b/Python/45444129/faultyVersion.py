N, Q = map(int, input().split())

T = [2] * N
for i in range(Q):
  c, x = map(int, input().split())

  if c == 1:
    T[x-1] -= 1
  elif c == 2:
    T[x-1] -= 2
  else:
    if (T[x-1] == 0):
      print('Yes')
    else:
      print('No')