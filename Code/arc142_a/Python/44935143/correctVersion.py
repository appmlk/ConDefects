N,K = map(int,input().split())
RK = int(str(K)[::-1])
ans = 0
if K<=RK:
	if K!=RK:
		while RK<=N:
			ans += 1
			RK *= 10
	while K<=N:
		ans += 1
		K *= 10
print(ans)