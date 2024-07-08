n=int(input())
q=list(map(int,input().split()))
a=list(map(int,input().split()))
b=list(map(int,input().split()))
am=10**6
for i in range(n):
    if a[i]==0:
        continue
    if am>q[i]//a[i]:
        am=q[i]//a[i]

bm=10**6
for i in range(n):
    if b[i]==0:
        continue
    if bm>q[i]//b[i]:
        bm=q[i]//b[i]
ans=am
t=0
k=[]
for i in range(n):
    if am*a[i]==0:
        k.append(q[i])
    else:
        k.append(q[i]-(am*a[i]))
kk=False
def check():
    for i in range(n):
        if k[i]<b[i]:
            return False
    return True
for i in range(am):
    while check():
        t+=1
        if t==bm:
            kk=True
            break
        for o in range(n):
            k[o]-=b[o]
    for o in range(n):
        k[o]+=a[o]
    if ans<t+am-i:
        ans=t+am-i
    if kk:
        break
print(ans)
    
    
    
    
