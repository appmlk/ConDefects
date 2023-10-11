N,M,T=list(map(int, input().split()))
A=list(map(int, input().split()))
d={}
for _ in range(M):
    x,y=list(map(int, input().split()))
    d[x]=y

for i in range(N-1):
    if i+1 in d:
        T+=d[i+1]
    if A[i]>=T:
        print("No")
        exit()
    T-=A[i]
print("Yes")