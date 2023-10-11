import sys
sys.setrecursionlimit(1000000000)
t=int(input())
s=[[]for i in range(2002)]
a=[]
def mdfs(x,y,z):
    p=[0for i in range(y+2)]
    for i in s[x]:
        (f,d)=mdfs(i,y,z)
        if f:
            return (1,p)
        p[0]+=d[0]
        if p[0]>1:
          return(0,p)
        for j in range(1,y+2):
            p[j]|=d[j]
    if a[x]==-1:
      p[0]+=1
    elif a[x]<=y:
      p[a[x]+1]=1
    cnt=0
    for j in range(1,y+1):
        if p[j]==0:
            cnt+=1
    if cnt==0 and p[y+1]==0 and p[0]<=1:
        return (1,p)
    elif cnt==1 and p[0]==1 and p[y+1]==0:
        return (1,p)
    else:
        return(0,p)
            
for i in range(t):
    n,k=map(int,input().split())
    for j in range(n):
        s[j]=[]
    p=list(map(int,input().split()))
    for j in range(n-1):
        s[p[j]-1].append(j+1)
    a=list(map(int,input().split()))
    f,t=mdfs(0,k,n)
    if f:
        print("Alice")
    else:
        print("Bob")