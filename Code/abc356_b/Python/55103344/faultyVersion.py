N,M=map(int, input().split())
A = list(map(int, input().split()))
X = [list(map(int, input().split())) for _ in range(N)]
for m in range(M):
  a=0
  for n in range(N):
    a+=X[n][m]
  if a<=A[m]:
    print ("No")
    exit(0)
print ('Yes')