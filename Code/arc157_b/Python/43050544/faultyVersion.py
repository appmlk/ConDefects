N,K=map(int,input().split())
S=list(input())+["Z"]
xcount=0
for i in range(N):
    if S[i]=="X":
        xcount+=1
if K>xcount:
    for i in range(N):
        if S[i]=="X":
            S[i]="Y"
        elif S[i]=="Y":
            S[i]="X"
    K=K-xcount

hq=[]
from heapq import *
heapify(hq)
for i in range(N+1):
    if i==0:
        count=1
        l=0
        continue
    if S[i]!=S[i-1]:
        if S[i-1]=="X":
            heappush(hq,(count,l,i))    
        count=0
        l=i
    count+=1

s=(-1,-1,-1)
e=(-1,-1,-1)
while len(hq) and K:
    c,l,r=heappop(hq)
    if l==0:
        s=(c,l,r)
        continue
    if r==N:
        e=(c,l,r)
        continue
    if K>=c:
        for i in range(l,r):
            S[i]="Y"
        K-=c
    else:
        for i in range(l,l+K):
            S[i]="Y"
        K=0
    
if K:
    c,l,r=s
    for i in reversed(range(max(0,r-K),r)):
        S[i]="Y"
        K-=1
if K:
    c,l,r=e
    for i in range(l,r):
        S[i]="Y"

ans=0
for i in range(1,N):
    if S[i]==S[i-1]=="Y":
        ans+=1

print(ans)






