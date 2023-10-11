N,M = map(int,input().split())
A = list(map(int,input().split()))
B = list(map(int,input().split()))

A.sort()
B.sort()
amax = A[-1]
bmax = B[-1]

#ok = 10**9
ok = max(amax,bmax)
ng = 0
while abs(ok-ng)>1:
    mid = (ok+ng)//2
    sell = 0
    for a in A:
        if a <= mid:
            sell += 1
    buy = 0
    for b in B:
        if b >= mid:
            buy += 1
    #print(f"ok={ok},ng={ng}")
    #print(f"sell={sell},buy={buy},mid={mid}")
    if sell >= buy:
        ok = mid
    else:
        ng = mid
#print(f"mid={mid}")
print(ok)