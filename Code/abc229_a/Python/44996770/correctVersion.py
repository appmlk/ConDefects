S1 = input()
S2 = input()
k = []
s = []
k.append(S1[0])
k.append(S1[1])
s.append(k)
k = []
k.append(S2[0])
k.append(S2[1])
s.append(k)
o = 0
if s[0][0] == "#":
  o +=1
if s[0][1] == "#":
  o += 1
if s[1][0] == "#":
  o += 1
if s[1][1] == "#":
  o += 1
  
# answer
if o == 1 or o >= 3:
  print("Yes")
else:
  if (s[0][0] == "#" and s[1][1] == "#") or (s[0][1] == "#" and s[1][0] == "#"):
    print("No")
  else:
    print("Yes")