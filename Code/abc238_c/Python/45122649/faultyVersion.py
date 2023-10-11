N = int(input())
ans = 0
mod = 998244353
for i in range(1,19):
  if i==1:
    if N<9:
      print(N)
      exit()
    ans += (9*10)//2
  else:
    MAX = int('9'*i)
    if N <= MAX:
      a = (N-(10**(i-1))+1)
      ans += (a%mod)*((a+1)%mod)//2
      ans %= mod
      break
    else:
      a = (MAX-(10**(i-1))+1)
      ans += (a%mod)*((a+1)%mod)//2
      ans %= mod
print(ans)
  
  