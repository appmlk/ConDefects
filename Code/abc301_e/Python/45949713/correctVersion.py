class Input_kyopro:
    def II(self): return int(input())
    def MI(self): return map( int,input().split())
    def MS(self): return map(str,input().split())
    def LMI(self): return list(self.MI())
    def LMS(self): return list(self.MS())
    def LLI(self,N): return [self.LMI() for _ in range(N)]
    def LLS(self,N): return [self.LMS() for _ in range(N)]
    def LS(self,N): return [input() for _ in range(N)]
    def LSL(self,N): return [list(input()) for _ in range(N)]
    def LI(self,N): return [self.II() for _ in range(N)]
I=Input_kyopro()
#入力
from collections import deque
H,W,T=I.MI()
a=I.LSL(H)
dx=[1,0,-1,0]
dy=[0,1,0,-1]
start=[0,0]
g=[0,0]
ls=[]
for i in range(H):
    for j in range(W):
        if a[i][j]=='S':
            start=[i,j]
        if a[i][j]=='G':
            g=[i,j]
        if a[i][j]=='o':
            ls.append([i,j])
cnt=len(ls)
d=[[[0]*W for _ in range(H)] for _ in range(cnt)]
def dist(i,j):
    res=[[float('inf')]*W for _ in range(H)]
    res[i][j]=0
    q=deque()
    q.append([i,j])
    while q:
        i,j=q.popleft()
        for k in range(4):
            if 0<=i+dx[k]<H and 0<=j+dy[k]<W:
                if res[i+dx[k]][j+dy[k]]!=float('inf') or a[i+dx[k]][j+dy[k]]=='#':
                    continue
                res[i+dx[k]][j+dy[k]]=res[i][j]+1
                q.append([i+dx[k],j+dy[k]])
    return res

for i in range(cnt):
    d[i]=dist(ls[i][0],ls[i][1])
dp=[[float('inf')]*(cnt) for _ in range(1<<cnt)]
for i in range(cnt):
    dp[1<<i][i]=d[i][start[0]][start[1]]
for s in range(1,1<<cnt):
    for last in range(cnt):
        if dp[s][last]==float('inf'):
            continue
        for nx in range(cnt):
            if s>>nx&1:
                continue
            dp[s|1<<nx][nx]=min(dp[s|1<<nx][nx],dp[s][last]+d[last][ls[nx][0]][ls[nx][1]])
ans=-1
if dist(start[0],start[1])[g[0]][g[1]]<=T:
    ans=0
for s in range(1,1<<cnt):
    for last in range(cnt):
        if dp[s][last]+d[last][g[0]][g[1]]<=T:
            now=0
            for i in range(cnt):
                if s>>i&1:
                    now+=1
            ans=max(ans,now)
print(ans)