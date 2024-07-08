N,M,K=map(int,input().split())
Tak=[list(map(int,input().split())) for i in range(N)]
Ao=[list(map(int,input().split())) for i in range(M)]

from bisect import *
def is_ok(x):
    X=x/100
    Ao2=[-Ao[i][0]+X*(Ao[i][0]+Ao[i][1]) for i in range(M)]
    Ao2.sort()
    count=0
    for i in range(N):
        now=Tak[i][0]-X*(Tak[i][0]+Tak[i][1])
        ind=bisect_right(Ao2,now)
        count+=ind
    if count>=K:
        return True
    else:
        return False
    
ok=0
ng=100
while ng-ok>10**(-6):
    mid=(ng+ok)/2
    if is_ok(mid):
        ok=mid
    else:
        ng=mid
print(ok)

