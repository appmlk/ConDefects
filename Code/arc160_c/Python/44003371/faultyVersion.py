


from collections import Counter
MOD=998244353
N=int(input())
A=list(map(int, input().split()))
M=max(A)
C=Counter(A)

dp=[1]

for a in range(1,M+1):
    c=C[a]
    dp=[0]*c+dp
    ndp=[0]*(len(dp)//2+2)
    for i in range(len(dp)):
        ndp[i//2+1]-=dp[i]
        ndp[0]+=dp[i]
        ndp[i//2+1]%=MOD
        ndp[0]%=MOD
    for i in range(len(ndp)-1):
        ndp[i+1]+=ndp[i]
        ndp[i+1]%=MOD
    dp=ndp
print(sum(dp)%MOD)