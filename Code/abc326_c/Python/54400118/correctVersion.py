import bisect 
n, m = map(int,input().split())
A = sorted(map(int,input().split()))

ans = 0
for i in range(n):
    ans = max(ans, bisect.bisect_left(A, A[i] + m) - i)
    
print(ans)
