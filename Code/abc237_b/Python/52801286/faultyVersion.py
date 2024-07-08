h,w = list(map(int,input().split(' ')))
b = [[0 for j in range(h)] for i in range(w)]
for i in range(h):
  tmp = list(map(int,input().split(' ')))
  for j in range(w):
    b[j][h-1-i] = tmp[j]
for b_ in b:
  print(*b_)