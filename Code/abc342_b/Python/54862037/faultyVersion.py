N=int(input())
P=list(map(int,input().split()))
flg=[[False]*N for i in range(N)]
for i in range(1,N):
	for j in range(i):
		flg[j][i]=True
Q=int(input())
for i in range(Q):
	A,B=map(int,input().split())
	if flg[A-1][B-1]:
		print(A)
	else:
		print(B)