n,m=map(int,input().split())
cps=[]
for i in range(n):
    tmp=list(map(int,input().split()))
    cps.append(tmp)
INF=1001001001001
dp=[INF]*(m+1)
dp[0]=0
for i in range(1,m+1):
  for lst in cps:
    c=lst[0]
    p=lst[1]
    s=lst[2:]
    zr=0
    now=0
    for j in s:
      if j==0:
        zr+=1
    num=p-zr
    for j in s:
      id=max(0,i-j)
      now+=dp[id]/num
    #if i==1:print("now",c,zr,now,num)
    dp[i]=min(dp[i],now+c*p/num)
print(dp[m])
#print(dp)