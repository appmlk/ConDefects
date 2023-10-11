n,m=map(int,input().split())
lst=[True]*(n+1);lst[0]=False
for i in range(m):
	a,b=map(int,input().split())
	lst[b]=False

print(-1 if sum(lst)!=1 else lst.index(1))
# print(lst)
