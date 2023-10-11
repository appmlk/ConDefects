n,a,b=map(int,input().split())
ans = [[] for _ in range(a*n)]
for i in range(a*n):
    for j in range(b*n):
        if ((i//a)%2==0 and (j//b)%2==0) or ((i//a)%2!=0 and (j//b)%2!=0):
            ans[i].append('.')
        else:
            ans[i].append('#')

for i in range(a*n):
    print(*ans[i])