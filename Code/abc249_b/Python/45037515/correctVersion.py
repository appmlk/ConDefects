S=input()
if S.upper()!=S and S.lower()!=S and len(set(list(S)))==len(S):
  print("Yes")
else:
  print("No")