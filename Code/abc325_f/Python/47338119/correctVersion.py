import sys
from collections import deque
input = sys.stdin.readline
LN = 1111111

def cnt(d):
    ret = []
    s,k = 0,0
    if d%s1[0] == 0: s = d//s1[0]
    else: s = d//s1[0]+1
    ssum = s1[0]*s
    remain = d-ssum
    ret.append([s,0])
    while s > 0:
        s -= 1
        remain += s1[0]
        if remain %s2[0] == 0: k = remain//s2[0]
        else: k = remain//s2[0]+1
        ret.append([s,k])
    return ret

N = int(input())
D = list(map(int,input().split()))
s1 = list(map(int,input().split())) #Len,Cost,K
s2 = list(map(int,input().split()))
ans = 10**24
dp = [LN]*(s1[2]+1) # idx : num of s1, val : num of s2
que = cnt(D[0])
for a,b in que: 
    if a > s1[2] or b > s2[2]: continue
    dp[a] = b
for i in range(1,N):
    tmp = [LN]*(s1[2]+1)
    que = cnt(D[i])
    for idx,val in enumerate(dp):
        if val == LN: continue
        for a,b in que:
            if idx+a > s1[2] or val+b > s2[2]: continue
            tmp[idx+a] = min(tmp[idx+a],val+b)
    dp = tmp
if dp.count(LN) == s1[2]+1: print(-1)
else:
    for i,j in enumerate(dp): 
        if j == LN: continue
        ans = min(ans,i*s1[1]+j*s2[1])
    print(ans)