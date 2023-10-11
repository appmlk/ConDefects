n=int(input())
a=[]
a=list(map(int,input().split()))
a.sort()
c1=0
c2=0
for i in range(n//2,-1,-1):
    if a[i]==a[n//2]:
        c1+=1
    else:
        break
for i in range(n//2+1,n):
    if a[i]==a[n//2]:
        c2+=1
    else:
        break
if (n+1)//2-c1>c2:
    print("Yes")
else:
    print("No")