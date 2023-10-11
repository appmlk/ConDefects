def solve(n, vs):
    ans = []
    l = 0
    while (l+1)*(l+2)//2 <= n:
        l += 1
    ans.append('7'*l)
    n -= l*(l+1)//2
    if n:
        for i in range(l):
            for j in range(len(vs)):
                vs[j] = (vs[j]*10+7)%7
        nxt = 1
        while nxt < 10:
            b = ((nxt%7)!=0)
            for v in vs:
                if (v*10+nxt)%7 == 0:
                    b = False
            if b:
                break
            nxt += 1
        ans.append(str(nxt))
        for j in range(len(vs)):
            vs[j] = (vs[j]*10+nxt)%7
        vs.append(nxt)
        ans += solve(n, vs)
    return ans

n = int(input())
vs = []
ans = solve(n, vs)
ans = ''.join(ans)
print(ans)
