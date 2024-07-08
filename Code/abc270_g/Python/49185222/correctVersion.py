Q=int(input())
for _ in range(Q):
  P,A,B,S,G=map(int,input().split())
  mod=P
  if S==G:
    print(0)
    continue
  if A==0:
    if B==G:
      print(1)
      continue
    else:
      print(-1)
      continue
  def g(x):
    ans=1
    w=x
    n=mod-2
    while n>0:
      if n&1:
        ans*=w
        ans%=mod
      w**=2
      w%=mod
      n//=2
    return ans
  if A==1:
    if B==0:
      print(-1)
      continue
    ans=(G-S)*g(B)
    ans%=mod
    print(ans)
    continue
  c=B*g(A-1)
  c%=mod
  if ((S+c)%mod)==0:
    print(-1)
    continue
  ans=(G+c)*g(S+c)
  ans%=mod
  T={}
  d=1
  from math import sqrt
  k=int(sqrt(mod))
  for i in range(k):
    if not d in T:
      T[d]=i
    d*=A
    d%=mod
  result=10**20
  for y in range(k+2):
    e=ans*pow(A,-k*y,mod)
    e%=mod
    if e in T:
      pos=T[e]
      result=y*k+pos
      break
  if result<10**20:
    print(result)
  else:
    print(-1)