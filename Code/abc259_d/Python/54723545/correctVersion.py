from collections import defaultdict
import sys
sys.setrecursionlimit(10**5)

N = int(input())
sx,sy,tx,ty = list(map(int,input().split()))
circles = []
for i in range(N):
    x,y,r = list(map(int,input().split()))
    circles.append((x,y,r))
s_maru = -1
t_maru = -1
for i in range(N):
    x,y,r = circles[i]
    if((sx-x)**2+(sy-y)**2 == r**2):
        s_maru = i
    if((tx-x)**2+(ty-y)**2 == r**2):
        t_maru = i
    if(s_maru == t_maru and s_maru != -1):
        print("Yes")
        exit()

edge = defaultdict(set)
for i in range(N):
    for j in range(N):
        if(i == j):continue
        x1,y1,r1 = circles[i]
        x2,y2,r2 = circles[j]
        if((r1-r2)**2 <= (x2-x1)**2+(y2-y1)**2 <= (r1+r2)**2):
            edge[i].add(j)
            edge[j].add(i)

used = [False for _ in range(N)]
def dfs(n):
    used[n] = True
    for i in edge[n]:
        if(i == t_maru):
            print("Yes")
            exit()
        if(not used[i]):
            dfs(i)

dfs(s_maru)
print("No")