N,M = map(int,input().split())
S = input()
T = input()

arr = [[0,0]]
for i in range(N-M+1):
    if T == S[i:i+M]:
        arr.append([i,i+M])
arr.append([N,N])

if not arr:
    exit(print('No'))

for j,(l,r) in enumerate(arr):
    if j==0 or j==len(arr)-1: continue
    ll = arr[j-1][1]
    rr = arr[j+1][0]
    ok = {l}
    minx = l
    for x in range(l-1,ll-1,-1):
        for i in range(1,M):
            if x+i in ok and T[:i] == S[x:x+i]:
                ok.add(x)
                minx = x
    arr[j][0] = minx
    ok = {r}
    maxx = r
    for x in range(r+1,rr+1):
        for i in range(1,M):
            if x-i in ok and T[-i:] == S[x-i:x]:
                ok.add(x)
                maxx = x
    arr[j][1] = maxx

for (_,l),(r,_) in zip(arr,arr[1:]):
    if S[l:r] not in T:
        exit(print('No'))
print('Yes')