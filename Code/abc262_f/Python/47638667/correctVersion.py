N,K = map(int,input().split())
P = list(map(int,input().split()))
d = {}
for i in range(N):
  d[P[i]] = i

for p in range(1,N+1):
  k = d[p]
  kk = N - k
  if min(k,kk) > K:
    continue
  Q = P[:k]
  R = P[k:]
  ans = []
  n = kk
  if n > K:
    ans = P
  else:
    for r in R:
      if len(ans) == 0:
        ans.append((r,0))
        continue
      while ans[-1][0] > r:
        ans.pop()
        if len(ans) == 0:
          break
      ans.append((r,0))
    for q in Q:
      while ans[-1][0] > q and not(n == K and ans[-1][1] == 1):
        r,cc = ans.pop()
        n += cc
      ans.append((q,1))
    while n < K:
      r,c = ans.pop()
      n += c
    for i in range(len(ans)):
      ans[i] = ans[i][0]

  ans_ = []
  n = k
  if n > K:
    ans_ = P
  else:
    for r in R:
      if len(ans_) == 0:
        ans_.append(r)
        continue
      while ans_[-1] > r and n < K:
        ans_.pop()
        n += 1
        if len(ans_) == 0:
          break
      ans_.append(r)
    while n < K:
      n += 1
      ans_.pop()
  ans = min(ans,ans_)
  print(*ans)
  exit()
print(*P)