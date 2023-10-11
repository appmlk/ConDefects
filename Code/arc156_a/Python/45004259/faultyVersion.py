t=int(input())
for i in range(t):
  n=int(input())
  s=list(map(str,input()))
  cnt=0
  adj=0
  for i in range(n):
    if(s[i]=='1'):
      cnt+=1
      if(s[i-1]=='1'):
        adj=1
      else:
        adj=0
  if(cnt%2==1):
    print(-1)
  else:
    if(cnt==2 and adj==1):
      if(n==2 or n==3):
        print(-1)
      else:
        print(2)
    else:
      print(cnt//2)