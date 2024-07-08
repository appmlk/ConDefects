(n,x,y),a,b=[[*map(int,s.split())]for s in open(0)]
INF=1<<60
dp=[INF]*(1<<n)
dp[0]=0
for s in range(1<<n):
  c=s.bit_count()
  for i in range(n):
    if s>>i&1:
      continue
    d=str(bin(s)[::-1][i:]).count('1')
    dp[s|(1<<i)]=min(dp[s|(1<<i)],dp[s]+(i+d-c)*y+abs(a[i]-b[c])*x)
print(dp[-1])