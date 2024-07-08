Q=int(input())
for _ in range(Q):
  A=list(map(int,input().split()))
  v=list(map(int,input().split()))
  if v[4]<A[4]:
    print('No')
    continue
  v[4]-=A[4]
  A[4]=0
  if v[3]+v[4]<A[3]:
    print('No')
    continue
  if v[3]>=A[3]:
    v[3]-=A[3]
    A[3]=0
  else:
    A[3]-=v[3]
    v[3]=0
  v[0]+=A[3]
  v[4]-=A[3]
  A[3]=0
  if v[2]+v[3]+v[4]<A[2]:
    print('No')
    continue
  if v[2]>=A[2]:
    v[2]-=A[2]
    A[2]=0
  else:
    A[2]-=v[2]
    v[2]=0
  if v[4]>=A[2]:
    v[1]+=A[2]
    v[4]-=A[2]
    A[2]=0
  else:
    v[1]+=v[4]
    A[2]-=v[4]
    v[4]=0
  v[0]+=A[2]
  v[3]-=A[2]
  A[2]=0
  n=2*(v[4]+v[3])+v[2]+v[1]
  m=5*v[4]+4*v[3]+3*v[2]+2*v[1]+v[0]
  if n>=A[1] and m>=2*A[1]+A[0]:
    print('Yes')
  else:
    print('No')