import bisect
n,m=map(int,input().split())
a=list(map(int,input().split()))
a.sort()
ans=0
for i in range(n):
    ans=max(ans,bisect.bisect_left(a,a[i]+m)-i)
print(ans)