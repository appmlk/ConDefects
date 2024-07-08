p=998244353
S=input()
N=len(S)
UC=[0 for _ in range(26)]
#S[i]までに現れる大文字の種類数
UK=[0 for _ in range(N)]
#S[i]が2回目以上の大文字かどうか
DB=[0 for _ in range(N)]
for n in range(N):
  i=ord(S[n])
  if 65<=i<=90:
    if UC[i-65]>=1:
      DB[n]=1
      UK[n]=UK[n-1]
    else:
      if n==0:
        UK[n]=1
      else:
        UK[n]=UK[n-1]+1
    UC[i-65]+=1
  else:
    if n==0:
      UK[n]=0
    else:
      UK[n]=UK[n-1]
inv=[0,1]
for i in range(2,30):
  inv.append(p//i*(-inv[p%i])%p)
#dp(n)[st]:S[n]まで見て、異なる大文字がst種類(0<=st<=26)、
#大文字重複ありかつその後小文字なし(st=27)、
#大文字重複ありかつその後小文字ありかつその後大文字なし(st=28)
dp=[0 for _ in range(29)]
dp[0]=1
for n in range(N):
  dpn=[0 for _ in range(29)]
  i=ord(S[n])
  if i>=97:
    for st in range(27):
      dpn[st]+=dp[st];dpn[st]%=p
    dpn[28]+=dp[27]+dp[28];dpn[28]%=p
  elif i>=65:
    for st in range(27):
      if DB[n]:
        dpn[27]+=dp[st];dpn[27]%=p
      else:
        if n==0:
          y=0
        else:
          y=UK[n-1]
        dpn[st+1]+=dp[st]*(26-st)*inv[26-y];dpn[st+1]%=p
        dpn[27]+=dp[st]*(st-y)*inv[26-y];dpn[27]%=p
    dpn[27]+=dp[27];dpn[27]%=p
  else:
    for st in range(27):
      dpn[st]+=dp[st]*26;dpn[st]%=p
      dpn[st+1]+=dp[st]*(26-st);dpn[st+1]%=p
      dpn[27]+=dp[st]*st;dpn[st]%=p
    dpn[27]+=dp[27]*26;dpn[27]%=p
    dpn[28]+=dp[27]*26;dpn[28]%=p
    dpn[28]+=dp[28]*26;dpn[28]%=p
  dp=dpn
print(sum(dp)%p)