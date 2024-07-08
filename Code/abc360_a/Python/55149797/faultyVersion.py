S = input()
for i in range(len(S)):
  if S[i] == 'R':
    R_index = i
  elif S[i] == 'S':
    S_index = i
if R_index < S_index:
  print("Yes")
else:
  print("No")