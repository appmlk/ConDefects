import sys
input=sys.stdin.readline

import heapq
N,M,L=map(int,input().split())
A=list(map(int,input().split()))
b=list(map(int,input().split()))
ban=set()
for i in range(L):
    p,q=map(int,input().split())
    ban.add(p*1000000+q)

B=[[b[i],i+1] for i in range(M)]
#A.sort(reverse=True)
B.sort(reverse=True)
cur=[0]*N
setmeal=[]
for i in range(N):
    heapq.heappush(setmeal,(-A[i]-B[0][0],i))

while setmeal:
    x,y=heapq.heappop(setmeal)
    if (y+1)*1000000+B[cur[y]][1] not in ban:
        print(-x)
        exit()
    
    else:
        cur[y]+=1
        if cur[y]<M:
            heapq.heappush(setmeal,(-A[y]-B[cur[y]][0],y))