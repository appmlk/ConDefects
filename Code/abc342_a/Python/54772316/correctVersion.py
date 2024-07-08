S = input()
if S.count(S[0])==1:
  print("1")
else:
  for i in range(1, len(S)):
    if S[i] != S[0]:
      print(i+1)
  