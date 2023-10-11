n,d,p=map(int,input().split())
f=list(map(int,input().split()))
f.sort(reverse=True)
ans=0
i=0
while d*i<n:
    ans+=min(p,sum(f[d*i:d*(i+1)]))
    i+=1
print(ans)