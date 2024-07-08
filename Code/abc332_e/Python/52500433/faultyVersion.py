ctypedef long long ll
from libcpp.vector cimport vector
cdef:
    ll N,D,S,i,j,k,w,b,now
    double mean
    vector[double] pre
    vector[ll] bi
    vector[vector[double]] dp

N,D=map(int,input().split())
W=list(map(int,input().split()))

mean=sum(W)/D
#print(mean)

for S in range(1<<N):
    w=0
    j=0
    for i in range(N):
        if S&(1<<i):
            w+=W[i]
            j+=1
    pre.push_back(((w-mean)**2)/D)
    bi.push_back(j)
    
dp.resize(1<<N)
for S in range(1<<N):
    dp[S].resize(D+1,10**20)
    b=bi[S]
    for k in range(1,min(D+1,b)):
        if k==1:
            dp[S][k]=pre[S]
        else:
            now=(S-1)&S
            while now>0:
                dp[S][k]=min(dp[S][k],dp[now][k-1]+pre[S^now])
                now=(now-1)&S
                
print(dp[(1<<N)-1][D])