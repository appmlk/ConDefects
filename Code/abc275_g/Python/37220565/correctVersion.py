import sys
input = lambda :sys.stdin.readline()[:-1]
ni = lambda :int(input())
na = lambda :list(map(int,input().split()))
yes = lambda :print("yes");Yes = lambda :print("Yes");YES = lambda : print("YES")
no = lambda :print("no");No = lambda :print("No");NO = lambda : print("NO")
#######################################################################

ok = 10
ng = 0
n = ni()
a,b,c = zip(*[na() for i in range(n)])
eps = 10**(-10)
while ok-ng>10**(-8):
    X = (ok+ng)/2
    s = 1
    l = 0
    r = X
    for i in range(n):
        if a[i] == b[i]:
            if c[i] - a[i]*X > 0:
                s = 0
                break
        elif b[i] - a[i] > 0:
            l = max(l, (c[i]-a[i]*X)/(b[i]-a[i]))
        else:
            r = min(r, (c[i]-a[i]*X)/(b[i]-a[i]))
    if l <= r+eps and s:
        ok = X
    else:
        ng = X

print(ok)
