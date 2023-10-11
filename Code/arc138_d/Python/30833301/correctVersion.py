n,k=map(int,input().split())
if n==1:
    print('Yes')
    print(0,1)
    exit()
if (not k&1) or k==n:
    print('No')
    exit()

gray = [0]


for i in range(n):
    nxt = []
    flip = False
    for ai in gray:
        if flip:
            nxt.append(ai|(1<<i))
            nxt.append(ai)
        else:
            nxt.append(ai)
            nxt.append(ai|(1<<i))
        flip^=1
    gray=nxt


def bit_cnt(x):
    return sum((x>>i)&1 for i in range(n))

bb = [i for i in range(1<<n) if bit_cnt(i)==k]
base = set()
base2 = set()
for bi in bb:
    bi2 = bi
    for x in base2:
        bi2 = min(bi2,bi2^x)
    if bi2:
        base.add(bi)
        base2.add(bi2)
base = list(base)

def f(x):
    res = 0
    for i in range(n):
        if (x>>i)&1:
            res^=base[i]
    return res

ans = [f(gi) for gi in gray]
print('Yes')
print(*ans)
