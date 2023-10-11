def can(A,digit):
    maskA = [a & (2**digit-1) for a in A]
    maskA.sort()
    ok = []
    ng = []
    for a in maskA:
        if 1<<(digit-1) & a:
            ok.append(a)
        else:
            ng.append(a)
    if len(ok) >= K:
        return ok,0
    else:
        tmp = 0
        for i in range(K-len(ok)):
            x = ng.pop()
            tmp += (1<<(digit-1))-x
        return [1<<(digit-1)]*(K-len(ok))+ok,tmp
N,M,K = map(int,input().split())
A = list(map(int,input().split()))
ans = 0
for i in range(30,0,-1):
    newa,cost = can(A,i)
    if cost <= M:
        M -= cost
        A = newa
        ans += 2**(i-1)
print(ans)