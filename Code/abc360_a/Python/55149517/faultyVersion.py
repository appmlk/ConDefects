s = input()
d = {}
n = 1
for i in s:
  d[i] = n
  n += 1

if d['R'] > d['M']:
  print('NO')
else:
  print('Yes')