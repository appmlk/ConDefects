import sys, random
input = lambda : sys.stdin.readline().rstrip()


write = lambda x: sys.stdout.write(x+"\n"); writef = lambda x: print("{:.12f}".format(x))
debug = lambda x: sys.stderr.write(x+"\n")
YES="Yes"; NO="No"; pans = lambda v: print(YES if v else NO); INF=10**18
LI = lambda v=0: list(map(lambda i: int(i)-v, input().split())); II=lambda : int(input()); SI=lambda : [ord(c)-ord("a") for c in input()]
def debug(_l_):
    for s in _l_.split():
        print(f"{s}={eval(s)}", end=" ")
    print()
def dlist(*l, fill=0):
    if len(l)==1:
        return [fill]*l[0]
    ll = l[1:]
    return [dlist(*ll, fill=fill) for _ in range(l[0])]

n = int(input())
lr = [LI() for _ in range(n)] + [[1e12,1e12]]
vs = [[1e12,1e12,[]]] + [lr[0]+[[0]]] # [[l,r,[ind...]] ,...
for i in range(1,n+1):
    l,r = lr[i]
    while len(vs)>=2:
        if i==n and len(vs)==2:
            break
        pl,pr,pind = vs[-1]
        pl2,pr2,pind2 = vs[-2]
        if pl<l and pl<pl2 and pl<pr:
            if pl2<=l and pl2<=pr:
                # pl2 まで上げる
                pr = min(pr, pr2)
                if len(pind)>len(pind2):
                    pind.extend(pind2)
                else:
                    pind2.extend(pind)
                    pind = pind2
                vs.pop()
                vs.pop()
                vs.append([pl2, pr, pind])
            elif l<=pr:
                # l まで上げる
                pr = min(pr, r)
                pind.append(i)
                vs.pop()
                vs.append([l,pr,pind])
                break
            else:
                # pr まで上げて、lr を追加
                vs.pop()
                vs.append([pr,pr,pind])
                vs.append([l,r,[i]])
                break
        else:
            vs.append([l,r,[i]])
            break
ans = [0]*n
for l,r,index in vs:
    for i in index:
        if i<n:
            ans[i] = l
print(*ans)