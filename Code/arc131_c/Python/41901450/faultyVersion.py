N=int(input())
x=0
A=list(map(int,input().split()))
T={}
for i in range(N):
  x^=A[i]
  T[A[i]]=1
if x in T:
  print('Win')
  exit()
if N%2==1:
  print('Win')
else:
  print('No')