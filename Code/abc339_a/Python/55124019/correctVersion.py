S = input()
for i in range(1,len(S)+1):
  if S[-i] == ".":
    print(S[-i+1:])
    break