from operator import itemgetter

N,D=map(int,input().split())
li=[list(map(int,input().split())) for _ in range(N)]
li.sort(key=itemgetter(1))

punch_length=-1
ans=0

for i in range(N):
    l,r=li[i]
    if ans==0:
        ans+=1
        punch_length=r+D-1
    else:
        if punch_length<l:
            ans+=1
            punch_length=r+D-1


print(ans)