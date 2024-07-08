from itertools import product
N,S,M,L=map(int,input().split())
ans=10**20
for i,j,k in product(range(N),repeat=3):
	if 6*i+8*j+12*k<N:
		continue
	ans=min(ans,S*i+M*j+L*k)
print(ans)