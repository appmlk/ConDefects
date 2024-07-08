N,M = map(int, input().split())
ans=0
for i in range(60):
  if (M>>i&1)==1:
    p=2**(i+1)
    r=N%p
    ans+=(N-r)/2
    if (r>=2**i):
      ans+=(r-(2**i)+1)

print(int(ans%998244353))