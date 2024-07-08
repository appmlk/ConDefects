N,K=map(int,input().split())
A=list(map(int,input().split()))
i,num=0,0
for x in range(N):
    if A[x]+i<=K:
        i=i+A[x]
        print(i,"now1")
    elif A[x]+i>K:
        num+=1
        i=0
        i=i+A[x]
        print(i,"now2")
print(num+1)