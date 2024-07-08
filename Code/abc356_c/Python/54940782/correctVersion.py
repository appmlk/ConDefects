n,m,k=map(int,input().split())
C=[0 for _ in range(m)]
A=[[0 for _ in range(n)] for _ in range(m)]
R=[False for _ in range(m)]

for i in range(m):
  X=list(map(str, input().split()))
  C[i]=int(X[0])
  r=X[-1]
  if r=="o":
    R[i]=True
  xx=[int(X[j]) for j in range(1,C[i]+1)]
  for j in range(n):
    if j+1 in xx:
      A[i][j]=1
      
s=0
for i in range(2**n):
  key=[0 for _ in range(n)]
  bi=bin(i)[2:]
  for j in range(len(bi)):
    key[j]=int(bi[-j-1])

  judge=True
  for ii in range(m):
    keysum=sum([A[ii][j]*key[j] for j in range(n)])
    if (keysum>=k)!=R[ii]:
      judge=False
      break
  if judge:
    s+=1
    
print(s)