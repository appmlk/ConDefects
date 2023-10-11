n=int(input())
a=list(map(int,input().split()))
q=[]
for i in range(n):
    if i==0:
        if a[0]>a[1]:
            a[0],a[1]=a[1],a[0]
            q.append(1)
    else:
        if a[i*2-1]<a[i*2] and  a[i*2+1]>a[i*2]:
            if a[i*2-1]<a[i*2+1]:
                a[i*2-1],a[i*2]=a[i*2],a[i*2-1]
                q.append(i*2)
            else:
                a[i*2],a[i*2+1]=a[i*2+1],a[i*2]
                q.append(i*2+1)
        elif a[i*2-1]<a[i*2]:
            a[i*2-1],a[i*2]=a[i*2],a[i*2-1]
            q.append(i*2)
        elif a[i*2]>a[i*2+1]:
            a[i*2],a[i*2+1]=a[i*2+1],a[i*2]
            q.append(i*2+1)
print(len(q))
print(*q)