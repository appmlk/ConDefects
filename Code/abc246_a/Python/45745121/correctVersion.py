array = list(map(int,input().split()))
xy1 = array
array = list(map(int,input().split()))
xy2 = array
array = list(map(int,input().split()))
xy3 = array
x = []
for i in range(2):
  if xy1[i] == xy2[i]:
    x.append(xy3[i])
  elif xy1[i] == xy3[i]:
    x.append(xy2[i])
  else:
    x.append(xy1[i])
print(f'{x[0]} {x[1]}')