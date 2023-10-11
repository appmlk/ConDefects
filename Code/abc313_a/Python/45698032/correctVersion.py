n=int(input())
p=list(map(int,input().split()))
ans=0 if p.count(max(p))==1 and p[0]==max(p) else max(p)+1-p[0]
print(ans)