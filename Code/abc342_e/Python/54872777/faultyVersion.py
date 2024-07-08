import heapq
N,M=map(int,input().split())
edge=[[] for i in range(N)]
for i in range(M):
	l,d,k,c,A,B=map(int,input().split())
	A-=1;B-=1
	edge[B].append((l,d,k,c,A))
P=[(-10**20,N-1)]
heapq.heapify(P)
ans=[-1]*N
while P:
	time,pos=heapq.heappop(P)
	time*=-1
	if ans[pos]>0:
		continue
	ans[pos]=time
	for l,d,k,c,A in edge[pos]:
		if ans[A]>0:
			continue
		kt=(time-c-l)//d
		if kt<0:
			continue
		if kt>k-1:
			kt=k-1
		heapq.heappush(P,(-kt*d-l,A))
for a in ans:
	print(a if a>=0 else 'Unreachable')