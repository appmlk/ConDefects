from collections import defaultdict
N,H = map(int,input().split())
T = set([0])
dic = defaultdict(int)
for i in range(N):
  t,d = map(int,input().split())
  T.add(t)
  dic[t] = max(dic[t],d)
T = list(T)
M = []
for t in T:
  M.append([t,dic[t]])
M.sort()
n = len(M)
T,D,S = [0]*n,[0]*n,[0]*n
for i in range(n):
  T[i],D[i] = M[i]

DD = [0 for i in range(n)]
DD[-1] = D[-1]
for i in range(n-2,-1,-1):
  DD[i] = max(DD[i+1],D[i])

l = 0
S = 0
P = []
for i in range(1,n):
  t,d = T[i],D[i]
  dd = DD[i]
  r = t - 1
  if S == 0:
    P.append((l,r,1,dd))
  else:
    x = S // dd
    if x >= r:
      P.append((l,r,0,S))
    elif x < l:
      P.append((l,r,1,dd))
    else:
      P.append((l,x,0,S))
      P.append((x+1,r,1,dd))
  S = max(S,t*d)
  l = t
P.append((l,10**18,0,S))

def f(X):
  Q = P[:]
  h = H
  while len(Q):
    l,r,f,s = Q.pop()
    if l > X:
      continue
    d = 0
    if f == 0:
      d += (X - l + 1) * s
    else:
      d += s*(r*(r+1) - l*(l-1))//2
    h -= d
    X = l - 1
  if h <= 0:
    return True
  return False

l,r = 0,H
while r - l > 1:
  m = (l + r) // 2
  if f(m):
    r = m
  else:
    l = m
print(r)