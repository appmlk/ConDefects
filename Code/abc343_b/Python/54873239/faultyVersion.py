N=int(input())
for i in range(N):
	A=list(map(int,input().split()))
	ans=[]
	for j in range(N):
		if A[j]:
			ans.append(j+1)
	print(ans)