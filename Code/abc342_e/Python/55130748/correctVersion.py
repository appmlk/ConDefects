from heapq import*;f=lambda:map(int,input().split());N,M=f();G=[[]for x in" "*N*2];I=1<<60;T=[-I]*N+[I];Q=[(-I,N)]
for m in " "*M:*t,b=f();G[b]+=[t]
while Q:
 s,x=heappop(Q)
 for l,d,k,c,y in G[x]:
  t=min((a:=-s-c-l)//d*d,(k-1)*d)+l
  if T[y]<t and a>=0:T[y]=t;heappush(Q,(-t,y))
for t in T[1:N]:print([t,"Unreachable"][t==-I])