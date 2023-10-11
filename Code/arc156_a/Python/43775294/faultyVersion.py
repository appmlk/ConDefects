t=int(input())
for _ in range(t):
  n=int(input())
  s=input()
  L=[]
  p=[]
  w2=0
  no_w2=0
  num_1=0
  for i in range(n):
    if s[i]=="1":
      p.append(1)
      num_1+=1
    else:
      if len(p)==2:
        L.append(p)
        p=[]
        w2+=1
      if len(p)!=0:
        L.append(p)
        p=[]
        no_w2+=1
    if i==n-1:
      if len(p)==2:
        L.append(p)
        p=[]
        w2+=1
      if len(p)!=0:
        L.append(p)
        p=[]
        no_w2+=1
      

  if num_1%2==1:
    print(-1)
  elif s=="011":
    print(-1)
  elif s=="110":
    print(-1)
  elif s=="0110":
    print(3)
  elif len(L)==0:
    print(0)
  elif w2==1:
    print(num_1//2+1)
  else:
    print(num_1//2)