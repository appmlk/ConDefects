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
from collections import defaultdict
import sys
sys.setrecursionlimit(10**8)
N,M=I.MI()
p=I.LMI()
xy=I.LLI(M)
dic=defaultdict(lambda : -1)
for x,y in xy:
    dic[x-1]=max(dic[x-1],y)
G=[[] for _ in range(N)]
for i in range(N-1):
    G[p[i]-1].append(i+1)
count=0
def dfs(i,hp):
    global count
    if hp<dic[i]:
        hp=dic[i]
    if hp>=0:
        count+=1
    for nex in G[i]:
        dfs(nex,hp-1)

dfs(0,0)
print(count)