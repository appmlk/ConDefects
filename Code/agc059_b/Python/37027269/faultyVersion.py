import sys
from collections import defaultdict
out = []

input = sys.stdin.readline

t = int(input())

for _ in range(t):
    n = int(input())
    l = input().split()

    d = {}
    
    for v in l:
        if v not in d:
            d[v] = 1
        else:
            d[v] += 1

    big2 = []
    big = []
    br = []
    smol = []

    for v in d:
        if d[v] == 1:
            smol.append(v)
        else:
            big.extend([v] * (d[v] - 1))
            big2.extend([v] * d[v])
            br.append(v)

    big = big + br[::-1]

    m = len(big)
    oo = []
    for i in range(m):
        if big[i] == big[i - 1] and smol:
            oo.append(smol.pop())
        oo.append(big[i])

    if smol:
        out.append(' '.join(big2 + smol))
    else:
        out.append(' '.join(oo))

    

    

print('\n'.join(out))
