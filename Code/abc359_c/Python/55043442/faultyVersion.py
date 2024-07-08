
Sx, Sy = map(int, input().split())
Tx, Ty = map(int, input().split())

if (Sx + Sy) % 2 == 1: 
  Sx -= 1
if (Tx + Ty) % 2 == 1: 
  Tx -= 1  

x = abs(Sx - Tx) 
y = abs(Sy - Ty)

if y >= x:
  print(y)
else:
  print(y + x // 2)



