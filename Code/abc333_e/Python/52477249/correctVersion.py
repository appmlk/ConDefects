n=int(input())
tx=[list(map(int,input().split())) for _ in range(n)]

K_max,k=0,0
ans=[]
cnt=[0]*(n+1)
for t,x in tx[::-1]:
  if t==1:
    if cnt[x]<0:
      cnt[x]+=1
      ans.append(1)
      k-=1
      
    else:
      ans.append(0)
    
  else:
    cnt[x]-=1
    k+=1
  
  K_max=max(K_max,k)
  
if min(cnt)==0:
  print(K_max)
  print(*ans[::-1])

else:
  print(-1)