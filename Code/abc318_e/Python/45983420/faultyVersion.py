N=int(input())
A = list(map(int, input().split()))

l = [[] for i in range(3*10**5+1)]

for i,j in enumerate(A):
    l[j].append(i)
#print(l[1])
ans=0
for i in range(1,300001):

    now = l[i]
    cnt = 0
    M = len(now)-1
    for j in range(len(now)-1):
        space = now[j+1]-now[j]-1
        cnt += M-j
        ans += cnt * space
        cnt-=1
print(ans)