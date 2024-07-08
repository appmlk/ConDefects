N,L,R=map(int,input().split())
A=list(map(int,input().split()))

g=[0]*N

for i in range(N):
    M=A[i]%(L+R)
    g[i]=M
  
res=0
for i in range(N):
    res^=g[i]

print('First' if res!=0 else 'Second')