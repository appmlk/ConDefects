from collections import Counter
n=int(input())
A=sorted(list(map(int,input().split())))
M=10**6

acount=[0]*(M+1)
for i in range(n):
  acount[A[i]]+=1
for i in range(M):
  acount[i+1]+=acount[i]

ans=0
for num,ct in Counter(A).items():
  ans+=ct*(ct-1)//2
  for j in range(1,M//num+1):
    c=acount[min(M,(j+1)*num-1)]-acount[j*num-1]
    if j==1:
      c-=ct
    ans+=c*ct*j

print(ans)