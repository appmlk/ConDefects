from math import log2
from collections import Counter

import sys
n, *alists = map(int, sys.stdin.read().split())

MOD = 998244353 

if n == 1:
    print(1)
    exit()

maxlen = max(alists) + int(log2(n))

li = [0] * (maxlen + 1)

co = Counter(list(alists))

for k,v in co.items():
    li[k] += v

dp = [1]

for i in range(1,maxlen):
    newdp = [0] * ((li[i] + len(dp))//2+2 )
    
    for j, dpi in enumerate(dp):
        newdp[0] += dpi
        newdp[(li[i]+j)//2+1] -= dpi
        # print(i,j,newdp)
    
    newdp[0] %= MOD

    for k in range(len(newdp)-1):
        newdp[k+1] += newdp[k]
        newdp[k+1] %= MOD
    # print(newdp)
    dp = newdp

print(sum(dp) % MOD)