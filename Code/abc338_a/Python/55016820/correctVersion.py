S = input()

if S[0].isupper():
  if (len(S)>= 2 and S[1:].islower()) or len(S) == 1:
    print("Yes")
  else:
    print("No")
else:
  print("No")