t = int(input())
for _ in range(t):
  n,m,k=map(int,input().split(' '))
  s=input()
  i=0
  l=len(s)
  ans=0
  while(i<l-1):
    if(i+3<=l and (s[i:i+3]=='RDR' or s[i:i+3]=='DRD')):
      ans+=1
      i+=2
    elif(s[i:i+2]=='RD' or s[i:i+2]=='DR'):
      ans+=1
      i+=1
    else:
      i+=1
  print(ans)
    