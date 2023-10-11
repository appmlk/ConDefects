def dt(n):
  if n == 0:
      return 0
 
  ternary = 0
  while n > 0:
    remainder = n % 3
    ternary+=remainder
    n //= 3
 
  return ternary
 
tc = int(input())
for _ in range(tc):
  n,k = map(int, input().split())
  print("Yes" if dt(n) <= k <= n and k%2==n%2 else "No")
