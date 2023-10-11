a,b = map(int, input().split())

ct=[[0],[1],[2],[1,2],[4],[1,4],[2,4],[1,2,7]]

taka=ct[a]
ao=ct[b]
su=taka+ao
ans=set(su)
sim=sum(ans)
print(sim)