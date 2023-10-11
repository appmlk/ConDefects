S = input()

level = 0
LV = [set() for _ in range(len(S))]
ST = set()

for ss in S:
  if ss == ")":
    ST = ST - LV[level]
    LV[level] =set()
    level -= 1
  elif ss == "(":
    level += 1
  else:
    if ss in ST:
      print("No")
      exit()
    else:
      ST.add(ss)
      LV[level].add(ss)
      
print("Yes")
