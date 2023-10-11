n=int(input())
pp=[]
for i in range(n):
    x,y,p=map(int,input().split())
    pp.append((x,y,p))

from collections import deque
def bfs(node,s):
    used=[False]*n
    d=deque()
    d.append(node)
    used[node]=True
    while d:
        tmp=d.popleft()
        p=s*pp[tmp][2]
        for i in range(n):
            if i==tmp:
                continue
            dist=abs(pp[tmp][0]-pp[i][0])+abs(pp[tmp][1]-pp[i][1])
            if not(used[i]) and p>=dist:
                used[i]=True
                d.append(i)
        
    return all(used)

l=1
r=4*10**9
while r-l>1:
    frag=True
    mid=(l+r)//2
    for i in range(n):
        if bfs(i,mid):
            r=mid
            frag=False
            break
    if frag:
        l=mid
print(r)