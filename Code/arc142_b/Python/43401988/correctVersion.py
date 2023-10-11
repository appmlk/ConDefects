n=int(input())
a=[[i*n+j+1 for j in range(n)]for i in range(n)]
# for i in a:print(i)
for i in range(n):
    # if i%2==0:continue
    for j in range(n//2):
        a[i][2*j],a[i][2*j+1]=a[i][2*j+1],a[i][2*j]
for i in a:print(*i)