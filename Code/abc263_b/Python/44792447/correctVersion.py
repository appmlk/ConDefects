n=int(input())
p=list(map(int, input().split()))
h=p[-1]
ans=0
while h!=1:
    h=p[h-2]
    ans+=1
if p[-1]==1:
    print(1)
else:
    print(ans+1)