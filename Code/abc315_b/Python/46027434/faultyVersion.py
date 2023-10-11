M=int(input())
D=list(map(int,input().split()))
half=(sum(D)+1)/2

for i in range(M):
  if half-D[i]<=0:
    print(i+1,half)
    break
  else:
    half-=D[i]