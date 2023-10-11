import sys
input = sys.stdin.readline
from collections import deque,Counter

N,K=map(int,input().split())
A=sorted(map(int,input().split()))
C=Counter(A)

mod=998244353

FACT=[1]
for i in range(1,N+10):
    FACT.append(FACT[-1]*i%mod)

now=1
Q=deque(A)
ANS=1

while Q:
    if Q[-1]+Q[0]>=K:
        Q.pop()
        ANS=ANS*now%mod
        now+=1
    else:
        Q.popleft()
        ANS=ANS*now%mod
        now-=1
     
for v in C.values():
    if v==1:
        continue
    ANS=ANS*pow(FACT[v],mod-2,mod)%mod

print(ANS)