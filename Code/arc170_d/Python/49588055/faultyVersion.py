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

def merge_interval(lr):
    # list of [l,r)
    lr.sort()
    ans = []
    pl = None
    max_r = -INF
    for l,r in lr:
        if l>max_r:
            if pl is not None:
                ans.append((pl, max_r))
            max_r = r
            pl = l
        else:
            max_r = max(max_r, r)
    ans.append((pl, max_r))
    return ans
t = II()
YES = "Alice"
NO = "Bob"
def sub(a,b):
    a,b = min(a,b), max(a,b)
    return b-(a-1), b+(a-1)
for _ in range(t):
    n = int(input())
    a = list(map(int, input().split()))
    b = LI()
    a.sort()
    b.sort()
    from bisect import bisect_left as bl
    for i in range(1,n)[::-1]:
        if a[i]-(a[i-1]-1)<=b[0]<=a[i]+(a[i-1]-1):
            lr = []
            for j in range(n):
                if j==i:
                    continue
                ll,rr = sub(a[i], a[j])
                lr.append((ll,rr))
            lr = merge_interval(lr)
            num = 0
            for ll,rr in lr:
                num += bl(b, rr) - bl(b, ll)
#             print(i,lr)
            if num==n:
                ans = 1
            else:
                ans = 0
            break
    else:
        for i in range(n):
            l = []
            js = list(range(i-1,i+2))
            for j in js:
                if j==i or not (0<=j<n):
                    continue
                x = a[i]
                x2 = a[j]
                ll = max(x,x2) - min(x,x2) + 1
                rr = x+x2
                l.append((ll,rr))
            lr = merge_interval(l)
    #         print(lr)
            num = 0
            for ll,rr in lr:
                num += (bl(b, rr) - bl(b, ll))
            if num==n:
                ans = 1
                break
        else:
            ans = 0
    pans(ans)
#     break