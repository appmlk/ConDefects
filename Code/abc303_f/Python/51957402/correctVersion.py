def btw(lf,ri):
    return ri*(ri-1)//2-lf*(lf-1)//2
N,H=map(int,input().split())
spell=[tuple(map(int,input().split())) for i in range(N)]
spell.sort(reverse=True,key=lambda x:x[::-1])
use=[spell[0]+(1,)]
for i in range(1,N):
    if use[-1][0]*use[-1][1]>spell[i][0]*spell[i][1]:
        continue
    use.append(spell[i]+((use[-1][0]*use[-1][1]+spell[i][1]-1)//spell[i][1],))
ok=10**18
ng=0
while ok-ng>1:
    mid=(ok+ng)//2
    now=mid+1
    h=H
    for t,d,lf in reversed(use):
        if now<=lf:
            continue
        t=min(t,now)
        h-=btw(lf,t)*d+(now-t)*d*t
        now=lf
    if h<=0:
        ok=mid
    else:
        ng=mid
print(ok)