n=int(input())
S=str(input())
C=list(map(int,input().split()))

mask=0
for i in range(n):
  if int(S[i])==i%2:
    mask|=1<<i

cost1=sum(C[i] for i in range(n) if mask>>i&1)
cost2=sum(C)-cost1


def changecost(mk,cost1,cost2):
  costlist=[]
  for i in range(n-1):
    if i==0:
      cost1i=cost1
      cost2i=cost2
    if (mk>>i)&1:
      cost1i-=C[i]
      cost2i+=C[i]
    else:
      cost1i+=C[i]
      cost2i-=C[i]

    costlist.append(cost1i)
    costlist.append(cost2i)
  return (min(costlist))
      
print(changecost(mask,cost1,cost2))