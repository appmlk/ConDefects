from collections import deque
from collections import defaultdict
from bisect import bisect_left
import sys
sys.setrecursionlimit(1000000)
from heapq import heappop, heappush
N,M=map(int, input().split())
Pos=[]
for _ in range(N):
    s=input()
    Pos.append(s)
dis1=[1<<60]*N
dis1[0]=0
que=deque()
que.append(0)
while(que):
    v=que.popleft()
    for i in range(M):
        if Pos[v][i]=="1":
            if dis1[v]+1 < dis1[v+i+1]:
                que.append(v+i+1)
                dis1[v+i+1]=dis1[v]+1
dis2=[1<<60]*N
dis2[-1]=0
for i in range(N-2, -1, -1):
    for j in range(M):
        if Pos[i][j]=="1":
            if dis2[i+j+1]+1 < dis2[i]:
                dis2[i]=dis2[i+j+1]+1
Ans=[]
for k in range(1, N-1):
    ans=1<<60
    for i in range(max(0, k-M+1),k):
        for j in range(M):
            if Pos[i][j]=="1" and i+j+1 != k:
                ans=min(ans, dis1[i]+1+dis2[i+j+1])
    
    if ans==1<<60: ans=-1
    Ans.append(ans)
print(*Ans)