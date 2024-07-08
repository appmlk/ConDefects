N = [0]*9
for i in range(9):
  l = list(map(int,input().split()))
  if len(set(l))<9:
    print("No")
    exit()
  N[i] = l
RN = list(zip(*reversed(N)))
for l in RN:
  if len(set(l))<9:
    print("No")
    exit()
    
a = set()
b = set()
c = set()
for i in range(3):
  for j in range(3):
    a|=set(N[3*i+j][:3])
    b|=set(N[3*i+j][3:6])
    c|=set(N[3*i+j][-3:])
  if len(a)<9 or len(b)<9 or len(c)<9:
    print("No")
    exit()
  a = set()
  b = set()
  c = set()
print("Yes")