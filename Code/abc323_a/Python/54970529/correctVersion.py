S = input()
ans = True
for i in range(1, 17, 2):
  if S[i]!='0':
    ans = False

if ans:
  print("Yes")
else:
  print("No")