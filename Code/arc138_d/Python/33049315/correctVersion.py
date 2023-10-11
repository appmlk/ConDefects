def gray_code(n):
  g=[0]*(2**n)
  for k in range(2 ** n):
    g[k]=format(bin(k^(k>>1))[2:],"0>"+str(n))
  return(g)

import array
n,k=map(int,input().split())
if n==k==1: 
  print("Yes");print(0,1);exit()
if k%2==0 or n==k:
  print("No");exit()
print("Yes")
xornum=[]
s={0}
for i in range(2**n):
  if bin(i).count("1")==k and i not in s:
    news=set()
    for j in s: news.add(j^i)
    s|=news
    xornum.append(i)
  #print(bin(xornum[l])[2:])
g=gray_code(n)
#print(g)
ans=array.array("i",[0]*(2**n))
tmp=0
for j in range(2**n):
  tmp=0
  for i in range(n):
    if g[j][i]=="1":tmp=tmp^xornum[i]
  ans[j]=tmp
print(*ans)