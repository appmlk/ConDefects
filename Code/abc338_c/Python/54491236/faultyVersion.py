n=int(input())
q=list(map(int,input().split()))
a=list(map(int,input().split()))
b=list(map(int,input().split()))
ans=0
inf=float("inf")
for x in range(max(q)+1):
    y=inf
    for i in range(n):
        if q[i]<x*a[i]:
            y=-inf
            break
        elif b[i]>0:
            y=min((q[i]-x*a[i])//b[i],x)
    ans=max(ans,x+y)
print(ans)