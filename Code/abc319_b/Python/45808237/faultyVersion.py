N = int(input())

D=[]
for i in range(1,9):
  if N % i ==0:
    D.append(i)

S =[1]+["-"]*(N)

for i in range(1,N+1):
  for d in D:
    if i % (N/d) == 0:
      S[i] = d
      break
 
print(*S,sep="")