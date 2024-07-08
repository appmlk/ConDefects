N,M = map(int,input().split())
S = set()
for _ in range(M):
  A,B = map(int,input().split())
  S.add((A+B)%N)
  
for i in range(M+1):
  if len(S)==M: break
  S.add(i)

print(N*M)
for v in S:
  for a in range(N):
    print(a+1,(v-a)%N+1)