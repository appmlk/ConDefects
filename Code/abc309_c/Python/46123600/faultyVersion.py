def check(x):
  sub = 0
  for i in range(N):
    a,b = ab[i]
    if x<=a:
      sub+=b
  #print(sub)
  if sub<=K:
    return 1
  else:
    return 0

N,K = map(int,input().split())
ab = [list(map(int,input().split())) for _ in range(N)]
ng,ok = 0,10**9
while abs(ok-ng)>1:
  ic = (ok+ng)//2
  #print(ok,ic,ng)
  if check(ic):
    ok = ic
  else:
    ng = ic
  
print(ok)