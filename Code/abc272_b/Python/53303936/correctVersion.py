N, M = map(int, input().split())
X=[]
for _ in range(M):
  _, *x=map(int, input().split())
  X.append(set(x))
flag=False
from itertools import combinations
for cmb in combinations(range(1,N+1), 2):
  for i in X:
    if cmb[0] in i and cmb[1] in i:
      flag=True
      break
  else:
      print("No")
      exit()
print("Yes" if flag else "No") 