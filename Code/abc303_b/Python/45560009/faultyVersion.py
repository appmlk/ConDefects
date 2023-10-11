n,m = map(int,input().split())
hunaka = [[1] * n for _ in range(n)]
for _ in range(m):
    a = list(map(int,input().split()))
    for i in range (n-1):
        x,y = a[i],a[i+1]
        hunaka[x-1][y-1]=0
        hunaka[y-1][x-1]=0
ans = sum(sum(row) for row in hunaka) - n
print(ans/2)