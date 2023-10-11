N,X=map(int,input().split())
  
DP=[0]*(X+1) #DP[n]=現在n円払えるか
DP[0]=1
for i in range(N):
  a,b=map(int,input().split())
  for n in reversed(range(X+1)):
    for c in range(1,b+1):
      if 0<=n-c*a and DP[n-c*a]:
        DP[n]=1
print("Yes" if DP[X] else "No")
