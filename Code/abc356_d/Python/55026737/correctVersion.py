N,M = map(int,input().split())
MOD = 998244353
ret = 0
for i in range(60):
    if(M & 2**i):
        tmp = N // ((2**i)*2)
        ret = ret + tmp*(2**i)
        tmp = N % ((2**i)*2)
        if(tmp >= (2**i)):
            ret = ret + tmp - (2**i) + 1
        ret = ret % MOD
print(ret)