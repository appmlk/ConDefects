N,L,R = map(int,input().split())
L -= 1
R -= 1
A = list(map(int,input().split()))
A.sort()
base = []
for i in range(N):
  a = A[i]
  for e in base:
    a = min(a,a^e)
  for i in range(len(base)):
    base[i] = min(base[i],a ^ base[i])
  if a > 0:
    base.append(a)
base.sort()

ans = []
for x in range(L,R+1):
  res = 1
  for j in range(60):
    if (x >> j) & 1:
      res ^= base[j]
  ans.append(res)
print(*ans)