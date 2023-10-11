T=int(input())
for _ in range(T):
  N=input()
  ans=0
  for i in range(1,len(N)):
    if len(N)%i==0:
      if int(N[:i]*(len(N)//i))<=int(N):
        ans=max(ans,int(N[:i]*(len(N)//i)))
      else:
        ans=max(ans,int(str(int(N[:i])-1)*(len(N)//i)))
  print(ans)