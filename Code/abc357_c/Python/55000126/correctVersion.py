import numpy as np

def center(size, l, x, y):
  for i in range(size):
    for j in range(size):
      l[i+size+x][j+size+y] = '.'


n = int(input())

size = 3**n
size_low = 3**(n-1)

l0 = np.array([
  ['#', '#', '#'],
  ['#', '.', '#'],
  ['#', '#', '#']
])

lk = [['#' for i in range(size)] for j in range(size)]

if n == 0:
  print('#')
elif n == 1:
  for i in l0:
    print(*i, sep='')
else:
  for i in range(size):
    for j in range(size):
      if i % 3 == 1 and j % 3 == 1:
        lk[i][j] = '.'


  center(size_low, lk, 0, 0)


  for i in range(3):
    for j in range(3):
      center(3**(n-2), lk, size_low*i, size_low*j)

  if n > 3:
    for i in range(3**(n-2)):
      for j in range(3**(n-2)):
        center(3, lk, 9*i, 9*j)

  if n > 4:
    for i in range(3**(n-3)):
      for j in range(3**(n-3)):
        center(9, lk, 27*i, 27*j)

  if n > 5:
    for i in range(3**(n-4)):
      for j in range(3**(n-4)):
        center(27, lk, 81*i, 81*j)


  for i in lk:
    print(*i,sep='')