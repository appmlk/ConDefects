mod=998244353
memo={}

def calc(l,r,tl,tr,flag,v):
  if l>r:
    return 0
  if l==r:
    if (l*tl)^(r*tr)==v and flag:
      return 1
    return 0
  
  if (l,r,tl,tr,flag,v) in memo:
    return memo[(l,r,tl,tr,flag,v)]

  res=0
  for l0 in range(2):
    for r0 in range(2):
      sl=l0&tl
      sr=r0&tr
      if sl^sr==v&1:
        nflag=0
        if l0!=r0:
          nflag=(l0<r0)
        else:
          nflag=flag
        res+=calc((l+1-l0)//2,(r-r0)//2,tl,tr,nflag,v>>1)

  res%=mod
  memo[(l,r,tl,tr,flag,v)]=res
  return res

def solve(L,R,V):
  ans=0
  s=[0,1,3,0]
  for i in range(4):
    for j in range(4):
      if s[i]^s[j]==V&3:
        ans+=calc((L+3-i)//4,(R-j)//4,(i&1)^1,(j&1)^1,i<j,V>>2)
  return ans

def naive(L,R,V):
  ans=0
  for l in range(L+1,R+1):
    for r in range(l,R+1):
      res=0
      for i in range(l,r+1):
        res^=i
      if res==V:
        ans+=1
  return ans

debug=0
if not debug:
  L,R,V=map(int,input().split())
  print(solve(L-1,R,V))
  exit()


import random
while True:
  L=random.randint(0,100)
  R=random.randint(L+1,101)
  V=random.randint(0,100)
  print(L,R,V)
  if solve(L,R,V)!=naive(L,R,V):
    print(L,R,V)
    print(solve(L,R,V),naive(L,R,V))