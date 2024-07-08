Q=int(input())
for _ in range(Q):
  A=list(map(int,input().split()))
  B=list(map(int,input().split()))
  if B[4]<A[4]:
    print('No')
    continue
  B[4]-=A[4]
  A[4]=0
  if A[3]>B[3]+B[4]:
    print('No')
    continue
  if B[3]>A[3]:
    B[3]-=A[3]
    A[3]=0
  else:
    A[3]-=B[3]
    B[3]=0
  B[4]-=A[3]
  B[0]+=A[3]
  if A[2]>B[2]+B[3]+B[4]:
    print('No')
    continue
  if B[2]>A[2]:
    B[2]-=A[2]
    A[2]=0
  else:
    A[2]-=B[2]
    B[2]=0
  if B[4]>A[2]:
    B[4]-=A[2]
    B[1]+=A[2]
    A[2]=0
  else:
    A[2]-=B[4]
    B[1]+=B[4]
    B[4]=0
  B[3]-=A[2]
  A[0]+=A[2]
  if A[1]>B[1]+B[2]+2*B[3]+2*B[4]:
    print('No')
    continue
  if A[0]+2*A[1]>B[0]+2*B[1]+3*B[2]+4*B[3]+5*B[4]:
    print('No')
  else:
    print('Yes')