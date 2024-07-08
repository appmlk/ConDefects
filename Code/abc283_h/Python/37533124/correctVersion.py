def floor_sum(n, m, a, b):
    res = 0
    if n == 0:
        return 0
    
    if a >= m:
        res += n*(n-1)//2*(a//m)
        a %= m
    
    if b >= m:
        res += n*(b//m)
        b %= m

    if a == 0:
        return res
    
    q = (a*n+b)//m
    r = (a*n+b)%m
    res += floor_sum(q, a, m, r)
    return res


T=int(input())
for i in range(T):
  N,M,R=map(int,input().split())
  ans=0
  X=(N-R)//M+1
  d=1
  while d<=N:
    ans+=floor_sum(X,d,M,R)-floor_sum(X,d*2,M,R)*2
    d*=2
  print(ans)
  
    
    