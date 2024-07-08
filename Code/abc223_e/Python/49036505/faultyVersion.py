#長方形の設置の仕方は川か仁の2つ

import itertools

X,Y,A,B,C = map(int,input().split())

for i in range(2):
  jls = list(itertools.permutations([A,B,C]))
  #仁の字型チェック
  for a,b,c in jls:
    x1 = (a-1)//Y +1
    if x1 < X:
      y1 = (b-1)//(X-x1) +1
      if y1 >= Y:
        if (X-x1)*(Y-y1) >= c:
          print("Yes")
          exit()
  #川の字型チェック
  for a,b,c in jls:
    x1 = (a-1)//Y +1
    x2 = (b-1)//Y +1
    x3 = (c-1)//Y +1
    if x1+x2+x3 <= X:
      print("Yes")
      exit()
  X,Y = Y,X

print("No")