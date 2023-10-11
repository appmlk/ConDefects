import sys
input = sys.stdin.readline

n, q = map(int, input().split())

skips = [0] * (n + 1)
for _ in range(q):
    l, r = map(int, input().split())
    skips[l - 1] += 1
    skips[r] += 1
skips.pop()
skips.pop(0)

comp = [v for v in skips if v]
#comp.sort()

sz = len(comp) + 1
o = 0

while pow(2, o) < sz:
    o += 1

if o == 0:
    print(0, 1)
    sys.exit()

take = sz - pow(2, o - 1)

INF = 10 ** 6

curr = [INF] * (take + 1)
curr[0] = 0

care = curr[:]

for v in comp:
    nex = [INF]

    for i in range(take):
        nex.append(curr[i] + v)

    for i in range(take + 1):
        curr[i] = min(curr[i], care[i])
    care = nex

print(o, 2 * min(care[-1], curr[-1]))
    
