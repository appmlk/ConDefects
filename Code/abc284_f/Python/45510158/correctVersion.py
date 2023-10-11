N = int(input())
T = input()

b = 26
h = 2**61 - 1
H = pow(b,N-1,h)
HH = pow(b,-1,h)
ans = 0
A = []
for i in range(N):
  if i == 0:
    X = 0
    Y = 0
    Z = 0
    for j in range(N,2*N):
      Z *= b
      Z += (ord(T[j])-97)
      Z %= h
    for j in range(N):
      n = N - 1 - j
      Y *= b
      Y += (ord(T[n])-97)
      Y %= h
    if Y == Z:
      ans = 1
      A.append(i)
  X *= b
  X %= h
  X += (ord(T[i])-97)
  Y -= (ord(T[i])-97)
  Y *= HH
  Y %= h
  Y += (ord(T[i+N])-97)*H
  Y %= h
  Z -= (ord(T[i+N])-97)*pow(b,N-1-i,h)
  Z %= h
  n = N - 1 - i
  S = X*pow(b,n,h) % h
  S += Z
  S %= h
  if S == Y:
    ans = 1
    A.append(i+1)

if ans == 0:
  print(-1)
  exit()
i = A.pop()
ans = []
for x in range(i):
  ans.append(T[x])
for x in range(i+N,2*N):
  ans.append(T[x])
S = ''.join(ans)
print(S)
print(i)