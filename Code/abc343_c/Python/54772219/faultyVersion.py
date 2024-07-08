N = int(input())
import math
n = [i**3 for i in range(math.floor(N**(1/3))+1)]
for i in range(len(n)-1, -1, -1):
  x = True
  for j in range(len(str(n[i]))//2 + 1):
    if str(n[i])[j] != str(n[i])[-j-1]:
      x = False
      
  if x:
    print(n[i])
    exit()