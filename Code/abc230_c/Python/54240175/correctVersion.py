N,A,B = map(int,input().split())
P,Q,R,S = map(int,input().split())

k1_min = max(1-A,1-B)
k1_max = min(N-A,N-B)
k2_min = max(1-A,B-N)
k2_max = min(N-A,B-1)

Z = [['.' for _ in range(S-R+1)] for _ in range(Q-P+1)]

if A<B:
  for i in range(P,Q+1):
    for j in range(R,S+1):
      if i == j-(B+k1_min)+1:
        Z[i-P][j-R] = '#'
      if i+j == A+B:
        Z[i-P][j-R] = '#'
else:
  for i in range(P,Q+1):
    for j in range(R,S+1):
      if i-(A+k1_min)+1 == j:
        Z[i-P][j-R] = '#'
      if i+j == A+B:
        Z[i-P][j-R] = '#'
  

for i in Z:
  print(''.join(i))