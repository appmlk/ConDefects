n = int(input())
p = list(map(lambda x:int(x)-1,input().split()))
ans = []
for i in range(n-2):
    if p[i] == i:
        continue
    for j in range(i+1,n):
        if p[j] == i:
            break
    if j != n-1:
        ans.append([j+1,i])
        p = p[:i]+p[j:j+2]+p[i:j]+p[j+2:]
    else:
        ans.append([n-1,n-3])
        ans.append([n-1,i+1])
        p = p[:i]+[p[-1],p[-3]]+p[i:-3]+[p[-2]]
if p[-1] == n-1:
    print('Yes')
    print(len(ans))
    for i in range(len(ans)):
        print(*ans[i])
else:
    print('No')