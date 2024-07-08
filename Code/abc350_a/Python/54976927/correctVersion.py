S = input()
K = 0
A = ["0", "1", "2", "3", "4"]

if S == "ABC316" or S == "ABC000":
  K = 0
else:
  if S[3] == "3":
    for a in A:
      if S[4] == a:
        K = 1
        break
  else:
    for a in A[:3]:
      if S[3] == a:
        K = 1
        break
print("Yes" if K == 1 else "No")
