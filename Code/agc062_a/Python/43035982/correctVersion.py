t=int(input())
for i in range(t):
  n=int(input())
  s=list(map(str,input()))
  cnt=0
  for i in range(n-1):
    if s[i]=="A" and s[i+1]=="B":
      cnt+=1
  if cnt==0 and s[0]=="B" and s[-1]=="B":
    print("B")
  elif cnt==1 and s[0]=="A" and s[-1]=="B":
    print("B")
  else:
    print("A")