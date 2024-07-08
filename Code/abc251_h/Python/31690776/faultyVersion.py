import sys
readline = sys.stdin.readline

n,m,k = map(int,readline().split())
ac = [list(map(int,readline().split())) for _ in range(m)]
ac = ac[::-1]

d = n-k
pow7 = [7**i for i in range(20)]
for i in range(20)[::-1]:
    P = pow7[i]
    q,d = divmod(d,P)
    if q==0: continue
    for _ in range(q):
        p = P
        res = []
        ac2 = [x[:] for x in ac]
        while ac2[-1][1] and p >= ac2[-1][1]:
            a,c = ac2.pop()
            p -= c
        ac2[-1][1] -= p

        #print(ac,ac2,"yes",P)
        while ac2:
            a1,c1 = ac[-1]
            a2,c2 = ac2[-1]
            v = min(c1,c2)
            r = (a1+a2)%7
            #print(r,v)
            if res and res[-1][0] == r:
                res[-1][1] += v
            else:
                res.append([(a1+a2)%7,v])
            if c1==v:
                ac.pop()
            else:
                ac[-1][1] -= v
            if c2==v:
                ac2.pop()
            else:
                ac2[-1][1] -= v
        ac = res
        #print(ac)
    #print(ac)

ans = []
for a,c in ac:
    ans += [a%7]*c
print(*ans[::-1])
    
