H,W,K=map(int,input().split())
S=[input() for i in range(H)]
dic=dict({'o':0,'x':1,'.':2})
ans=10**10
for i in range(H):
	chk=[0,0,0]
	if W<K:
		break
	for j in range(K):
		chk[dic[S[i][j]]]+=1
	if chk[1]==0:
		ans=min(ans,chk[2])
	for j in range(K,W):
		chk[dic[S[i][j-K]]]-=1
		chk[dic[S[i][j]]]+=1
		if chk[1]==0:
			ans=min(ans,chk[2])
for j in range(W):
	chk=[0,0,0]
	if H<K:
		break
	for i in range(K):
		chk[dic[S[i][j]]]+=1
	if chk[1]==0:
		ans=min(ans,chk[2])
	for i in range(K,H):
		chk[dic[S[i-K][j]]]-=1
		chk[dic[S[i][j]]]+=1
		if chk[1]==0:
			ans=min(ans,chk[2])
print(ans)