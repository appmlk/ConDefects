T = int(input())
for _ in range(T):
  A = list(map(int, input().split()))
  P = list(map(int, input().split()))
  num_reviews = sum(A)
  original_score = sum([A[i] * (i + 1) for i in range(5)])
  # print(original_score)
  
  
  rhs = 3 * num_reviews - original_score
  if original_score >= 3 * num_reviews:
    print(0)
    continue
  
  def test(x):
    return 2 * x >= rhs
    
  ok = 10**18
  ng = 0
  while abs(ok - ng) > 1:
    mid = (ok + ng) // 2
    if test(mid):
      ok = mid
    else:
      ng = mid
  k5 = ok
  ans = k5 * P[4]
  
  def test2(x):
    return x >= rhs
    
  ok = 10**18
  ng = 0
  while abs(ok - ng) > 1:
    mid = (ok + ng) // 2
    if test2(mid):
      ok = mid
    else:
      ng = mid
  
  k4 = ok
  ans = min(ans, k4 * P[3])
  
  def test3(x):
    return 2 * x >= rhs - 1
    
  ok = 10**18
  ng = 0
  while abs(ok - ng) > 1:
    mid = (ok + ng) // 2
    if test3(mid):
      ok = mid
    else:
      ng = mid
  
  ans = min(ok * P[4] + P[3], ans)
  
  print(ans)
      
  
  