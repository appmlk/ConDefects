N=int(input())
index=1
s = set()
l=[]
while (True):
  flg=0
  three=index*index*index
  if(three>N):
    break
  l.append(three)
  index+=1

#print(l)
flg=0
for i in reversed(l):
  flg=0
  strthree=str(i)
  if(len(strthree)==1):
    print(i)
    break
  for j in range(len(strthree)//2):
    #print(j)
    #print(strthree[j],strthree[len(strthree)-1-j])
    if(strthree[j]!=strthree[len(strthree)-1-j]):
      flg=1
      break
  if(flg):
    continue
  print(i)
  break
