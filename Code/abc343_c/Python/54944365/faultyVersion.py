N = int(input())
ans = 0

for x in range(N):
  if x**3 > N:
    print(ans)
    exit()
  else:
    s = str(x**3)
    str(s)
    S = list(s)
    P = S.copy()
    P.reverse()
   
    if S == P:
      if ans < x**3:
        ans = x**3
        
        
    