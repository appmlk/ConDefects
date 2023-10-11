tex=['ACE', 'BDF', 'CEG', 'DFA', 'EGB', 'FAC', 'GBD']
s=input()

ans=0
for i in range(len(tex)):
  if s == tex[i]:
    ans+=1

if ans==0:
  print("No")
else:
  print("Yes")