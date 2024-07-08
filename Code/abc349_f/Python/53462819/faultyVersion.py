def tridiv(n):
    pf, f = dict(), 2
    while f*f<=n:
        if n%f == 0 :
            if f not in pf : pf[f]=0
            pf[f]+=1
            n//=f
        else : f = f+2 if f>2 else 3
    if n>1 : 
        if n not in pf : pf[n]=0
        pf[n]+=1
    return [k**v for k,v in pf.items()]
mod = 998244353
N,M = map(int,input().split())
A = [*map(int,input().split())]
d = tridiv(M)
K = len(d)
cnt = [0]*(1<<K)
for n in range(N):
    if M%A[n] : continue
    cnt[sum(int(A[n]%d[k]==0)*(1<<k) for k in range(K))] += 1
dp = [0 for n in range(1<<K)]
dp[0] = 1
p2 = [pow(2,k,mod) for k in range(max(cnt)+1)]
for i in range(1<<K):
    ndp = [0 for n in range(1<<K)]
    for j in range(1<<K):
        ndp[j] += dp[j]
        ndp[j] %= mod
        ndp[i|j] += dp[j]*(p2[cnt[i]]-1)
        ndp[i|j] %= mod
    dp = ndp
print(dp[(1<<K)-1])