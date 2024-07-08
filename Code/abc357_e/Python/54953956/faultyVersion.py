from atcoder.scc import*
N,*A=map(int,open(0).read().split())
G=SCCGraph(N)
for i in range(N):
 G.add_edge(i,A[i]-1)
d=[0]*N
for g in G.scc()[::-1]:
 for v in g:d[v]=len(g)*-~d[A[g[0]]-1]
print(sum(d))