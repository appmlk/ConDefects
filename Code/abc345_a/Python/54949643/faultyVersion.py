n = input()
cnta,cntb =0,0
stack = []
bool = True
for i in range(len(n)):
  if n[i]=='<':
    cnta+=1
  elif n[i]=='>':
    cntb+=1
    if cntb>cnta:
        bool = False
        break;
if bool and cnta==cntb:
  print("Yes")
else:
  print("No")