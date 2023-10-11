
n,m,k = map(int, input().split())

s = input()

li = [0 for i in range(n+1)]

for i in range(1, n+1):
    c = s[i-1]
    li[i] += li[i-1] 
    if c == "x":
        li[i] += 1

xcnt = li[-1]
ans = -1


for l in range(n):
    
    ok = 1
    ng = n*m - l
    
    while abs(ok-ng) > 1:
        mid = (ok+ng)//2
        # print(ok,mid,ng)

        # [l, l+mid)内にxがk個以上存在するか
        repeat = (l+mid) // n 
        res = (l+mid) % n
        
        
        xnum = xcnt * repeat - li[l] + li[res]
        # print(ok,mid,ng, xnum)
        if xnum <= k:
            ok = mid
        else:
            ng = mid

    if ans < ok:
        ans = ok

print(ans)



