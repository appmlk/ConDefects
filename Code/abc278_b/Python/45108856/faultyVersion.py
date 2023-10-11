H, M = map(int, input().split())

while True:
  if H < 20:
    if int(str(H)[-1]) < 6:
      print(H,M)
      exit()
    else:
      M += 1
      if M == 60:
        H += 1
        H = H % 24
        M = 0
  else:
    if int(str(M)[0]) < 4:
      print(H,M)
      exit()
    else:
      M += 1
      if M == 60:
        H += 1
        H = H % 24
        M = 0