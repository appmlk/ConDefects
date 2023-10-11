N,M,K=map(int,input().split())
mod=998244353

dp=[[0 for _ in range(M)] for _ in range(N)]

def one_d_prefix_sums(array):
    N=len(array)
    prefix_sums=[0 for _ in range(N)]
    for i,number in enumerate(array):
        if i==0:
            prefix_sums[i]=number
        else:
            prefix_sums[i]=number+prefix_sums[i-1]
    return prefix_sums

for j in range(M):
    dp[0][j]=1

for i in range(N):
    if i==0:
        continue
    prefix_sums=[0]+one_d_prefix_sums(dp[i-1])
    for j in range(M):
        dp[i][j]=(prefix_sums[M]-max(0,(prefix_sums[min(M,j+K)]-prefix_sums[max(0,j-K+1)])))%mod

print((sum(dp[-1]))%mod)