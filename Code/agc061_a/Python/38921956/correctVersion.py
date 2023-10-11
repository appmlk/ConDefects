def ans(n,k,bit):
  b=1<<bit
  if n&b==0: return ans(n,k,bit-1)
  if n==b:
    if k%2==0: return k-1
    else: return k+1
  elif n==b+1:
    if k==1: return 2
    elif k==n-1: return n
    elif k%2==1: return k-2
    else: return k+2
  else:
    if k<=n-b: return ans(n-b,k,bit-1)
    elif k>=b+1: return ans(n-b,k-b,bit-1)+b
    else: return k
    
T=int(input())
for _ in range(T):
  N,K=map(int,input().split())
  print(ans(N,K,60))