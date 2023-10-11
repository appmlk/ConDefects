N,K=map(int,input().split())
A=list(map(int,input().split()))

p=[]
for i in range(N):
    p.append([A[i],-i])
    
Maxsuu=-1
p.sort()
ans=10**10
for a,i in p:
    i*=-1
    if i>=K and Maxsuu !=-1:
        ans=min(ans,i-Maxsuu)
    elif K>i:
        Maxsuu=max(Maxsuu,i)

print(ans if ans!=10**10 else -1)