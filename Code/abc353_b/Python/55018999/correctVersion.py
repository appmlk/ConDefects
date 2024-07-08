N,K=map(int,input().split())
A=list(map(int,input().split()))
i,num=0,0
for x in range(N):
    if A[x]+i<=K:
        i=i+A[x]
    elif A[x]+i>K:
        num+=1
        i=0
        i=i+A[x]
print(num+1)