n,x=map(int,input().split())
L=[[0,0]]
for _ in range(n):
    a,b=map(int,input().split())
    L+=[[L[-1][0]+a+b,b]]
ans=[]
for i in range(1,n+1):
    ans+=[L[i][0]+L[i][1]*max(0,x-i)]
print(min(ans))