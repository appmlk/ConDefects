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

t = II()
for _ in range(t):
    n = int(input())
    a = list(map(int, input().split()))
    if all((v%2==0 for v in a)):
        ans = 1
    else:
        M2 = -INF
        M = -INF
        na = []
        for v in a:
            if v%2:
                na.append(v)
                M = max(M, v)
            else:
                M2 = max(M2, v)
        a = na
        a.sort(reverse=True)
        if M2>len(a):
            ans = 1
        else:
            for i in range(0, len(a), 2):
                if a[i]>len(a)-1-i:
                    ans = 1
                    break
            else:
                ans = 0
    pans(ans)