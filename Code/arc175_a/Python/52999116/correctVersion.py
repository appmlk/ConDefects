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
p = LI(1)
s = input()
M = 998244353
v0 = p[0]
def sub(v0):
    ans = 1
    done = [0]*n
    # p[0] の v0 はとる
    if v0=="L":
        done[p[0]] = 1
    else:
        done[(p[0]+1)%n] = 1
    for i in range(1,n):
        if v0=="L":
            if s[p[i]]=="R" and not done[(p[i]+1)%n]:
                return 0
            assert not done[p[i]]
            done[p[i]] = 1
            if s[p[i]]=="?" and done[(p[i]+1)%n]:
                ans *= 2
                ans %= M
        else:
            if s[p[i]]=="L" and not done[p[i]]:
                return 0
            assert not done[(p[i]+1)%n]
            done[(p[i]+1)%n] = 1
            if s[p[i]]=="?" and done[p[i]]:
                ans *= 2
                ans %= M
    return ans
if s[p[0]]=="?":
    ans = sub("L") + sub("R")
else:
    ans = sub(s[p[0]])
print(ans%M)