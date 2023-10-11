n=int(input())
a=list(map(int,input().split()))
if a.count(a[0])==n:
    print()
    exit()
ind=0
pre=0
d=dict()
while ind!=n:
    cnt=0
    pre=ind-1
    while ind!=n-1 and a[ind]==a[ind+1]:ind+=1
    if ind==n-1:break
    # print(pre,ind)
    if pre!=-1 and a[pre]>a[ind] and d.get(a[pre],0)<=1:
        ans=[]
        for i in range(n):
            if a[i]!=a[pre]:ans.append(a[i])
        print(*ans)
        exit()
    # print(ind)
    if a[ind] not in d:d[a[ind]]=1
    else:d[a[ind]]+=1
    # print(d)
    ind+=1
mx=max(a)
ans=[]
for i in range(n):
    if a[i]!=mx:ans.append(a[i])
print(*a)
        
        
