S = input()
count=0
for i in range(1, 16, 2): 
    if S[i] == '0':
      continue
    else:
        count+=1
if count==0:
  print("Yes")
else:
  print("No")
  