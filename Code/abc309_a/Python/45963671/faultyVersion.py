a, b = map(int, input().split())

if b-a != 1:
  print("No")
  

cnt = 0

def judge(s):
  if s in (1,3,4,6,7,9):
    return 1
  else:
    return 2
    
if judge(a) + judge(b) == 2:
  print("No")
else:
  print("Yes")

