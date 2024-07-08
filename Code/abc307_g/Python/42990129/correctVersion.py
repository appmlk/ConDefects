N=int(input())
A=list(map(int,input().split()))

x=sum(A)//N
r=sum(A)%N

SUM=[0]
for a in A:
    SUM.append(SUM[-1]+a)

DP=[1<<100]*(r+1) # x+1にしたものの個数
DP[0]=0

for i in range(N-1):
    NDP=[1<<100]*(r+1)

    for j in range(r+1):
        if DP[j]==1<<100:
            continue

        # index iまでの総和は
        S=(x+1)*j # x+1がj個
        S+=x*(i-j) # xがi-j個

        rest=S-SUM[i] # 差分。Sの方がrestだけ多いということは
        now=A[i]-rest # A[i]はrestだけ小さい

        # xにするとき
        NDP[j]=min(NDP[j],DP[j]++abs(now-x))
        
        # x+1にするとき
        if j+1<r+1:
            NDP[j+1]=min(NDP[j+1],DP[j]+abs(now-(x+1)))
    DP=NDP

print(min(DP[r-1],DP[r]))