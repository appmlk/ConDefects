T = int(input())
for _ in range(T):
  N, K = map(int, input().split())
  if N % 2 == 0:
    N //= 2
    i = K
    if i % 2 == 1:
      i += 1
    i //= 2
    if (N - 1) & (i - 1) == i - 1:
      if K % 2 == 0:
        print(K - 1)
      else:
        print(K + 1)
    else:
      print(K)
  else:
    if K == 1:
      print(2)
    else:
      N -= 1
      N //= 2
      i = K
      if i % 2 == 1:
        i += 1
      i //= 2
      if (N - 1) & (i - 1) == i - 1:
        if K % 2 == 0:
          if (N - 1) & ((K + 2) // 2 - 1) == (K + 2) // 2 - 1:
            print(K + 2)
          else:
            print(K + 1)
        else:
          if (N - 1) & ((K - 1) // 2) == (K - 1) // 2:
            print(K - 2)
          else:
            print(K + 1)
      else:
        if (N - 1) & (K // 2 - 1) == K // 2 - 1:
          if K % 2 == 0:
            if (N - 1) & ((K + 2) // 2 - 1) == (K + 2) // 2 - 1:
              print(K + 2)
            else:
              print(K + 1)
          else:
            if (N - 1) & ((K - 1) // 2 - 1) == (K - 1) // 2 - 1:
              print(K - 2)
            else:
              print(K + 1)
        else:
          print(K)