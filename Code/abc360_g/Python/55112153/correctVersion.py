from atcoder.segtree import SegTree

n = int(input())
alist = list(map(int, input().split()))

se = set([0])
uplim = pow(10,9)
se.add(uplim)

for a in alist:
    se.add(a)
    # if a < uplim:
    se.add(a+1)
    # if a > 1:
    #     se.add(a-1)

so = sorted(se)

dic = {v:i for i,v in enumerate(so)}

zatu = [dic[a] for a in alist]

def op(a,b):
    if a >= b:
        return a
    else:
        return b

e = -10

seg0 = SegTree(op,e,[0 for i in range(len(so)+10)])
seg1 = SegTree(op,e,[0 for i in range(len(so)+10)])

# print(zatu)

cj = 0
cx = 1
for i in range(n):
    a = zatu[i]
    now0 = seg0.prod(0,a)
    now1 = seg1.prod(0,a)

    seg0.set(a, max(now0+1, seg0.get(a)))
    seg1.set(a, max(now1+1, seg1.get(a)))
    seg1.set(cj, max(cx, seg1.get(cj)))

    cj = a+1
    cx = now0+2

ans = seg1.all_prod()

print(ans)

