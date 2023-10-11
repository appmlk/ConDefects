import sys
input=sys.stdin.readline
sys.setrecursionlimit(10**7)
n,m=map(int,input().split())
L=[[] for _ in range(n)]
for _ in range(m):
    u,v=map(int,input().split())
    L[u-1]+=[v-1]
    L[v-1]+=[u-1]
ans=0
def dfs(i,a):
    global ans
    ans+=1
    a.add(i)
    for j in L[i]:
        if j not in a:
            dfs(j,a)
    a.remove(i)
dfs(0,set())
print(ans)