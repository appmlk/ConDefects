S = input()
if S[0] == S[1] and S[1] == S[2]:
  print(1)
elif S[0] == S[1] or S[0] == S[2] or S[1] == S[2]:
  print(3)
else:
  print(6)