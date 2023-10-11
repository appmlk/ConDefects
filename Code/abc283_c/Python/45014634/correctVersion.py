s=list(map(int,input()))
n=len(s)
ans=0
ze=0
for i in range(n):
  if s[i]!=0:
    ans+=1
    ans+=(ze+1)//2
    ze=0
  else:
    ze+=1
print(ans+(ze+1)//2)