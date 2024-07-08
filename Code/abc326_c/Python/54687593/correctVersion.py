n,m = map(int,input().split())
A = list(map(int,input().split()))
A.sort()

j = 0
ans = 0
for i in range(n):
    x = A[i] + m
    while j < n and A[j] < x:      
        ans = max(ans,j-i+1)
        j += 1

print(ans)