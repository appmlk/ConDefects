from itertools import product

N,K,P=map(int,input().split())
dp=[10**18]*(P+1)**K
dp[0]=0

for i in range(N):
    I=list(map(int,input().split()))
    for p in product(reversed(range(P+1)),repeat=K):
        pn=[0]*K
        for j in range(K):
            pn[j]=min(P,p[j]+I[j+1])
        pi=0
        for pp in p:
            pi*=(P+1)
            pi+=pp
        pni=0
        for pp in pn:
            pni*=(P+1)
            pni+=pp
        dp[pni]=min(dp[pni],dp[pi]+I[0])
print(dp[-1] if dp[-1]<10**10 else -1)