n = int(input())
a = list(map(int,input().split()))
ikeru = [[] for i in range(n)]
for i in range(n-1):
	u, v = map(int,input().split())
	u-=1; v-=1
	ikeru[u].append(v)
	ikeru[v].append(u)

def canreach(x):
	#x以上に到達可能か？
	#部分木DP
	mada = [~0, 0]
	tansaku = [0] * n
	tansaku[0] = 1
	dp = [0] * n
	while mada:
		i = mada.pop()
		if i >= 0:
			for j in ikeru[i]:
				if tansaku[j] == 0:
					tansaku[j] = 1
					mada.append(~j)
					mada.append(j)
		else:
			i = ~i
			tmp = 0
			for j in ikeru[i]:
				if tansaku[j] == 2:
					tmp += dp[j]
			tmp = max(0, tmp-1)
			if i >= 1:
				if a[i-1] >= x:
					tmp += 1
			dp[i] = tmp
			tansaku[i] = 2
	if dp[0] >= 1:
		return True
	return False
	

suki = 10**9 + 5
kirai = 0
while suki - kirai > 1:
	targ = (suki + kirai) // 2
	if canreach(targ):
		kirai = targ
	else:
		suki = targ

print(kirai)