

n,k,q=map(int,input().split())
a=list(map(int,input().split()))
l=list(map(int,input().split()))

masu=[ 0 for _ in range(n)]

for i in range(k):
    masu[a[i] -1] = 1

#print(masu)

for i in range(q):
    c=0
    for j in range(n):
        if masu[j] == 1:
            c+=1

        if c==l[i]:
            if j+1>=n:
                continue 

            if masu[j+1]==0:
                masu[j+1]=masu[j]
                masu[j]=0
                continue


for i in range(n):
    if masu[i] ==1:
        print(i+1 , end=' ')



