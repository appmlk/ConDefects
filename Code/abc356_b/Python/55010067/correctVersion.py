N,M = map(int,input().split())
A = list(map(int,input().split()))
X =[]
for i in range(N):
  X.append(list(map(int,input().split())))
Y = []
for i in range(M):
  Y.append(0)

for i in range(M):
  for j in range(N):
    Y[i] += X[j][i]
    
Flag =True
for i in range(M):
  if Flag == False:
    break
  if Y[i] < A[i]:
    print('No')
    Flag = False
    break

if Flag == True:
  print('Yes')