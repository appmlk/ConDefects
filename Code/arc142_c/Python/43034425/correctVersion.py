N = int(input())
G = []
G1 = [0 for i in range(N+1)]
for u in range(3,N+1):
  print('?',1,u)
  d = int(input())
  G1[u] = d

G2 = [0 for i in range(N+1)]
for u in range(3,N+1):
  print('?',2,u)
  d = int(input())
  G2[u] = d
  
  

d = 10**9
for u in range(3,N+1):
  dd = G1[u] + G2[u]
  d = min(d,dd)
if d == 3:
  X = []
  for u in range(3,N+1):
    if G1[u] + G2[u] == 3:
      X.append(u)
  if len(X) != 2:
    d = 1
  else:
    u,v = X[0],X[1]
    print('?',u,v)
    dd = int(input())
    if dd != 1:
      d = 1
print('!',d)
exit()