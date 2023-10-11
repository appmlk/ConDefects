N,M=map(int,input().split())
A=input()
B=input()
X=[[0,0] for _ in range(M)]
a=0
b=0
if M==0:
  print(N-1)
  exit()
elif N==0:
  print(M-1)
  exit()
for i in range(N+M):
  if A[i]=="1":
    X[a][0]=i
    a+=1
  if B[i]=="1":
    X[b][1]=i
    b+=1
X=[sorted(X[i]) for i in range(M)]
#0-start
p=X[0][1]
c=1
for i in range(1,M):
  if X[i][0]>p+1:
    c+=2
    p=X[i][1]
  else:
    p+=1
if p!=N+M-1:
  c+=1
#1-start
p=-1
d=1
for i in range(M):
  if X[i][0]>p+1:
    d+=2
    p=X[i][1]
  else:
    p+=1
if p!=N+M-1:
  d+=1    
print(N+M-min(c,d)-1)    