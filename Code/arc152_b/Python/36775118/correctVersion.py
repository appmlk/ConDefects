from bisect import *
N,L = map(int,input().split())
inf = L*4
A = list(map(int,input().split()))
ans = inf
for a in A:
  b = L-a
  p = bisect_left(A,b)
  p1 = A[p] if p < N else inf
  p2 = A[p-1]
  ans_ = 2*L + min(abs(b-p1),abs(b-p2))*2
  ans = min(ans,ans_)
print(ans)