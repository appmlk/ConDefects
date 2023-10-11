n=int(input())
count=[[0]*10 for _ in range(10)]
time=[[] for _ in range(10)]
for i in range(n):
    s=input()
    for x in range(10):
        s_x=int(s[x])
        count[s_x][x]+=1
        time[s_x].append(x+(count[s_x][x]-1)*10)
ans=10**100
for i in range(10):
    ans=min(ans,max(time[i]))
print(ans)