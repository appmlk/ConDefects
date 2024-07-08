S = input()
for i in range(len(S)):
  if S[-i] == ".":
    print(S[-i+1:])
    break