import math
n,m=map(int,input().split(' '))
ans=-1
for i in range(1, min(n,int(math.sqrt(m)))+1):
  k = (m+i-1)//i
  if(k>=1 and k<=n):
    if(ans==-1):
      ans=k*i
    else:
      ans=min(ans, k*i)
print(ans)