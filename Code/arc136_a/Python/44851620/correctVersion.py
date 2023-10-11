n=int(input())
s=input()
L=[]
for i in range(n):
  if s[i]=="A":
    L.append("B")
    L.append("B")
  else:
    L.append(s[i])
now=0
while now<len(L):
  if now==len(L)-1:
    print(L[now])
    now+=1
  elif L[now]=="B" and L[now+1]=="B":
    print("A",end="")
    now+=2
  else:
    print(L[now],end="")
    now+=1