l,n1,n2=map(int,input().split())
vl1=[list(map(int,input().split())) for _ in range(n1)]
vl2=[list(map(int,input().split())) for _ in range(n2)]

acc1,acc2=[0]*(n1+1),[0]*(n2+1)
for i in range(n1):
  acc1[i+1]=acc1[i]+vl1[i][1]
for i in range(n2):
  acc2[i+1]=acc2[i]+vl2[i][1]

i,j=0,0
ans=0
while i<n1 and j<n2:
  a,b=vl1[i]
  c,d=vl2[j]

  if a==c:
    max_l=max(acc1[i],acc2[j])
    min_r=min(acc1[i+1],acc2[j+1])
    ans+=min_r-max_l

  if acc1[i+1]<acc2[j+1]:
    i+=1
  else:
    j+=1
print(ans)