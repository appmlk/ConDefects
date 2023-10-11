S = input()
K = int(input())
N = len(S)
X = [0]*N

if N==1:
  if (S[0]=='.' and K==1) or (S[0]=='X' and K==0):
    print(1)
    exit()

if K==0:
  ans = 0
  for i in range(N):
    if S[i]=='X':
      if i!=N-1:
        for j in range(i+1,N):
          if S[j]!='X':
            break
        ans = max(ans,j-i)
      else:
        ans = max(ans,1)
  print(ans)
  exit()

  
for i in range(N):
  if S[i]=='.':
    X[i]+=1
for i in range(1,N):
  X[i] = X[i] + X[i-1]
X = [0] + X
#print(X)
ans = 0
for i in range(1,N):
  ok,ng =i,N+1
  while abs(ok-ng)>1:
    ic = (ok+ng)//2
    #print(ok,ic,ng)
    if X[ic]-X[i-1]<=K:
      ok = ic
    else:
      ng = ic
  #print(ok,ok-i+1)
  ans = max(ans,ok-i+1)
print(ans)