import itertools
import math
N,L,R= (int(x) for x in input().split())
ll= 0
lr = 0
ns = []
nh = []
a = 0
b = 0
c = 0
l0 = 0
count = 0
for i in range (1 , N):
    c = N - i + c
    if L <= c:
        ll = i
        Bc = c - (N - i)
        rl = L - Bc + ll
        break
for i in range (ll,N):
    if i !=ll:
        c = N - i + c
    if R <= c:
        lr = i
        Bc = c - (N - i)
        rr = R - Bc + lr
        break
for i in range(1 ,ll):
    nh.append(i)
for i in range( 0 , lr - ll):
    nh.append(N - i)
for i in range(ll, N - (lr - ll) + 1):
    ns.append(i)
if rl - ll <= len(ns):
    ns.insert(rl - ll-1,ns.pop(0))
else:
    l = len(ns)
    l0 = rl -ll
    for i in range(1,l0 - l + 1):
        ns.append(nh.pop(-1))
    nh.append(ns.pop(0))
    for i in range(1,l0 - l):
        nh.append(ns.pop(-1))                
for r in range(1 , rr - lr + 1):
    tmp = ns[r]
    ns[r] = ns[0]
    ns[0] = tmp
print(*(nh + ns))


