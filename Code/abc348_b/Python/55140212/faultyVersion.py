N=int(input())
X=[None for _ in range(N+1)]
Y=[None for _ in range(N+1)]
for n in range(1,N+1):
  X[n],Y[n]=map(int,input().split())
for i in range(1,N+1):
  k,d=0,0
  for j in range(1,N+1):
    dn=(X[i]-X[j])**2+(Y[i]-Y[j])**2
    if dn<d:
      k,d=j,dn
  print(k)