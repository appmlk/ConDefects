N,K = map(int,input().split())
S = [input() for _ in range(N)]

import functools
S.sort(key=functools.cmp_to_key(lambda x,y:-1 if x+y>y+x else 1))

dp = ['|']*(K+1)
dp[0]= ''
for i in range(N):
	for k in range(min(i,K),0,-1):
		dp[k] = min(dp[k],S[i]+dp[k-1])

print(dp[K])