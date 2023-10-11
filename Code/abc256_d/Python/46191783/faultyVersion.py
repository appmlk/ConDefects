n=int(input())
que=[]
for i in range(n):
    l,r=map(int,input().split())
    que.append((l,r))
que.sort()
now=0
ans=[]
while now<n:
    ans.append(que[now][0])
    tmp=que[now][1]
    if now==n-1:
        ans.append(tmp)
        break
    now+=1
    while tmp>=que[now][0]:
        tmp=max(tmp,que[now][1])
        now+=1
        if now==n:
            break    
    ans.append(tmp)

for i in range(len(ans)//2):
    print(ans[i],ans[i+1])