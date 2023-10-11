T=int(input())
for i in range(T):
  N,A,B,X,Y,Z=map(int, input().split())
  Y,Z=min(Y,A*X),min(Z,B*X)
  if Y/A>Z/B:
    Y,Z,A,B=Z,Y,B,A
  ans=10**20
  if N//A<2*A:
    for a in range(N//A+1):
      c=Y*a+Z*((N-A*a)//B)+X*((N-A*a)%B)
      ans=min(c,ans)
    print(ans)
  else:
    for b in range(A):
      c=Z*b+Y*((N-B*b)//A)+X*((N-B*b)%A)
      if N-B*b<0:
        break
      ans=min(c,ans)
    print(ans)