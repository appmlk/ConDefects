n,m=map(int,input().split())
s=input()
c=list(map(int,input().split()))
color=[[] for i in range(n+1)]
for i in range(n):
    color[c[i]].append(i)
ans=[0]*n
for i in range(1,n+1):
    l=len(color[i])
    for j in range(l):
        ans[color[i][j]]=color[i][(j-1)%l]
for i in range(n):
    ans[i]=s[ans[i]]
print(*ans,sep="")