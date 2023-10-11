def f(L,R,V):
  #L,R,V=map(int,input().split())
  ans=0
  V2=V
  L2=L
  R2=R
  if V%2==0:
    V+=1
  L+=1-L%2
  R-=R%2
  if L>R:
    return 0
  mod=998244353
  #dp[i][Rflag][Lflag][flag] i桁目までRのflag,Lのflag xorがVのi桁目までと一致してる個数,等しいか？
  S=bin(L)[2:]
  T=bin(R)[2:]
  K=bin(V)[2:]
  S="0"*(len(T)-len(S))+S
  if len(K)>len(T):
    print(0)
    exit()
  K="0"*+(len(T)-len(K))+K
  K="0"+K
  S="0"+S
  T="0"+T
  dp=[[[[0]*2 for i in range(2)] for j in range(2)] for s in range(len(T))]
  dp[0][1][1][1]=1
  for i in range(1,len(T)):
    if int(T[i])^int(S[i])==int(K[i]):
      if int(K[i])==0:
        dp[i][1][1][1]+=dp[i-1][1][1][1]
      dp[i][1][1][0]+=dp[i-1][1][1][0]
      if int(T[i])>int(S[i]):
        dp[i][1][1][0]+=dp[i-1][1][1][1]

    if int(K[i])==0:
      dp[i][1][0][1]+=dp[i-1][1][0][1]
      if int(T[i])>int(S[i]):
        dp[i][1][0][1]+=dp[i-1][1][1][1]
    if int(T[i])^int(K[i])<int(T[i]):
      dp[i][1][0][0]+=dp[i-1][1][0][1]
    if int(T[i])^int(K[i])>int(S[i]):
      dp[i][1][0][0]+=dp[i-1][1][1][0]
      #dp[i][1][0][0]+=dp[i-1][1][1][1]
    dp[i][1][0][0]+=dp[i-1][1][0][0]

    if int(K[i])==0:
      dp[i][0][1][1]+=dp[i-1][0][1][1]
    if int(K[i])==0 and int(T[i])>int(S[i]):
      dp[i][0][1][1]+=dp[i-1][1][1][1]
    dp[i][0][1][0]+=dp[i-1][0][1][0]
    if int(T[i])==1 and int(S[i])==int(K[i]):
      dp[i][0][1][0]+=dp[i-1][1][1][0]
    if int(S[i])==0 and int(K[i])==1:
      dp[i][0][1][0]+=dp[i-1][0][1][1]
    #dp[i][0][1][0]+=dp[i-1][1][1][1]

    if int(K[i])==0:
      dp[i][0][0][1]+=dp[i-1][0][0][1]*2
      if int(S[i])==0:
        dp[i][0][0][1]+=dp[i-1][0][1][1]
      if int(T[i])==1:
        dp[i][0][0][1]+=dp[i-1][1][0][1]
      #if 1-int(S[i])==1-int(T[i]):
        #dp[i][0][0][1]+=dp[i-1][1][1][1]
    if int(K[i])==1:
      dp[i][0][0][0]+=dp[i-1][0][0][1]

      #dp[i][0][0][0]+=dp[i-1][1][0][1]
      #dp[i][0][0][0]+=dp[i-1][0][1][1]
      #dp[i][0][0][0]+=dp[i-1][1][1][1]
    dp[i][0][0][0]+=dp[i-1][0][0][0]*2
    if int(T[i])==1:
      dp[i][0][0][0]+=dp[i-1][1][0][0]
    if int(S[i])==0:
      dp[i][0][0][0]+=dp[i-1][0][1][0]
    if int(T[i])==1 and int(S[i])==0 and int(K[i])==1:
      dp[i][0][0][0]+=dp[i-1][1][1][0]
    for n in range(8):
      s=n//4
      k=(n%4)//2
      t=n%2
      dp[i][s][k][t]%=mod
  #ans+=dp[len(T)-2][1][1][1]
  ans+=dp[len(T)-2][1][1][0]
  #ans+=dp[len(T)-2][1][0][1]
  ans+=dp[len(T)-2][1][0][0]
  #ans+=dp[len(T)-2][0][1][1]
  ans+=dp[len(T)-2][0][1][0]
  #ans+=dp[len(T)-2][0][0][1]
  ans+=dp[len(T)-2][0][0][0]
  return ans%mod

mod=998244353
ans=0
L,R,V=map(int,input().split())

if V==1:
  if L==1:
    ans+=(R-L)//4+1
  L+=L%2
  R-=1-R%2
  R+=2
  V=0
if V==0:
  if L==1:
    ans+=1+(R-3)//4
  L+=L%2
  R-=1-R%2
  if L>R:
    print(0)
    exit()
  else:
    s=(R-L+1)//2
    if s%2==1:
      s=s//2
      print((ans+s*(s+1))%mod)
    else:
      s=s//2
      print((ans+s*s)%mod)
  exit()
ans=0
"""
ans=0
for i in range(L,R+1):
  this=0
  for j in range(i,R+1):
    this^=j
    if this==V:
      ans+=1
print(ans)
"""
if L<=V<=R:
  a=0
  if V%2==0:
    a+=(V-L)//4
    a+=(R+1-V)//4
    a+=1
  else:
    a+=(V+1-L)//4
    a+=(R-V)//4
    a+=1
  if V%4==0:
    print((f(L,R,V)+a)%mod)
  if V%4==1:
    print((a)%mod)
  if V%4==2:
    print((a)%mod)
  if V%4==3:
    print((f(L,R,V)+a)%mod)
else:
  if V%4==1 or V%4==2:
    print(0)
  else:
    print(f(L,R,V))