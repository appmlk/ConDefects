R=range;f=lambda:map(int,input().split());N,M=f()
I=1<<60
D=[[I]*N for x in R(N)]
for m in R(M):u,v,c=f();D[u-1][v-1]=min(D[u-1][v-1],c)
for a in R(N**3):i,j,k=a//N//N,a//N%N,a%N;D[j][k]=min(D[j][k],D[j][i]+D[i][k])
d=[[I]*N for b in R(1<<N)]
for b in R(1,1<<N):
 L=[i for i in R(N)if b&1<<i]
 if len(L)==1:
  d[b][L[0]]=0
 else:
  for i in L:
   for j in L:
    d[b][i]=min(d[b][i],d[b^1<<i][j]+D[j][i]+I*(i==j))
a=min(d[-1]);print([a,"No"][a>I//2])