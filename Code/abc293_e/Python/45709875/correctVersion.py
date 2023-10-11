a,x,m=map(int,input().split())
if a==1:print(x% m)
else:
	t=pow(a,x,(a-1)*m)
	if t==0:t+=(a-1)*m
	print((t-1)//(a-1))