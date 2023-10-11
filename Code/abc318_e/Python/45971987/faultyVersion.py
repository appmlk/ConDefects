N = int(input())
A = list(map(int,input().split()))
check = [[] for _ in range(N+1)]
for i in range(N):
    check[A[i]].append(i)
ans = 0
for i in check:
    tmp = []
    for j in range(len(i)-1):
        tmp.append(i[j+1]-i[j]-1)
    for j in range(len(tmp)):
        ans += tmp[j]*(len(tmp)-j)    
print(ans)