N=int(input())
P=[]
T=0
total=0
for _ in range(N):
    x,y,z=list(map(int, input().split()))
    if x>y:
        T+=z
    else:
        P.append((x,y,z))
    total+=z
need = total//2+1
if need<=T:
    print(0)
    exit()
need-=T
dp = [[10**15]*(need+1) for _ in range(len(P)+1)]
dp[0][0]=0

for i in range(len(P)):
    x,y,z = P[i]
    dxy=(y-x)//2+1
    for j in range(need):
        if dp[i][j]!=10**15:
            dp[i+1][j]=min(dp[i+1][j],dp[i][j])
            dp[i+1][min(need,j+z)]=min(dp[i+1][min(need,j+z)],dp[i][j]+dxy)
print(dp[-1][-1])