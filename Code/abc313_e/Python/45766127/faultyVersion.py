# coding: utf-8
# Your code here!
MOD = 998244353
from itertools import groupby
N = int(input())
S = input()
last = 1
ans = 0
for key,val in groupby(S):
    val = list(val)
    key = int(key)
    if (key>1 and len(val)>1) or (last>1 and key>1):
        break
    if key==1:
        ans += (last-1)*ans+len(val)
    else:
        ans += 1
    ans %= MOD
    last = key
else:
    print((ans-1)%MOD)
    exit()
print(-1)
    
    