N=int(input())
A=list(map(int,input().split()))
for i in range(1,N,2):
  A[i]^=1
if A==[0]*N: print("Yes") ; exit()
for i in range(N):
  if A[i]==1: break
S=[j%2 for j in range(N)]
idx=0
for k in range(i,N):
  if idx>=N: break
  if A[k]!=S[idx]: idx+=2
  else: idx+=1
if idx>=N: print("No")
else: print("Yes")