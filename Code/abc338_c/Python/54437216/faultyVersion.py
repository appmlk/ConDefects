n=int(input())
q=tuple(map(int,input().split()))
a=list(map(int,input().split()))
b=list(map(int,input().split()))
amax=10**7
bmax=10**7
for k,i in enumerate(a):
    if i!=0:
        x=q[k]//i
        if x <amax:
            amax=x
x=0
for k,i in enumerate(b):
    if i!=0:
        x=q[k]//i
        if x <bmax:
            bmax=x
mi=10**7
if amax >bmax:
    ans=amax
    for i in range(amax+1)[::-1]:
        for k,j in enumerate(a):
            if b[k]!=0:
                y=q[k]-(i*j)
                x=y//b[k]
            else:
                x=10**7
            if x<mi:
                mi=x
        if mi+i>ans:
            ans=mi+i
else:
    ans=bmax
    for i in range(bmax+1)[::-1]:
        for k,j in enumerate(b):
            if a[k]!=0:
                y=q[k]-(i*j)
                x=y//a[k]
            else:
                x=10**7
            if x<mi:
                mi=x
        
        if mi+i>ans:
            ans=mi+i
print(ans)
        