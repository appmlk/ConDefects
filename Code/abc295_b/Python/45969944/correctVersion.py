import copy
r, c = map(int, input().split())
b = []
for _ in range(r):
  b.append(list(input()))


z = [0] * 10
zz = [0] * (c + 20)
ans = copy.deepcopy(b)

  
for i in range(r):
  for k in range(c):
    if (b[i][k] == '1' or
    b[i][k] == '2' or
    b[i][k] == '3' or
    b[i][k] == '4' or
    b[i][k] == '5' or
    b[i][k] == '6' or
    b[i][k] == '7' or
    b[i][k] == '8' or
    b[i][k] == '9'):
      p = int(b[i][k])
      for m in range(r):
        for n in range(c):
          if abs(i - m) + abs(k - n) <= p:
            ans[m][n] = '.'

for i in range(r):
  for k in range(c):
    print(ans[i][k], end='')
  print()
    