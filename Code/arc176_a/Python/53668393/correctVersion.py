N, M = map(int,input().split())
print(N*M)
L = [0]*N
count = 0

for _ in range(M):
  a, b = map(int,input().split())
  d = (a-b)%N
  if L[d]==1:
    continue
  for _ in range(N):
    print(a,b)
    a += 1
    b += 1
    if a==N+1:
      a=1
    if b==N+1:
      b=1
  L[d]=1
  count += 1

for i in range(M):
  if count==M:
    break
  if L[i]==0:
    a, b = i+1,1
    for _ in range(N):
      print(a,b)
      a += 1
      b += 1
      if a==N+1:
        a=1
      if b==N+1:
        b=1
    L[i]=1
    count += 1
    


