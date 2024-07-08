t = int(input())
for _ in range(t):
  n = int(input())
  ans = 0
  if n>=1: ans += 1

  cnt = 2
  while n>=10**cnt-2*10**(cnt//2):
    ans += 1
    ans += min(n, 10**cnt+10**(cnt//2)-1) - (10**cnt-10**(cnt//2)-1)
    cnt += 2
  
  print(ans)
