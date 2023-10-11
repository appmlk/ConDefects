n,m=map(int,input().split())
s=[input() for _ in range(n)]
t={input() for _ in range(m)}
all=0
for i in range(n):
    all+=len(s[i])
und=16-all-(n-1)
add=[]
def dfs1(i,L):
    if i==n:
        global add
        add+=[L[:]]
    else:
        for j in range(n):
            if L[j]==-1:
                L[j]=i
                dfs1(i+1,L)
                L[j]=-1
dfs1(0,[-1]*n)
addund=[]
def dfs2(i,res,L):
    if i==n-1:
        global addund
        addund+=[L[:]]
    else:
        for j in range(res+1):
            L[i]=j
            dfs2(i+1,res-j,L)
dfs2(0,und,[0]*(n-1))
for a in add:
    for b in addund:
        ans=""
        for i in range(n-1):
            ans+=s[a[i]]+"_"*(1+b[i])
        ans+=s[a[-1]]
        if ans not in t and 3<=len(ans)<=16:
            print(ans)
            exit()
print(-1)