t = int(input())

for iiiii in range(t):
  n = int(input())
  s =(input())
  
  flag = True
  b = False
  for i in range(n):
    if s[i] == "B":
      b=True
    if s[i] == "A" and b:
      flag = False
  
  if(flag):
    print("B")
  else :
    print("A")
      